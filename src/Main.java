// Importation des bibliothèques nécessaires pour le traitement JSON, la gestion des fichiers et les filtres
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import config.FiltreDefinition;
import filtres.*;
import gestionnaire.GestionFichiers;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Initialisation d'un scanner pour recueillir l'entrée de l'utilisateur depuis la console
        Scanner scanner = new Scanner(System.in);

        // Demander à l'utilisateur de choisir entre un fichier texte ou un fichier binaire
        System.out.println("Veuillez choisir un fichier à traiter :");
        System.out.println("1 - Text File");
        System.out.println("2 - Binary File");
        int fileChoice = scanner.nextInt();  // Lecture du choix de l'utilisateur

        String filePath = "";  // Variable qui contiendra le chemin du fichier sélectionné
        boolean isTextFile = false;  // Variable pour savoir si le fichier est texte ou binaire

        // Bloc switch pour déterminer le chemin du fichier et le type de fichier selon le choix de l'utilisateur
        switch (fileChoice) {
            case 1:
                filePath = "entree.txt";  // Si l'utilisateur choisit le fichier texte
                isTextFile = true;  // Le fichier est texte.
                break;
            case 2:
                filePath = "Virevolt.pdf";  // Si l'utilisateur choisit le fichier binaire
                isTextFile = false;  // Le fichier est binaire.
                break;
            default:
                System.err.println("Choix invalide.");  // Si l'utilisateur fait un choix invalide
                return;  // Quitte le programme
        }

        // Vérification que le fichier sélectionné existe bien
        if (!Files.exists(Paths.get(filePath))) {
            System.err.println("Le fichier sélectionné n'existe pas.");  // Si le fichier n'existe pas
            return;
        }

        // Lecture du fichier JSON de configuration pour obtenir les informations sur les filtres disponibles
        String cheminConfig = "config.json";  // Spécifie le chemin du fichier de configuration JSON
        Gson gson = new Gson();  // Création de l'objet Gson pour travailler avec le JSON
        JsonObject configObject = gson.fromJson(new FileReader(cheminConfig), JsonObject.class);  // Lecture et conversion du fichier JSON en objet JsonObject
        JsonArray filtersArray = configObject.getAsJsonArray("filters");  // Récupération de l'Array "filters" du fichier JSON

        // Conversion de l'Array JSON en liste d'objets FiltreDefinition
        List<FiltreDefinition> filtres = gson.fromJson(filtersArray, new TypeToken<ArrayList<FiltreDefinition>>(){}.getType());

        // Initialisation d'une liste pour stocker les filtres disponibles selon le type de fichier
        List<Filtre> availableFilters = new ArrayList<>();
        System.out.println("Filtres disponibles:");

        // Parcours de la liste des filtres et ajout des filtres compatibles en fonction du type de fichier (texte ou binaire)
        for (FiltreDefinition def : filtres) {
            Filtre filter = null;  // Variable pour stocker le filtre choisi

            // Vérification du type de filtre en fonction du type de fichier
            switch (def.getType()) {
                case "FiltreMajusculeMinuscule":
                    if (isTextFile) {
                        filter = new FiltreMajusculeMinuscule();  // Si c'est un fichier texte, on applique le filtre Majuscule/Minuscule
                    }
                    break;
                case "FiltreCryptageAtbash":
                    if (isTextFile) {
                        filter = new FiltreCryptageAtbash();  // Si c'est un fichier texte, on applique le filtre Cryptage Atbash
                    }
                    break;
                case "FiltreCryptageKey":
                    if (!isTextFile) {
                        // Si c'est un fichier binaire, on crée un filtre de Cryptage avec la clé récupérée depuis les paramètres JSON
                        String cle = def.getParams().get("key");
                        filter = new FiltreCryptageKey(cle);
                    }
                    break;
                case "FiltreBitwiseNot":
                    if (!isTextFile) {
                        filter = new FiltreBitwiseNot();  // Si c'est un fichier binaire, on applique le filtre Bitwise Not
                    }
                    break;
                default:
                    break;
            }

            // Si un filtre compatible est trouvé, on l'ajoute à la liste des filtres disponibles
            if (filter != null) {
                availableFilters.add(filter);
                System.out.println("- " + def.getType());  // Affiche le type de filtre disponible
            }
        }

        // Si aucun filtre compatible n'est trouvé, afficher une erreur
        if (availableFilters.isEmpty()) {
            System.err.println("Erreur: aucun filtre approprié pour le type de fichier choisi.");
            return;  // Quitte le programme si aucun filtre compatible n'est disponible
        }

        // Demander à l'utilisateur de choisir le filtre qu'il souhaite appliquer
        System.out.println("Choisissez un filtre à appliquer:");
        int filterChoice = 1;
        for (Filtre filter : availableFilters) {
            System.out.println(filterChoice + ". " + filter.getClass().getSimpleName());  // Affiche le nom simple de la classe du filtre
            filterChoice++;
        }

        // Validation du choix de filtre de l'utilisateur
        int userFilterChoice = scanner.nextInt();
        while (userFilterChoice < 1 || userFilterChoice > availableFilters.size()) {
            System.out.println("Choix invalide. Veuillez refaire votre choix:");
            userFilterChoice = scanner.nextInt();  // Demande à l'utilisateur de refaire un choix valide
        }

        // Sélectionner le filtre en fonction du choix de l'utilisateur
        Filtre selectedFilter = availableFilters.get(userFilterChoice - 1);

        // Lire le fichier sélectionné en tant que tableau de bytes
        byte[] donnees = GestionFichiers.lireFichier(filePath);

        // Appliquer le filtre choisi
        if (isTextFile) {
            // Si c'est un fichier texte, on convertit les bytes en chaîne de caractères et on applique le filtre
            String texte = new String(donnees);
            donnees = selectedFilter.appliquer(texte).getBytes();
        } else {
            // Si c'est un fichier binaire, on applique directement le filtre sur les données binaires
            donnees = selectedFilter.appliquer(donnees);
        }

        // Choisir le chemin du fichier de sortie en fonction du type de fichier sélectionné
        String cheminSortie;
        switch (fileChoice) {
            case 1:
                cheminSortie = "sortie_text.txt";  // Fichier texte de sortie
                break;
            case 2:
                cheminSortie = "sortie_binaire.bin";  // Fichier binaire de sortie
                break;
            default:
                System.err.println("Choix invalide.");
                return;  // Quitte si le choix est invalide
        }

        // Sauvegarder les données modifiées dans le fichier de sortie
        try {
            GestionFichiers.ecrireFichier(cheminSortie, donnees);
            System.out.println("Filtre appliqué avec succès. Résultat enregistré dans " + cheminSortie);  // Affiche un message de succès
        } catch (Exception e) {
            System.err.println("Erreur lors de l'enregistrement du fichier: " + e.getMessage());  // Gestion des erreurs lors de l'écriture du fichier
        }

        scanner.close();
    }
}