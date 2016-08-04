package sunil.project2;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import sunil.project2.Products.Product;

public class ProductDetailActivity extends AppCompatActivity {

    TextView titleview,detailview,stockview,priceview;
    ImageView imageview;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        //references...
        imageview = (ImageView) findViewById(R.id.imageview_detailactivity);
        titleview = (TextView)findViewById(R.id.title_detailactivity);
        detailview = (TextView) findViewById(R.id.description_detailactivity);
        stockview = (TextView) findViewById(R.id.stock_detailactivity);
        priceview = (TextView) findViewById(R.id.price_detailactivity);
        button = (Button)findViewById(R.id.button_detailactivity);

        // get the helper in this activity
         StoreDBHelper helper = StoreDBHelper.getInstance(ProductDetailActivity.this);

        // get the name that we mapped in the mainactivity intent
        // query the db by name to return all of its columns/info
        String name = getIntent().getStringExtra("ITEM_NAME");
        final Cursor cursor = helper.getRowFromName(name);

        // get images, format price with commas...
        Context context = imageview.getContext();
        int imgrsc = context.getResources().getIdentifier(cursor.getString(cursor.getColumnIndex(Schemas.Inventory.ITEM_THUMB)),"drawable",context.getPackageName());
        double dprice = Double.parseDouble(cursor.getString(cursor.getColumnIndex(Schemas.Inventory.ITEM_PRICE)));
        DecimalFormat formatter = new DecimalFormat("#,###.00");

        // get our info to the widgets
        imageview.setImageResource(imgrsc);
        titleview.setText(cursor.getString(cursor.getColumnIndex(Schemas.Inventory.ITEM_NAME)));
        detailview.setText(cursor.getString(cursor.getColumnIndex(Schemas.Inventory.ITEM_DESC)));
        stockview.setText(cursor.getString(cursor.getColumnIndex(Schemas.Inventory.ITEM_QUANT)));
        priceview.setText("$"+formatter.format(dprice));
        //cursor.close();

        // add to cart button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProductDetailActivity.this,"Added to Cart",Toast.LENGTH_SHORT).show();
                StoreDBHelper.getInstance(ProductDetailActivity.this).addToCartByCursor(cursor);
            }
        });

    }
}
