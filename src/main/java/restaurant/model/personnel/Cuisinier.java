package restaurant.model.personnel;

public abstract class Cuisinier extends Personnel{

    private String poste;

    public Cuisinier(String nom, String prenom, int niveauExperience, String poste){
        super(nom, prenom, niveauExperience);
        this.poste = poste;
    }

    public void setPoste(String poste){
        this.poste = poste;
    }

    public String getPoste(){
        return this.poste;
    }

    @Override
    public abstract void travailler();

    @Override
    public String toString(){
        return super.toString() + ", je suis un(e) cuisinier(e) qui gère ce poste : " + this.poste;
    }
}
