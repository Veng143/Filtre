// Classe FiltreCryptageKey qui implémente un filtre de chiffrement avec une clé personnalisée
package filtres;

public class FiltreCryptageKey extends Filtre {
    private final byte[] cle;  // Clé utilisée pour le chiffrement (stockée sous forme de tableau de bytes)

    // Constructeur qui accepte une clé sous forme de chaîne de caractères.
    // La clé est convertie en tableau de bytes afin de pouvoir être utilisée pour le chiffrement.
    public FiltreCryptageKey(String cle) {
        this.cle = cle.getBytes();  // Convertit la chaîne de caractères en tableau de bytes
    }

    // Méthode qui applique le chiffrement sur un tableau de bytes en utilisant l'opération XOR.
    @Override
    public byte[] appliquer(byte[] donnees) {
        byte[] resultat = new byte[donnees.length]; // Création d'un tableau pour stocker le résultat du chiffrement
        for (int i = 0; i < donnees.length; i++) {
            // Applique l'opération XOR entre chaque byte du tableau 'donnees' et la clé (répétée si nécessaire).
            // XOR est une opération binaire où chaque bit de la donnée est comparé avec celui de la clé.
            // Si les bits sont identiques, le résultat est 0, sinon 1.
            // La clé est répétée en utilisant 'i % cle.length' pour gérer les cas où la clé est plus courte que les données.
            resultat[i] = (byte) (donnees[i] ^ cle[i % cle.length]);
        }
        return resultat; // Retourne le tableau de bytes résultant du chiffrement
    }

    // Méthode qui applique le chiffrement sur une chaîne de caractères en la convertissant en bytes.
    @Override
    public String appliquer(String texte) {
        // Convertit le texte en tableau de bytes, applique le filtre et reconvertit en chaîne de caractères.
        return new String(appliquer(texte.getBytes()));
    }
}