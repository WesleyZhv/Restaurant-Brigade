package restaurant.main;

import java.util.*;
import restaurant.model.personnel.Cuisinier;
import restaurant.model.personnel.Manager;
import restaurant.model.personnel.cuisine.*;
import restaurant.model.personnel.management.*;
import restaurant.model.Brigade;
import restaurant.model.plat.Plat;
import restaurant.model.plat.StatutPlat;

public class Main {
    public static void main(String[] args){
        Brigade<Cuisinier> brigadeCuisine = new Brigade<Cuisinier>("Brigade Cuisine");
        Brigade<Manager> brigadeAdmin = new Brigade<Manager>("Brigade Administration");

        System.out.println("                      *****************************************************");
        System.out.println("                      Bienvenue au Restaurant de Wesley, voici la brigade !");
        System.out.println("                      *****************************************************");

        //Brigade de cuisine
        ChefExecutif chefExe = new ChefExecutif("Morel", "Lucas", "Chef de cuisine");
        SousChef sousChef = new SousChef("Vuillemin", "Emma", "Sous Cheffe de Cuisine");
        ChefDePartie cdp1 = new ChefDePartie("Girard", "Nathan", "Chef de Partie pour les sauces");
        ChefDePartie cdp2 = new ChefDePartie("Perret", "Chloé", "Chef de Partie pour les pâtisseries");
        Commis com1 = new Commis("Marchand", "Hugo", " entrées");
        Commis com2 = new Commis("Favre", "Sarah", " desserts avec " + cdp2.getPrenom());
        Commis com3 = new Commis("Piguet", "Laura", " viandes");

        //Service administration
        DirecteurRestaurant direc = new DirecteurRestaurant("Dubois", "Thomas", "Directeur de la restauration");
        ResponsableRH resRH = new ResponsableRH("Perrin", "Julien", "Ressources humaines");

        //Affectation de liste
        brigadeCuisine.ajouterMembre(chefExe);
        brigadeCuisine.ajouterMembre(sousChef);
        brigadeCuisine.ajouterMembre(cdp1);
        brigadeCuisine.ajouterMembre(cdp2);
        brigadeCuisine.ajouterMembre(com1);
        brigadeCuisine.ajouterMembre(com2);
        brigadeCuisine.ajouterMembre(com3);

        brigadeAdmin.ajouterMembre(direc);
        brigadeAdmin.ajouterMembre(resRH);

        //Je commence par présenté la cuisine :
        System.out.println(" ");
        System.out.println("Voici les rôles en cuisine :");
        System.out.println(" ");
        brigadeCuisine.afficherBrigade();

        System.out.println(" ");
        System.out.println("Voici les rôles en administration :");
        System.out.println(" ");
        brigadeAdmin.afficherBrigade();

        Plat plat1 = new Plat("Lasagne", 230, List.of("Pâte","Viande", "Tomate", "Oignon", "Carotte", "Celeris branche", "Vin rouge", "Carotte", "Béchamel"));

        //2 Status différent grâce au Enum
        System.out.println(plat1.toString());
        plat1.setStatut(StatutPlat.PRET);

        System.out.println(plat1.toString());






    }
}
