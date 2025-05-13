// Classe abstraite Filtre, qui sert de base pour les filtres spécifiques
package filtres;

public abstract class Filtre {

    // Méthode abstraite qui applique le filtre sur un tableau de bytes.
    // Chaque classe qui hérite de Filtre devra implémenter cette méthode.
    public abstract byte[] appliquer(byte[] donnees);

    // Méthode abstraite qui applique le filtre sur une chaîne de caractères.
    // Chaque classe qui hérite de Filtre devra aussi implémenter cette méthode.
    public abstract String appliquer(String texte);
}