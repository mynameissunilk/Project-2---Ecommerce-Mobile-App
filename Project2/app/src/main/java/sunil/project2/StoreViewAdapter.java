package sunil.project2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunil on 7/26/16.
 */
public class StoreViewAdapter extends RecyclerView.Adapter<StoreViewHolder> {

    ArrayList<Product> productList;

    public StoreViewAdapter(final ArrayList<Product> plist){
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

        Product product = productList.get(position);

        //need to pass this to getresources
        product.getItemName()

        //holder.animalview.setImageResource(product());
        holder.titleview.setText(product.getItemName());
        holder.descview.setText(product.getDescription());
        //holder.priceview.setText(product.getItemPrice());
        holder.stockview.setText((int)product.getStock());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}







//int resId = context.getResources().getIdentifier("picture1","drawable",context.getPackageName());
//image.setImageResource(resId);;