package sunil.project2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Product>inventoryRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar
        //Toolbar toolbar = (Toolbar)findViewById(R.id.maintoolbar);
        //setSupportActionBar(toolbar);

        //let's talk to the helper
        StoreDBHelper helper = StoreDBHelper.getInstance(MainActivity.this);

        //add objects to their respective lists
        populateInventory();
        populateIncidents();

        //insert lists into tables
        helper.insertInventoryList(StoreLists.getInstance().getInventoryList());
        helper.insertIncidentList(StoreLists.getInstance().getIncidentsList());

        //RecyclerAdapter...
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(llm);

        StoreViewAdapter adapter = new StoreViewAdapter(StoreLists.getInstance().getInventoryList());
        recyclerView.setAdapter(adapter);
    }

    // add Inventory objects to the singleton
    public void populateInventory(){
        StoreLists.getInstance().addToInventory(new Product("Tiger", "Big cat with Stripes.", 3000, true, 7, R.drawable.tiger));
        StoreLists.getInstance().addToInventory(new Product("Wolf", "Big dog with ferocity", 750, true, 15, R.drawable.wolf));
        StoreLists.getInstance().addToInventory(new Product("Great White Shark", "Popular predatory fish", 250000, true, 2, R.drawable.shark));
        StoreLists.getInstance().addToInventory(new Product("Saltwater Crocodile", "Large, Dangerous Crocodile", 10000, true, 8, R.drawable.croc));
        StoreLists.getInstance().addToInventory(new Product("Komodo Dragon", "Sort of a dragon", 2500, true, 4, R.drawable.komodo));
        StoreLists.getInstance().addToInventory(new Product("Asian Giant Hornet", "The killer-bee-killer", 250, true, 100, R.drawable.hornet));
        StoreLists.getInstance().addToInventory(new Product("Beaver", "Not all that dangerous, kind of cute...", 150, true, 200, R.drawable.beaver));
        StoreLists.getInstance().addToInventory(new Product("Walrus", "I call my brother-in-law a walrus... I mean buy this.", 1000, true, 7, R.drawable.walrus));
        StoreLists.getInstance().addToInventory(new Product("Brown Bear", "Cute killer indigenous to North America", 10000, true, 5, R.drawable.bear));
        StoreLists.getInstance().addToInventory(new Product("Gila Monster", "Sounds cool", 5000, true, 2, R.drawable.gilamonster));
        StoreLists.getInstance().addToInventory(new Product("King Cobra", "World's longest venomous snake!", 20000, true, 6, R.drawable.cobra));
    }

    // add Incident objects to the singleton
    public void populateIncidents(){
        StoreLists.getInstance().addToIncidents(new Incidents("ambushed paperboy","Newark, NJ", "MIA", false));
        StoreLists.getInstance().addToIncidents(new Incidents("ate favorite parakeet", "Abuja, Nigeria", "gone native", true));
        StoreLists.getInstance().addToIncidents(new Incidents("stole five seals", "Capetown, South Africa", "applying for dolphin license", true));
        StoreLists.getInstance().addToIncidents(new Incidents("snacked on snorkelers", "Australia", "MIA", true));
        StoreLists.getInstance().addToIncidents(new Incidents("bit me, didn't even breathe fire","Indonesia","I got over it",false));
        StoreLists.getInstance().addToIncidents(new Incidents("can take on ten times as many normal bees!","Japan","In pain",false));
        StoreLists.getInstance().addToIncidents(new Incidents("Dammed a stream to death","Ottawa","MIA",false));
        StoreLists.getInstance().addToIncidents(new Incidents("Not too dangerous, did I mention I call my brother-in-law a walrus?","New York","alive",false));
        StoreLists.getInstance().addToIncidents(new Incidents("taught former owner the bare necessities with massive size, claws","Wilderness","KIA",true));
        StoreLists.getInstance().addToIncidents(new Incidents("boring poisonous reptile","boring poisonous places","KIA... poison-duh",true));
        StoreLists.getInstance().addToIncidents(new Incidents("longest poisonous snake","unknown","KIA",true));
    }
}
