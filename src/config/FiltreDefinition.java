// Classe FiltreDefinition qui contient la définition d'un filtre, avec son type et ses paramètres.
package config;

import java.util.Map;

public class FiltreDefinition {

    // Attribut 'type' qui représente le type du filtre (par exemple, "MajusculeMinuscule").
    private String type;

    // Attribut 'params' qui est une carte (Map) contenant des paramètres associés au filtre.
    // La clé et la valeur sont toutes les deux des chaînes de caractères (String).
    private Map<String, String> params;

    // Getter pour l'attribut 'type' qui retourne le type du filtre.
    public String getType() {
        return type;
    }

    // Setter pour l'attribut 'type' qui permet de définir le type du filtre.
    public void setType(String type) {
        this.type = type;
    }

    // Getter pour l'attribut 'params' qui retourne les paramètres associés au filtre.
    public Map<String, String> getParams() {
        return params;
    }

    // Setter pour l'attribut 'params' qui permet de définir les paramètres du filtre.
    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}