package restaurant.model.personnel.cuisine;

import restaurant.model.personnel.Cuisinier;

public class ChefDePartie extends Cuisinier {

    public ChefDePartie(String nom, String prenom, String poste){
        super(nom, prenom, 3, poste);

    }

    @Override
    public void travailler(){
        System.out.println(getPrenom() + " supervise l'ensemble de son poste qui est : " + getPoste());
    }

    @Override
    public String toString(){
        return super.toString() + ", et je suis le chef d'une seule partie";
    }
}

