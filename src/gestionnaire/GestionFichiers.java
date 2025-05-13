// Classe GestionFichiers qui fournit des méthodes pour lire et écrire des fichiers.
package gestionnaire;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GestionFichiers {

    // Méthode pour lire un fichier et retourner son contenu sous forme de tableau de bytes.
    // Le chemin du fichier est passé en paramètre.
    public static byte[] lireFichier(String chemin) throws IOException {
        // Utilise la méthode readAllBytes de la classe Files pour lire tous les bytes du fichier.
        // 'Path.of(chemin)' convertit la chaîne de caractères en un objet Path représentant le fichier.
        return Files.readAllBytes(Path.of(chemin));
    }

    // Méthode pour écrire des données dans un fichier.
    // Le chemin du fichier et les données sous forme de tableau de bytes sont passés en paramètre.
    public static void ecrireFichier(String chemin, byte[] donnees) throws IOException {
        // Utilise la méthode write de la classe Files pour écrire les données dans le fichier.
        // Si le fichier n'existe pas, il sera créé automatiquement.
        Files.write(Path.of(chemin), donnees);
    }
}