package app.rhuangal.com.crudexample.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;

/**
 * Created by rober on 06-Oct-17.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "MyDB";

    public static final String TABLE_PERSONA = "tb_persona";

    //Columnas de la Tabla Notes
    public static final String COL_ID = "id";
    public static final String COL_NOMBRE = "nombre";
    public static final String COL_TELEFONO = "telefono";
    public static final String COL_CORREO = "correo";

    //User table
    public static final String KEY_USER_ID = "id";
    public static final String KEY_USER_NAME = "name";

    private static DatabaseHelper mInstance = null;

    //Tablas
    public static final String CREATE_PERSONA = "CREATE TABLE " + TABLE_PERSONA + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
            + COL_NOMBRE + " TEXT,"
            + COL_TELEFONO + " TEXT,"
            + COL_CORREO + " TEXT" + ")";

    public static final String DROP_PERSONA ="DROP TABLE IF EXISTS " + TABLE_PERSONA;


    public DatabaseHelper(Context context) {
        // TODO Auto-generated constructor stub
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new DatabaseHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(CREATE_PERSONA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL(DROP_PERSONA);
    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }
}
