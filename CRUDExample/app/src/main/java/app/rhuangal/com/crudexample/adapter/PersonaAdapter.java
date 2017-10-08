package app.rhuangal.com.crudexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import app.rhuangal.com.crudexample.R;
import app.rhuangal.com.crudexample.model.Persona;

/**
 * Created by rober on 06-Oct-17.
 */

public class PersonaAdapter  extends BaseAdapter {

    private Context context;
    private List<Persona> listaPersona;

    public PersonaAdapter(Context context, List<Persona> listaPersona) {
        this.context = context;
        this.listaPersona = listaPersona;
    }

    @Override
    public int getCount() {
            return listaPersona.size();
    }

    @Override
    public Object getItem(int position) {
        return listaPersona.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.row_persona, null);
            ViewHolder holder = new ViewHolder();
            holder.tvNombre = (TextView)v.findViewById(R.id.tvNombre);
            holder.tvTelefono = (TextView)v.findViewById(R.id.tvTelefono);
            holder.tvCorreo = (TextView)v.findViewById(R.id.tvCorreo);
            v.setTag(holder);
        }
        Persona persona = listaPersona.get(position);
        if(persona != null) {
            ViewHolder holder = (ViewHolder)v.getTag();
            holder.tvNombre.setText(persona.getNombre());
            holder.tvTelefono.setText(persona.getTelefono());
            holder.tvCorreo.setText(persona.getCorreo());
        }

        return v;
    }

    private class ViewHolder {
        TextView tvNombre;
        TextView tvTelefono;
        TextView tvCorreo;
    }
}
