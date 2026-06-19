package restaurant.model;

import restaurant.model.personnel.Personnel;
import java.util.*;

public class Brigade<T extends Personnel>{

    private ArrayList<T> membres;
    private String nomBrigade;

    public Brigade(String nomBrigade){
        this.nomBrigade = nomBrigade;
        this.membres = new ArrayList<T>();
    }

    public void setNom(String nomBrigade){
        this.nomBrigade = nomBrigade;
    }

    public String getNom(){
        return this.nomBrigade;
    }

    public void ajouterMembre(T membre){
        membres.add(membre);
    }

    public void afficherBrigade(){
        for(int i = 0; i < membres.size(); i++) {
            membres.get(i).travailler();
            System.out.println(membres.get(i));
            System.out.println(" ");
        }
    }

    public T chercherParNom(String nom){
        for (int i = 0; i < membres.size(); i++) {
            if (membres.get(i).getNom().equals(nom)) {
                return membres.get(i);
            }
        }
        return null;

    }

}
