package com.example.retrofitcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.retrofitcrud.adapter.UsuarioAdapter;
import com.example.retrofitcrud.model.User;
import com.example.retrofitcrud.remote.APIUtils;
import com.example.retrofitcrud.remote.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UsuariosMainActivity extends AppCompatActivity {
    List<User> lstdata = new ArrayList<User>();
    UsuarioAdapter adaptador = null;
    ListView listView = null;
    Button btnAddUser;

    UserService api =null;
    /*
    ArrayList<String> usuarios = new ArrayList<String>();
    ListView lstUsu = null;
    ArrayAdapter adaptador = null;

    UserService userService;
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios_main);

        listView  = findViewById(R.id.lstUsuarios);
        adaptador = new UsuarioAdapter(this,R.layout.activity_list_user_item, lstdata);
        listView.setAdapter(adaptador);
        btnAddUser = (Button) findViewById(R.id.btnAddUser);

        api = APIUtils.getUserService();

        lista();

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsuariosMainActivity.this,UserActivity.class);
                intent.putExtra("user_name","");

                startActivity(intent);
            }
        });
    }

    private void lista(){

        Call<List<User>> call = api.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    List<User> user = response.body();
                    lstdata.clear();
                    lstdata.addAll(user);
                    adaptador.notifyDataSetChanged();
                }else
                {
                    mensaje("Error-> " + "Error en la respuesta carga inicial");
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                mensaje("Error-> " + "Error en la respuesta");
            }
        });
    }

    private void mensaje(String msg){
        Toast toast1 = Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG);
        toast1.show();
    }
}

/*
        lstUsu = (ListView) findViewById(R.id.lstUsuarios);
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, usuarios);
        lstUsu.setAdapter(adaptador);

        cargarUsuarios();

    }

    public void cargarUsuarios() {
        //UserService userService = APIUtils.getUserService()
        userService = APIUtils.getUserService();
        Call<List<User>> call = userService.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> lista = response.body();
                for (User x : lista) {
                    usuarios.add(x.getPerfil() + " - " + x.getApenom()  );
                }
                adaptador.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
            }
        });
    }
}
*/