package restaurant.model.service;

import restaurant.model.personnel.Cuisinier;
import restaurant.model.Brigade;
import restaurant.model.plat.Plat;
import restaurant.model.plat.StatutPlat;
import restaurant.exception.ServiceSurchargeException;

import java.util.*;

public class Service {

    private String nomService;
    private Brigade<Cuisinier> brigade;
    private Queue<Plat> commandes;
    private List<Plat> platsTermines;

    public Service(String nomService, Brigade<Cuisinier> brigade) {
        this.nomService = nomService;
        this.brigade = brigade;
        this.commandes = new LinkedList<Plat>();
        this.platsTermines = new ArrayList<Plat>();

    }

    public void ajouterCommande(Plat plat){
        commandes.offer(plat);
    }

    public void traiterProchaineCommande() throws ServiceSurchargeException {
        if(commandes.size() > 10 ){
            throw new ServiceSurchargeException("Il y'a trop de commandes");
        }
        Plat plat = commandes.poll();
        if (plat != null) {
            plat.setStatut(StatutPlat.PRET);
            platsTermines.add(plat);
        }
    }

        public void afficherEtatService(){
            System.out.println("Service : " + this.nomService);
            System.out.println("Commandes en attente : " + commandes.size());
            System.out.println("Plats terminés : " + platsTermines.size());
        }

    public List<Plat> getPlatsTermines() {
        return platsTermines;
    }
}


