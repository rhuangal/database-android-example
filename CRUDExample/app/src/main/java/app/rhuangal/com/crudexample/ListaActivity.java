package app.rhuangal.com.crudexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import app.rhuangal.com.crudexample.adapter.PersonaAdapter;
import app.rhuangal.com.crudexample.model.Persona;
import app.rhuangal.com.crudexample.storage.DatabaseHelper;
import app.rhuangal.com.crudexample.storage.OperationsDatabase;

public class ListaActivity extends AppCompatActivity {

    private static final String TAG = "ListaActivity" ;

    //Elementos
    private ListView lvPersonas;

    //Lista
    private List<Persona> listaPersona;

    //Base de datos
    private OperationsDatabase operations;
    private PersonaAdapter personaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        init();
        loadData();
    }

    private void init() {
        lvPersonas= (ListView)(findViewById(R.id.lvPersonas));
        lvPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Persona persona = (Persona) adapterView.getAdapter().getItem(i);
            }
        });
    }

    private void loadData() {
        operations= new OperationsDatabase(DatabaseHelper.getInstance(this));
        listaPersona = operations.getAllPersona();
        personaAdapter=  new PersonaAdapter(this,listaPersona);
        lvPersonas.setAdapter(personaAdapter);
        if(listaPersona.size() == 0){
            populate();
        }
    }

    private void populate() {

        OperationsDatabase operations = new OperationsDatabase(DatabaseHelper.getInstance(this));
        operations.createPersona(new Persona("Carlos Ruiz","963-452-214 ","cruiz@correo.com"));
        operations.createPersona(new Persona("Maria Perez","988-452-756 ","mperez@correo.com"));
        operations.createPersona(new Persona("Sergio Cespedes","955-637-812 ","scespedes@correo.com"));
        operations.createPersona(new Persona("Jhon Smith","951-248-636 ","jsmith@correo.com"));
        Log.i(TAG, "populate " + operations.getAllPersona());
    }
}
