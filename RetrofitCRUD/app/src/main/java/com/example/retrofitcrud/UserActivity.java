package com.example.retrofitcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitcrud.model.User;
import com.example.retrofitcrud.remote.APIUtils;
import com.example.retrofitcrud.remote.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    UserService userService;
    TextView txtUId;
    EditText edtUserId;
    EditText edApenom;
    Spinner spTipodoc;
    EditText edDocumento;
    EditText edEmail;
    Spinner spPerfil;
    EditText edUsuario;
    EditText edPassword;
    Button btnSave;
    Button btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        //Cargar tipo documento
        spTipodoc = (Spinner) findViewById(R.id.s_tipodoc);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.listatipodocumento, android.R.layout.simple_spinner_item);
        spTipodoc.setAdapter(adapter);

        //Cargar Perfil
        spPerfil = (Spinner) findViewById(R.id.sperfil);
        ArrayAdapter<CharSequence> adapterP = ArrayAdapter.createFromResource(this, R.array.listaperfil, android.R.layout.simple_spinner_item);
        spPerfil.setAdapter(adapterP);

        txtUId = (TextView) findViewById(R.id.txtUId);
        edtUserId = (EditText) findViewById(R.id.edtUserId);
        edApenom = (EditText) findViewById(R.id.txtnomape);
        spTipodoc = (Spinner) findViewById(R.id.s_tipodoc);
        edDocumento = (EditText) findViewById(R.id.txtDNI);
        edEmail = (EditText) findViewById(R.id.txtemail);
        spPerfil = (Spinner) findViewById(R.id.sperfil);
        edUsuario = (EditText) findViewById(R.id.txtuser);
        edPassword = (EditText) findViewById(R.id.txtpass);
        btnSave = (Button) findViewById(R.id.btnregistrar);
        btnBack = (Button) findViewById(R.id.btnback);

        userService = APIUtils.getUserService();

        //Se agrega lo que viene de userAdapter(Valores que manda)
        Bundle extras = getIntent().getExtras();
        String userId = extras.getString("user_userid");
        String Apenom = extras.getString("user_apenom");
        String TipoDoc = extras.getString("user_tipodoc");
        String documento = extras.getString("user_documento");
        String Email = extras.getString("user_email");
        String Usuario = extras.getString("user_usuario");
        String Perfil = extras.getString("user_perfil");
        String Pass = extras.getString("user_pass");

        edtUserId.setText(userId);
        edApenom.setText(Apenom);
        spTipodoc.setSelection(1); // Requiere posicion del elemento
        edDocumento.setText(documento);
        edEmail.setText(Email);
        spPerfil.setSelection(1); // requiere posicion del elemento
        edUsuario.setText(Usuario);
        edPassword.setText(Pass);

        if (userId != null && userId.trim().length() > 0) {
            edtUserId.setFocusable(false);
        } else {
            txtUId.setVisibility(View.INVISIBLE);
            edtUserId.setVisibility(View.INVISIBLE);

        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User u = new User();
                u.setApenom(edApenom.getText().toString());
                u.setTipodoc(spTipodoc.getSelectedItem().toString());
                u.setDocumento(edDocumento.getText().toString());
                u.setEmail(edEmail.getText().toString());
                u.setPerfil(spPerfil.getSelectedItem().toString());
                u.setUsuario(edUsuario.getText().toString());
                u.setPassword(edPassword.getText().toString());


                if (userId != null && userId.trim().length() > 0) {
                    //update User
                    Toast.makeText(UserActivity.this, "entrando a update", Toast.LENGTH_SHORT).show();
                    updateUser(userId, u);

                } else {
                    //Add user
                    Toast.makeText(UserActivity.this, "entrando a Insert", Toast.LENGTH_SHORT).show();
                    addUser(u);
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(UserActivity.this, UsuariosMainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void addUser(User u){
        Call<User> call = userService.addUser(u);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(UserActivity.this, "Usuario creado satisfactoriamente", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UserActivity.this, "No se pudo hacer insert", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Error: ", t.getMessage());
            }
        });
    }

    public void updateUser(String id, User u){
        Call<User> call = userService.updateUser(id, u);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(UserActivity.this, "Usuario Actualizado satisfactoriamente", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UserActivity.this, "no se pudo hacer pdate", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Error: ", t.getMessage());
            }
        });


    }

    public void deleteUser(String id){
        Call<User> call = userService.deleteUser(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(UserActivity.this, "Usuario eliminado satisfactoriamente", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Error: ", t.getMessage());
            }
        });
    }
}