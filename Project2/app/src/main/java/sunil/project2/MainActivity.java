package sunil.project2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.List;

import sunil.project2.Products.Product;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Product>inventoryRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar)findViewById(R.id.mainactivity_toolbar);
        //setSupportActionBar(toolbar);

        //let's talk to the helper
        StoreDBHelper helper = StoreDBHelper.getInstance(MainActivity.this);

        //add objects to their respective lists
        populateInventory();

        //insert lists into tables
        helper.insertInventoryList(StoreLists.getInstance().getInventoryList());

        //RecyclerAdapter...
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(llm);

        //adapter takes list of product objects stored in singleton class
        StoreViewAdapter adapter = new StoreViewAdapter(StoreLists.getInstance().getInventoryList());
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menus, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // add Inventory objects to the singleton
    public void populateInventory(){
        StoreLists.getInstance().addToInventory(new Product("Tiger", "Big cat with Stripes.", "3000", "7", "tiger"));
        StoreLists.getInstance().addToInventory(new Product("Wolf", "Big dog with ferocity", "750", "15", "wolf"));
        StoreLists.getInstance().addToInventory(new Product("Great White Shark", "Popular predatory fish", "250000", "2", "shark"));
        StoreLists.getInstance().addToInventory(new Product("Saltwater Crocodile", "Large, Dangerous Crocodile", "10000", "8", "croc"));
        StoreLists.getInstance().addToInventory(new Product("Komodo Dragon", "Sort of a dragon", "2500", "4", "komodo"));
        StoreLists.getInstance().addToInventory(new Product("Asian Giant Hornet", "The killer-bee-killer", "250", "100", "hornet"));
        StoreLists.getInstance().addToInventory(new Product("Beaver", "Not all that dangerous, kind of cute...", "150", "200", "beaver"));
        StoreLists.getInstance().addToInventory(new Product("Walrus", "I call my brother-in-law a walrus... I mean buy this.", "1000", "7", "walrus"));
        StoreLists.getInstance().addToInventory(new Product("Brown Bear", "Cute killer indigenous to North America", "10000", "5", "bear"));
        StoreLists.getInstance().addToInventory(new Product("Gila Monster", "Sounds cool", "5000","2", "gilamonster"));
        StoreLists.getInstance().addToInventory(new Product("King Cobra", "World's longest venomous snake!", "20000","6", "cobra"));
    }

}
