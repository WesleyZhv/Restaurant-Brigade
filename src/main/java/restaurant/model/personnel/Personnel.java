package restaurant.model.personnel;

    public abstract class Personnel {

        private String nom;
        private String prenom;
        private int niveauExperience;

        public Personnel(String nom, String prenom, int niveauExperience){
            this.nom = nom;
            this.prenom = prenom;
            this.niveauExperience = niveauExperience;
        }

        public void setNom(String nom){
            this.nom = nom;
        }

        public String getNom() {
            return this.nom;
        }

        public void setPrenom(String prenom){
            this.prenom = prenom;
        }

        public String getPrenom() {
            return this.prenom;
        }

        public void setNiveauExperience(int niveauExperience){
            this.niveauExperience = niveauExperience;
        }

        public int getNiveauExperience(){
            return this.niveauExperience;
        }

        public abstract void travailler();

        @Override
        public String toString(){
            return "Je m'appelle " + this.prenom + " " + this.nom + " et mon niveau est de " + this.niveauExperience;
        }
    }

