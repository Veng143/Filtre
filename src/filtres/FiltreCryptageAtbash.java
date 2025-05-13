// Classe FiltreCryptageAtbash qui implémente un filtre de chiffrement basé sur la méthode Atbash.
package filtres;

public class FiltreCryptageAtbash extends Filtre {

    // Applique le filtre de chiffrement Atbash sur un tableau de bytes.
    // La méthode convertit d'abord le tableau de bytes en String, puis applique la méthode 'appliquer' sur la chaîne.
    @Override
    public byte[] appliquer(byte[] donnees) {
        // Convertit les bytes en String, applique le filtre et reconvertit le résultat en bytes.
        return appliquer(new String(donnees)).getBytes();
    }

    // Applique le chiffrement Atbash sur une chaîne de caractères.
    // Le chiffrement Atbash consiste à inverser l'alphabet : A devient Z, B devient Y, etc.
    @Override
    public String appliquer(String texte) {
        StringBuilder sb = new StringBuilder(); // Utilisation d'un StringBuilder pour une modification efficace de la chaîne.

        // Parcourt chaque caractère du texte.
        for (char c : texte.toCharArray()) {
            // Si le caractère est une majuscule, on effectue la transformation Atbash.
            if (Character.isUpperCase(c)) {
                // 'A' + ('Z' - c) effectue l'inversion de la lettre dans l'alphabet pour les majuscules.
                sb.append((char) ('A' + ('Z' - c)));
            }
            // Si le caractère est une minuscule, on applique également la transformation Atbash.
            else if (Character.isLowerCase(c)) {
                // 'a' + ('z' - c) effectue l'inversion de la lettre dans l'alphabet pour les minuscules.
                sb.append((char) ('a' + ('z' - c)));
            }
            // Si le caractère n'est ni une majuscule ni une minuscule, il est ajouté tel quel (espace, ponctuation, etc.).
            else {
                sb.append(c);
            }
        }
        return sb.toString(); // Retourne la chaîne modifiée.
    }
}