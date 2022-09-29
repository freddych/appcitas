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

import com.example.retrofitcrud.R;
import com.example.retrofitcrud.UserActivity;
import com.example.retrofitcrud.model.User;
import java.util.List;

public class UsuarioAdapter extends ArrayAdapter<User> {
    private Context context;
    private List<User> users;

    public UsuarioAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<User> objects) {
        super(context, resource, objects);
        this.context = context;
        this.users = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_list_user_item, parent, false);

        //Lo que se muestra en el listview personalizado
        //TextView txtUserId = (TextView) rowView.findViewById(R.id.iddemo);
        TextView txtUsername = (TextView) rowView.findViewById(R.id.itemListaUserNombre);
        TextView txtTipoDoc = (TextView) rowView.findViewById(R.id.txttipodoc);
        TextView txtDoc = (TextView) rowView.findViewById(R.id.txtdoc);

        txtUsername.setText(users.get(pos).getApenom());
        txtTipoDoc.setText(users.get(pos).getTipodoc());
        txtDoc.setText(users.get(pos).getDocumento());

        //Al dar clic en un elemento del listview, se abre la ventana de Formulario (UserActivity)
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity User Form
                Intent intent = new Intent(context, UserActivity.class);
                intent.putExtra("user_userid", String.valueOf(users.get(pos).getId()));
                intent.putExtra("user_apenom", String.valueOf(users.get(pos).getApenom()));
                intent.putExtra("user_tipodoc", users.get(pos).getTipodoc());
                intent.putExtra("user_documento", users.get(pos).getDocumento());
                intent.putExtra("user_email", users.get(pos).getEmail());
                intent.putExtra("user_perfil", users.get(pos).getPerfil());
                intent.putExtra("user_usuario", users.get(pos).getUsuario());
                intent.putExtra("user_pass", users.get(pos).getPassword());
                context.startActivity(intent);
            }
        });
        return rowView;
    }
}
