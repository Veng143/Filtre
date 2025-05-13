// Classe FiltreMajusculeMinuscule qui implémente un filtre de transformation des majuscules et minuscules.
package filtres;

public class FiltreMajusculeMinuscule extends Filtre {

    // Applique le filtre sur un tableau de bytes. Convertit d'abord les bytes en chaîne, puis applique la méthode 'appliquer' sur cette chaîne.
    @Override
    public byte[] appliquer(byte[] donnees) {
        return appliquer(new String(donnees)).getBytes(); // Convertit les bytes en String et applique le filtre
    }

    // Applique le filtre de transformation sur une chaîne de caractères.
    // Les majuscules deviennent des minuscules, les minuscules deviennent des majuscules, et les chiffres sont inversés.
    @Override
    public String appliquer(String texte) {
        StringBuilder sb = new StringBuilder(); // Utilisation d'un StringBuilder pour une construction efficace de la chaîne résultante.

        // Parcours chaque caractère du texte pour effectuer les transformations nécessaires.
        for (char c : texte.toCharArray()) {
            // Si le caractère est une majuscule, il est transformé en minuscule.
            if (Character.isUpperCase(c)) {
                sb.append(Character.toLowerCase(c));
            }
            // Si le caractère est une minuscule, il est transformé en majuscule.
            else if (Character.isLowerCase(c)) {
                sb.append(Character.toUpperCase(c));
            }
            // Si le caractère est un chiffre, l'opération inverse les chiffres : 9 devient 0, 8 devient 1, etc.
            else if (Character.isDigit(c)) {
                sb.append(9 - Character.getNumericValue(c)); // Inverse la valeur numérique du chiffre.
            }
            // Si le caractère n'est ni une lettre ni un chiffre, il est ajouté tel quel (ponctuation, espaces, etc.).
            else {
                sb.append(c);
            }
        }

        // Retourne la chaîne résultante après toutes les transformations.
        return sb.toString();
    }
}