package app.rhuangal.com.crudexample.storage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.rhuangal.com.crudexample.model.Persona;

/**
 * Created by rober on 06-Oct-17.
 */
public class OperationsDatabase {

    private static final String TAG = "OperationsDatabase";

    private DatabaseHelper openHelper;
    SQLiteDatabase database;
    
    public OperationsDatabase(SQLiteOpenHelper _helper) {
        super();
        openHelper =(DatabaseHelper)_helper;
    }

    public void open(){
        Log.i(TAG,"Database Opened");
        database = openHelper.getWritableDatabase();


    }
    public void close(){
        Log.i(TAG, "Database Closed");
        openHelper.close();

    }

    /*
     * CRUD ==> Create, Read, Update y Delete
     */

    /*public void createPersona(Persona noteEntity)
    {
        SQLiteDatabase db = openHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_NOMBRE, noteEntity.getNombre());
        values.put(DatabaseHelper.COL_TELEFONO, noteEntity.getTelefono());
        values.put(DatabaseHelper.COL_CORREO, noteEntity.getCorreo());

        db.insert(DatabaseHelper.TABLE_PERSONA, null, values);
        db.close();
    }*/

    public Persona createPersona(Persona noteEntity){
        this.open();
        ContentValues values  = new ContentValues();
        values.put(DatabaseHelper.COL_NOMBRE, noteEntity.getNombre());
        values.put(DatabaseHelper.COL_TELEFONO, noteEntity.getTelefono());
        values.put(DatabaseHelper.COL_CORREO, noteEntity.getCorreo());
        long insertid = database.insert(DatabaseHelper.TABLE_PERSONA,null,values);
        noteEntity.setId(insertid);
        this.close();
        return noteEntity;

    }

    public Persona readPersona(int id)
    {
        SQLiteDatabase db = openHelper.getReadableDatabase(); //modo lectura
        Cursor cursor = db.query(DatabaseHelper.TABLE_PERSONA,
                new String[]{DatabaseHelper.COL_ID, DatabaseHelper.COL_NOMBRE,
                        DatabaseHelper.COL_TELEFONO, DatabaseHelper.COL_CORREO},
                DatabaseHelper.COL_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
        }

        Persona persona= cursorToPersona(cursor);

        return persona;
    }

    public int updatePersona(Persona noteEntity)
    {
        SQLiteDatabase db = openHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_NOMBRE, noteEntity.getNombre());
        values.put(DatabaseHelper.COL_TELEFONO, noteEntity.getTelefono());
        values.put(DatabaseHelper.COL_CORREO, noteEntity.getCorreo());

        int row =db.update(DatabaseHelper.TABLE_PERSONA,
                values,
                DatabaseHelper.COL_ID+"=?",
                new String[]{String.valueOf(noteEntity.getId())});
        db.close();

        return row;
    }

    public int deletePersona(Persona noteEntity)
    {
        SQLiteDatabase db = openHelper.getWritableDatabase();
        int row= db.delete(DatabaseHelper.TABLE_PERSONA,
                DatabaseHelper.COL_ID+"=?",
                new String[]{String.valueOf(noteEntity.getId())});
        db.close();
        return row;
    }

    /*
     * Operaciones extras
     */
    public List<Persona> getAllPersona()
    {
        List<Persona> lista = new ArrayList<Persona>();
        String sql= "SELECT  * FROM " + DatabaseHelper.TABLE_PERSONA;
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst())
        {
            do{
                lista.add(cursorToPersona(cursor));
            }while (cursor.moveToNext());
        }
        return lista;
    }

    public List<Persona> getAllPersona2() {
        String sql= "SELECT  * FROM " + DatabaseHelper.TABLE_PERSONA;
        Cursor cursor = database.rawQuery(sql, null);
        List<Persona> personas = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                personas.add(cursorToPersona(cursor));
            }
        }
        return personas;
    }

    public int getPersonaCount()
    {
        String sql= "SELECT * FROM "+ DatabaseHelper.TABLE_PERSONA;
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.close();
        return cursor.getCount();
    }

    /*
     * Metodos de ayuda
     */
    private Persona cursorToPersona(Cursor cursor) {
        Persona persona =new Persona();
        persona.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID)));
        persona.setNombre(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NOMBRE)));
        persona.setTelefono(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TELEFONO)));
        persona.setCorreo(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_CORREO)));
        return persona;
    }


}
