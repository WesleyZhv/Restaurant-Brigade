package restaurant.model.personnel.management;

import restaurant.model.personnel.Manager;

public class ResponsableRH extends Manager {

    public ResponsableRH(String nom, String prenom, String departement){
        super(nom, prenom, 4, departement);
    }

    @Override
    public void travailler(){
        System.out.println("Je suis le ResponsableRh du restaurant et je m'occupe de ce departement : " + getDepartement());
    }

    @Override
    public String toString(){
        return super.toString() + ", et je suis le recruteur du restaurant";
    }

}
