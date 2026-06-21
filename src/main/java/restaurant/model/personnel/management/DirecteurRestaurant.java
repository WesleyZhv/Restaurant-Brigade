package restaurant.model.personnel.management;

import restaurant.model.personnel.Manager;

public class DirecteurRestaurant extends Manager {

    public DirecteurRestaurant(String nom, String prenom, String departement){
        super(nom, prenom, 5, departement);
    }

    @Override
    public void travailler(){
        System.out.println("Je suis le directeur du restaurant et je m'occupe de ce departement : " + getDepartement());
    }

    @Override
    public String toString(){
        return super.toString() + ", et je suis le superviseur du restaurant";
    }

}