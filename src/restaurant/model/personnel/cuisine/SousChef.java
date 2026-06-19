package restaurant.model.personnel.cuisine;

import restaurant.model.personnel.Cuisinier;

public class SousChef extends Cuisinier{

    public SousChef(String nom, String prenom, String poste){
        super(nom, prenom, 4, poste);
    }

    @Override
    public void travailler(){
        System.out.println(getPrenom() + " supervise l'ensemble de la brigade quand le chef Executif n'est pas là.");
    }

    @Override
    public String toString(){
        return super.toString() + ", et je suis la cheffe qui dirige quand le chef Executif est absent.";
    }
}