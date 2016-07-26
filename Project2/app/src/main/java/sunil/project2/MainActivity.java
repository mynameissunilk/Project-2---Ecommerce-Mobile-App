package sunil.project2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // i want to call the list from here so I can generate my recyclerview
        // why is that so fucking hard to do? I have no fucking idea!
        InventoryList inventoryList = InventoryList.getInstance();

        InventoryHelper helper = InventoryHelper.getInstance(MainActivity.this);

        inventoryList.populateInventory();
        // add arraylist objects to the table
        for(int i = 0; i<inventoryList.mInventoryList.size(); i++){
            helper.addInvRow(inventoryList.mInventoryList.get(i));
        }
    }
}
