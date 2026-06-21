package restaurant.model.personnel.cuisine;

import restaurant.model.personnel.Cuisinier;

public class ChefExecutif extends Cuisinier{
    public ChefExecutif(String nom, String prenom, String poste){
        super(nom, prenom, 5, poste);

    }

    @Override
    public void travailler(){
        System.out.println(getPrenom() + " supervise l'ensemble de la brigade.");
    }

    @Override
    public String toString(){
        return super.toString() + " et je gère toute les cuisines !";
    }
}

