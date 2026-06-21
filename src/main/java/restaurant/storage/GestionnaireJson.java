package restaurant.storage;

import restaurant.model.plat.Plat;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;

public class GestionnaireJson {

    public void sauvegarderPlat(Plat plat, String cheminFichier){
    Gson gson = new Gson();
    try(FileWriter writer = new FileWriter(cheminFichier)){
        gson.toJson(plat, writer);
    } catch(IOException e){
        e.printStackTrace();
    }
    }
}
