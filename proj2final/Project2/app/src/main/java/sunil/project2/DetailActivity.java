package sunil.project2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class DetailActivity extends AppCompatActivity {

    ImageView imgview;
    TextView titletext, desctext, price, quanttext;
    int itemid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        // receive intent from MainActivity
        // call our helper
        StoreHelper helper = StoreHelper.getInstance(DetailActivity.this);

        titletext = (TextView)findViewById(R.id.textview_title);
        desctext = (TextView)findViewById(R.id.textview_desc);
        price = (TextView)findViewById(R.id.textview_price);
        quanttext = (TextView)findViewById(R.id.textview_stock);
        imgview = (ImageView)findViewById(R.id.imageview);


        // display columns for clicked item
        int id = getIntent().getIntExtra("ID",-1);
        if(id>=0){
            Cursor cursor = helper.getRowById(id);

            //store row id to put into cart intent
            itemid = id;

            //give our row's columns to their respective widgets
            Context context = imgview.getContext();
            int imgresrc = context.getResources().getIdentifier("drawable/" + cursor.getString(cursor.getColumnIndex(Schema.Inventory.INV_IMGID)), null, context.getPackageName());
            imgview.setImageResource(imgresrc);
            titletext.setText(cursor.getString(cursor.getColumnIndex(Schema.Inventory.INV_NAME)));
            desctext.setText(cursor.getString(cursor.getColumnIndex(Schema.Inventory.INV_DESC)));


            String pricecat = "$"+cursor.getString(cursor.getColumnIndex(Schema.Inventory.INV_PRICE));

            //DecimalFormat formatter = new DecimalFormat("###,###,###");
            //formatter.format(pricecat);
            price.setText(pricecat);


            String quantcat = "Quantity: "+cursor.getString(cursor.getColumnIndex(Schema.Inventory.INV_QUANT));
            quanttext.setText(quantcat);
        }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent = new Intent(DetailActivity.this, Cart_Activity.class);
                intent.putExtra("PET_ID",itemid);
                startActivity(intent);
            }
        });
    }
}
