// Classe FiltreBitwiseNot qui hérite de la classe Filtre
package filtres;

public class FiltreBitwiseNot extends Filtre {

    // Applique la négation binaire (~) sur chaque byte du tableau
    @Override
    public byte[] appliquer(byte[] donnees) {
        byte[] resultat = new byte[donnees.length]; // Crée un tableau de même taille pour stocker le résultat
        for (int i = 0; i < donnees.length; i++) {
            // Inverse chaque bit de donnees[i] et stocke le résultat dans 'resultat'
            resultat[i] = (byte) ~donnees[i];
        }
        return resultat; // Retourne le tableau modifié
    }

    // Applique le même filtre sur un texte sous forme de chaîne de caractères
    @Override
    public String appliquer(String texte) {
        // Convertit le texte en bytes, applique le filtre, puis reconvertit en chaîne de caractères
        return new String(appliquer(texte.getBytes()));
    }
}