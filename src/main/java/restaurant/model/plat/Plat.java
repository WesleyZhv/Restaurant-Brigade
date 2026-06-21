package restaurant.model.plat;

import java.util.*;
import restaurant.exception.PlatIndisponibleException;

public class Plat {

    private String nom;
    private int tempsPreparation;
    private List<String> ingredients = new ArrayList<String>();
    private StatutPlat status;

public Plat(String nom, int tempsPreparation, List<String> ingredients){
    this.nom = nom;
    this.tempsPreparation = tempsPreparation;
    this.ingredients = ingredients;
    this.status = StatutPlat.EN_ATTENTE;
}

public void setNom(String nom){
    this.nom = nom;
}

public String getNom(){
    return this.nom;
}

public void setTempsPreparation(int tempsPreparation){
        this.tempsPreparation = tempsPreparation;
}

public int getTempsPreparation(){
    return this.tempsPreparation;
}

public List<String> getIngredients() {
    return this.ingredients;

}
    public void setStatut(StatutPlat status){
    this.status = status;
}
    public StatutPlat getStatut(){
        return this.status;
    }

    public void verifierDisponibilite(List<String> stockDisponible) throws PlatIndisponibleException{
        for (int i = 0; i < this.ingredients.size(); i++) {
            if (!stockDisponible.contains(ingredients.get(i))) {
                throw new PlatIndisponibleException("Le plat suivant est indisponible : " + ingredients.get(i));
            }
        }
    }

    @Override
    public String toString(){
    return "Plat : " + getNom() + ", temps de préparation : " + getTempsPreparation() + " min, statut : " + this.status;
    }
}

