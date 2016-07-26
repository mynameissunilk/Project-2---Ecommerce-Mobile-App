package sunil.project2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunil on 7/26/16.
 */
public class InventoryList {
    private static InventoryList sInstance;

    List<Product> mInventoryList = new ArrayList<>();

    public static InventoryList getInstance(){
        if(sInstance==null){
            sInstance = new InventoryList();
        }
        return sInstance;
    }

    private InventoryList(){}

    public void populateInventory(){

        mInventoryList.add(new Product("Tiger","Big cat with Stripes.",3000,true,7,R.drawable.tiger ));
        mInventoryList.add(new Product("Wolf","Big dog with ferocity", 750,true,15,R.drawable.wolf));
        mInventoryList.add(new Product("Great White Shark", "Popular predatory fish", 250000,true,2,R.drawable.shark));
        mInventoryList.add(new Product("Saltwater Crocodile", "Large, Dangerous Crocodile", 10000, true, 8,R.drawable.croc));
        mInventoryList.add(new Product("Komodo Dragon", "Sort of a dragon", 2500,true,4,R.drawable.komodo));
        mInventoryList.add(new Product("Asian Giant Hornet", "The killer-bee-killer",250,true,100,R.drawable.hornet));
        mInventoryList.add(new Product("Beaver","Not all that dangerous, kind of cute...",150,true,200,R.drawable.beaver));
        mInventoryList.add(new Product("Walrus","I call my brother-in-law a walrus... I mean buy this.",1000,true,7,R.drawable.walrus));
        mInventoryList.add(new Product("Brown Bear","Cute killer indigenous to North America",10000,true,5,R.drawable.bear));
        mInventoryList.add(new Product("Gila Monster", "Sounds cool",5000,true,2,R.drawable.gilamonster));
        mInventoryList.add(new Product("King Cobra","World's longest venomous snake!",20000,true, 6,R.drawable.cobra));

    }
// tiger, wolf, shark, croc, dragon, hornet, beaver, walrus, bear, gila mon, king cobra
    public void populateIncidents(){



    }

    // now we add these to the database. but only once. and we check for duplicates because otherwise life would suck

    // this class is going to insert, update, and read from the database
    // after onclicking the add button, call update inventory

}
