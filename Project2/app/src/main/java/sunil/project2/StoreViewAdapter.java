package sunil.project2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import sunil.project2.Products.Product;

/**
 * Created by sunil on 7/26/16.
 */
public class StoreViewAdapter extends RecyclerView.Adapter<StoreViewHolder> {

    List<Product> productList;

    public StoreViewAdapter(ArrayList<Product> plist){
        productList = plist;
    }

    @Override
    public StoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View parentView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.activitymain_toinflate,parent,false);

        StoreViewHolder holder = new StoreViewHolder(parentView);
        return holder;
    }

    @Override
    public void onBindViewHolder(StoreViewHolder holder, int position) {

        final Product product = productList.get(position);

        /** Assign values to recyclerview members **/
        // get our R.drawable string
        final Context context = holder.imageview.getContext();
        int imgloc = context.getResources().getIdentifier(productList.get(position).getImgID(),"drawable",context.getPackageName());
        //format price with commas, and add ".00" to the end
        double dprice = Double.parseDouble(product.getItemPrice());
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        String formattedprice = formatter.format(dprice);


        holder.imageview.setImageResource(imgloc);
        holder.titleview.setText(product.getItemName());
        holder.descview.setText(product.getDescription());
        holder.priceview.setText("$"+formattedprice);
        holder.stockview.setText(product.getStock()+" In Stock");

        // Set a Listener on the imageview
        // Pass a value to the product detail activity,
        // and use that value as a query to populate the product description
        holder.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ProductDetailActivity.class);
                intent.putExtra("ITEM_NAME", product.getItemName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
