package sunil.project2;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create some pet objects
        Pet pet1 = new Pet("Tiger","Large striped cat",
                "tiger","2000", "10");
        Pet pet2 = new Pet("Great White Shark","Popular Carnivorous Fish",
                "shark","250000","2");
        Pet pet3 = new Pet("Wolf","Fierce Wild Dog",
                "wolf","5000","7");
        Pet pet4 = new Pet("Saltwater Crocodile","The reason snorkelers die in Australia",
                "croc","10000","9");
        Pet pet5 = new Pet("Komodo Dragon","Real life dragon",
                "komodo","5000","22");
        Pet pet6 = new Pet("Asian Giant Hornet","Killer bee killer",
                "hornet","250","100");
        Pet pet7 = new Pet("Beaver","Cute wood murderer",
                "beaver","1000","48");
        Pet pet8 = new Pet("Walrus","Tusked floating cow",
                "walrus","7000","4");
        Pet pet9 = new Pet("Brown Bear","Majestic forest custodian",
                "bear","10000","8");
        Pet pet10 = new Pet("Gila Monster","Sounds cool also poisonous",
                "gilamonster","500","21");
        Pet pet11 = new Pet("King Cobra","Longest venomous snake in the world",
                "cobra","700","10");


        StoreHelper helper = StoreHelper.getInstance(MainActivity.this);
        // add Pets to the table
        helper.addInventoryRow(pet1);
        helper.addInventoryRow(pet2);
        helper.addInventoryRow(pet3);
        helper.addInventoryRow(pet4);
        helper.addInventoryRow(pet5);
        helper.addInventoryRow(pet6);
        helper.addInventoryRow(pet7);
        helper.addInventoryRow(pet8);
        helper.addInventoryRow(pet9);
        helper.addInventoryRow(pet10);
        helper.addInventoryRow(pet11);

        ListView listview = (ListView) findViewById(R.id.mainlistview);

        final Cursor cursor = helper.getAllProducts();
        CursorAdapter adapter = new CursorAdapter(this, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.item_toinflate,parent,false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {

                int imgresrc = context.getResources().getIdentifier("drawable/" +
                        cursor.getString(cursor.getColumnIndex(Schema.Inventory.INV_IMGID)), null, context.getPackageName());

                TextView textview = (TextView)view.findViewById(R.id.textview_toinflate);
                textview.setBackgroundResource(imgresrc);
                int nameindex = cursor.getColumnIndex(Schema.Inventory.INV_NAME);
                textview.setText(cursor.getString(nameindex));
            }
        };
        listview.setAdapter(adapter);

        //we want to get data for the item we click on
        //we pass the id column value into the intent

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                cursor.moveToPosition(i);
                intent.putExtra("ID",cursor.getInt(cursor.getColumnIndex(Schema.Inventory._ID)));
                startActivity(intent);
            }
        });
    }
    // search is supposed to query descriptions, take you to searchresultactivity, and add results to a listview, from which you can navigate to the product-in-question...
    // can't figure out why the intent isn't picked up by the searchresultactivity...
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        // more to go here
        SearchManager searchmanager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);

        // using support searchview FWIW...
        SearchView searchview = (SearchView)menu.findItem(R.id.search).getActionView();

        ComponentName componentname = new ComponentName(this,SearchResultActivity.class);
        searchview.setSearchableInfo(searchmanager.getSearchableInfo(componentname));
        return true;
    }

}
