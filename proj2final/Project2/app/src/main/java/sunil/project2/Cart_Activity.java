package sunil.project2;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Cart_Activity extends AppCompatActivity {

    TextView titletext,pricetext, quanttext;
    Button cartbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_);


        ShopLists cart = ShopLists.getInstance();



        StoreHelper helper = StoreHelper.getInstance(Cart_Activity.this);
        titletext = (TextView)findViewById(R.id.itemtext);
        pricetext = (TextView)findViewById(R.id.pricetext);
        quanttext = (TextView)findViewById(R.id.quantttext);
        cartbutton = (Button)findViewById(R.id.cartbutton);

        //Cursor cursor = helper.getRowById(id)
        int id = getIntent().getIntExtra("PET_ID",-1);
        if(id>=0){
            Cursor cursor = helper.getRowById(id);
            titletext.setText(cursor.getString(cursor.getColumnIndex(Schema.Inventory.INV_NAME)));
            pricetext.setText("PRICE: "+cursor.getString(cursor.getColumnIndex(Schema.Inventory.INV_PRICE)));
            quanttext.setText("QUANTITY IN STOCK: "+cursor.getString(cursor.getColumnIndex(Schema.Inventory.INV_QUANT)));
        }
        cartbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Cart_Activity.this,"Might have been added to cart",Toast.LENGTH_LONG).show();


            }
        });

    }
}
