package sunil.project2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunil on 7/26/16.
 */
public class StoreLists {
    private static StoreLists sInstance = null;

    private ArrayList<Product> mInventoryList = new ArrayList<>();
    private ArrayList<Incidents>mIncidentsList = new ArrayList<>();
    private ArrayList<Product>cart = new ArrayList<>();

    public static StoreLists getInstance(){
        if(sInstance==null){
            sInstance = new StoreLists();
        }
        return sInstance;
    }

    //Let's keep this constructor empty for now
    private StoreLists(){}

    public void addToInventory(Product prod){
        mInventoryList.add(prod);
    }

    public void addToIncidents(Incidents in){
        mIncidentsList.add(in);
    }

    public ArrayList<Product> getInventoryList(){
        return mInventoryList;
    }

    public ArrayList<Incidents> getIncidentsList(){
        return mIncidentsList;
    }

    public int getInventorySize(){return mInventoryList.size();}

}
