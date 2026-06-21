package restaurant.model.personnel.cuisine;

import restaurant.model.personnel.Cuisinier;

public class Commis extends Cuisinier{

    public Commis(String nom, String prenom, String poste){
        super(nom, prenom, 1, poste);
    }

    @Override
    public void travailler(){
        System.out.println(getPrenom() + " et je suis aux " + getPoste());
    }

    @Override
    public String toString(){
        return super.toString() + ", et je suis le commis de cuisine qui vient de commencer";
    }
}
