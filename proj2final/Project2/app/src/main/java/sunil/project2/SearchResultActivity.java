package sunil.project2;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SearchResultActivity extends AppCompatActivity {

    ListView searchListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        searchListView = (ListView)findViewById(R.id.search_result_view);


        // handle search query
        if(Intent.ACTION_SEARCH.equals(getIntent().getAction())){
            String query = getIntent().getStringExtra(SearchManager.QUERY);
            // assign cursor to query method
            Cursor cursor = StoreHelper.getInstance(SearchResultActivity.this).searchTitleDesc(query);
            // then use a cursor adapter to populate a list of search results, then set up an onclicklistener to take you to the product page you clicked on
            CursorAdapter queryAdapter = new CursorAdapter(this, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER) {
                @Override
                public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
                    return LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1,viewGroup,false);
                }

                @Override
                public void bindView(View view, Context context, Cursor cursor) {
                    TextView resultText = (TextView)view.findViewById(android.R.id.text1);
                    resultText.setText(cursor.getString(cursor.getColumnIndex(Schema.Inventory.INV_DESC)));
                }
            };

        }

    }
}
