package com.example.retrofitcrud.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.example.retrofitcrud.CitasActivity;
import com.example.retrofitcrud.R;
import com.example.retrofitcrud.model.Citas;



import java.util.List;

public class CitaAdapter extends ArrayAdapter<Citas> {
    private Context context;
    private List<Citas> citas;

    public CitaAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Citas> objects) {
        super(context, resource, objects);
        this.context = context;
        this.citas = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_list_citas_item, parent, false);

        //Lo que se muestra en el listview personalizado
        TextView txtNombre = (TextView) rowView.findViewById(R.id.itemListaCitasNombre);
        TextView txtFecha = (TextView) rowView.findViewById(R.id.txtFecha);
        TextView txtHora = (TextView) rowView.findViewById(R.id.txtHora);

        txtNombre.setText(citas.get(pos).getNombrepaciente());
        txtFecha.setText(citas.get(pos).getFecha());
        txtHora.setText(citas.get(pos).getHora());

        //Al dar clic en un elemento del listview, se abre la ventana de Formulario (CitasActivity)
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity Cita Form
                Intent intent = new Intent(context, CitasActivity.class);
                intent.putExtra("cita_citaid", String.valueOf(citas.get(pos).getId()));
                intent.putExtra("cita_fecha", String.valueOf(citas.get(pos).getFecha()));
                intent.putExtra("cita_hora", citas.get(pos).getHora());
                intent.putExtra("cita_idusuario", citas.get(pos).getIdusuario());
                intent.putExtra("cita_nombrepaciente", citas.get(pos).getNombrepaciente());
                intent.putExtra("cita_comentario", citas.get(pos).getComentario());
                intent.putExtra("cita_especialidad", citas.get(pos).getEspecialidad());
                intent.putExtra("cita_medico", citas.get(pos).getMedico());
                context.startActivity(intent);
            }
        });
        return rowView;
    }
}
