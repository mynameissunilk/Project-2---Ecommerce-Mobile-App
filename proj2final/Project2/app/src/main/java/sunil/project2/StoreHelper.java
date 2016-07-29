package sunil.project2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sunil on 7/28/16.
 */
public class StoreHelper extends SQLiteOpenHelper {

    private static StoreHelper sInstance;

    public static final String DB_NAME="online_store";
    public static final int DB_VER=7;

    public static StoreHelper getInstance(Context context){
        if(sInstance==null){
            sInstance = new StoreHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private StoreHelper(Context context) {
        super(context.getApplicationContext(), DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Schema.Inventory.CREATE_INV_TABLE);
        sqLiteDatabase.execSQL(Schema.Cart.CREATE_CART_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Schema.Inventory.DELETE_INV_TABLE);
        sqLiteDatabase.execSQL(Schema.Cart.DELETE_CART_TABLE);
        onCreate(sqLiteDatabase);
    }
    /** Query Stuff **/
    // _id, name, description...
    String[] nameDescProjection = new String[]{Schema.Inventory._ID, Schema.Inventory.INV_NAME, Schema.Inventory.INV_DESC};
    // all columns
    String[] everyProjection = new String[]{
            Schema.Inventory._ID,
            Schema.Inventory.INV_NAME,
            Schema.Inventory.INV_DESC,
            Schema.Inventory.INV_PRICE,
            Schema.Inventory.INV_QUANT,
            Schema.Inventory.INV_IMGID};

    // add Pet objects  to Inventory Table...
    public void addInventoryRow(Pet p){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Schema.Inventory.INV_NAME,p.getName());
        cv.put(Schema.Inventory.INV_DESC,p.getDescription());
        cv.put(Schema.Inventory.INV_PRICE,p.getPrice());
        cv.put(Schema.Inventory.INV_QUANT,p.getQuantity());
        cv.put(Schema.Inventory.INV_IMGID,p.getImgResId());
        db.insertWithOnConflict(Schema.Inventory.INVENTORY_T_NAME,null,cv,SQLiteDatabase.CONFLICT_ABORT);
    }

    // get a row by id, when receiving an id in an intent payload
    public Cursor getRowById(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Schema.Inventory.INVENTORY_T_NAME,
                everyProjection,
                Schema.Inventory._ID+" =?",
                new String[]{String.valueOf(id)},
                null, null,null);

        if(cursor.moveToFirst())
            return cursor;
        else return null;
    }

    // get *everything* ..
    public Cursor getAllProducts(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Schema.Inventory.INVENTORY_T_NAME,
                everyProjection,null,null,null,null,null);
        cursor.moveToFirst();
        return cursor;
    }

    public void insertCartRow(Pet p){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Schema.Cart.CART_NAME,p.getName());
        values.put(Schema.Cart.CART_PRICE,p.getPrice());
        db.insert(Schema.Cart.CART_NAME,null,values);
    }

    public Cursor searchTitleDesc(String query){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(Schema.Inventory.INVENTORY_T_NAME,
                nameDescProjection,
                Schema.Inventory.INV_DESC+" =?",
                new String[]{"%"+query+"%"},
                null,
                null,
                null);
        cursor.moveToFirst();
        return cursor;
    }

}
