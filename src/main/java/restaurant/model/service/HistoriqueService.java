package restaurant.model.service;

import java.util.*;

public class HistoriqueService {

    private ArrayList<Service> historique;

    public HistoriqueService(){
        this.historique = new ArrayList<>();
    }

    public void ajouterService(Service service){
        historique.add(service);
    }

    public List<Service> getHistorique(){
        return historique;
    }

    public void afficherHistorique(){
        for(int i = 0; i < historique.size();i++){
            System.out.println(historique.get(i).getNom() + " - plats traités : " + historique.get(i).getPlatsTermines().size());
        }
    }
}
