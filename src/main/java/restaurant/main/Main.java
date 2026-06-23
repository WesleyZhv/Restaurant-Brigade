package restaurant.main;

import java.util.*;
import restaurant.model.personnel.Cuisinier;
import restaurant.model.personnel.Manager;
import restaurant.model.personnel.cuisine.*;
import restaurant.model.personnel.management.*;
import restaurant.model.Brigade;
import restaurant.model.plat.Plat;
import restaurant.model.plat.StatutPlat;
import restaurant.model.service.Service;
import restaurant.exception.ServiceSurchargeException;
import restaurant.exception.PlatIndisponibleException;
import restaurant.exception.PersonnelAbsentException;
import restaurant.storage.GestionnaireJson;

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

        //Test de la class Service qui utilise, list, queue et LinkedList ET test Exception
        Service serv = new Service("Service du soir", brigadeCuisine);
        Plat plat2 = new Plat("Crêpe au fromage", 20, List.of("Farine", "Oeuf", "Lait", "Fromage", "Sel"));

        serv.ajouterCommande(plat1);
        serv.ajouterCommande(plat2);

        serv.afficherEtatService();

        try{
            serv.traiterProchaineCommande();
        }
        catch(ServiceSurchargeException e){
            System.out.println(e.getMessage());
        }

        serv.afficherEtatService();

        //2eme Test pour 2eme Exception

        List<String> stock = List.of("Pâte", "Viande", "Tomate");

        try {
            plat1.verifierDisponibilite(stock);
        }
        catch(PlatIndisponibleException e){
            System.out.println(e.getMessage());
        }

        //3eme et dernière exception

        //Rien ne se passe dans la console car Chef de cuisine existe
        try {
            brigadeCuisine.verifierPosteCouvert("Chef de cuisine");
        }
        catch(PersonnelAbsentException e) {
            System.out.println(e.getMessage());
        }

        try {
            brigadeCuisine.verifierPosteCouvert(" Plongeur");
        }
        catch(PersonnelAbsentException e){
            System.out.println(e.getMessage());
        }

        //Utilisation de Json pour enregistré un plat
        GestionnaireJson gestionnaire = new GestionnaireJson();

        gestionnaire.sauvegarderPlat(plat1, "plat1.json");

        //Utilisation d'une désérealisation

        Plat platCharge = gestionnaire.chargerPlat("plat1.json");
        System.out.println(platCharge);

        
        //gestionnaire.sauvegarderBrigade(brigadeCuisine, "brigade.json");
        //Brigade<Cuisinier> brigadeChargee = gestionnaire.chargerBrigade("brigade.json");
        //brigadeChargee.afficherBrigade();



    }
}
