package restaurant.model.personnel;

public abstract class Manager extends Personnel{

    private String departement;

    public Manager(String nom, String prenom, int niveauExperience, String departement){
        super(nom, prenom, niveauExperience);
        this.departement = departement;
    }

    public void setDepartement(String departement){
        this.departement = departement;
    }

    public String getDepartement(){
        return this.departement;
    }

    @Override
    public abstract void travailler();

    @Override
    public String toString(){
        return "Je travaille à ce département ; " + this.departement;
    }
}
