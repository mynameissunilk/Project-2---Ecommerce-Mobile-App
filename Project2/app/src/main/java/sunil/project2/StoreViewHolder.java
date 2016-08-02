package sunil.project2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sunil on 7/26/16.
 */
public class StoreViewHolder extends RecyclerView.ViewHolder {
    ImageView imageview;
    TextView titleview, descview, priceview, stockview;

    public StoreViewHolder(View itemView) {
        super(itemView);

        imageview = (ImageView)itemView.findViewById(R.id.imageview);
        titleview = (TextView)itemView.findViewById(R.id.textview_title);
        descview  = (TextView) itemView.findViewById(R.id.textview_desc);
        priceview = (TextView) itemView.findViewById(R.id.textview_price);
        stockview = (TextView)itemView.findViewById(R.id.textview_stock);

    }
}
