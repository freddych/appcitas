package com.example.retrofitcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.retrofitcrud.adapter.CitaAdapter;
import com.example.retrofitcrud.model.Citas;
import com.example.retrofitcrud.model.User;
import com.example.retrofitcrud.remote.APIUtils;
import com.example.retrofitcrud.remote.CitasService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CitasMainActivity extends AppCompatActivity {
    List<Citas> lstdata = new ArrayList<Citas>();
    CitaAdapter adaptador = null;
    ListView listView = null;
    Button btnAddCita;

    CitasService api =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_main);

        listView  = findViewById(R.id.lstCitas);
        adaptador = new CitaAdapter(this,R.layout.activity_list_citas_item, lstdata);
        listView.setAdapter(adaptador);
        btnAddCita = (Button) findViewById(R.id.btnAddCita);

        api = APIUtils.getCitaService();

        lista();

        btnAddCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mensaje("clic en agregar cita");
                Intent intent = new Intent(CitasMainActivity.this,CitasActivity.class);
                intent.putExtra("user_name","");

                startActivity(intent);
            }
        });

    }

    private void lista(){

        Call<List<Citas>> call = api.getCitas();
        call.enqueue(new Callback<List<Citas>>() {
            @Override
            public void onResponse(Call<List<Citas>> call, Response<List<Citas>> response) {
                if(response.isSuccessful()){
                    List<Citas> cita = response.body();
                    lstdata.clear();
                    lstdata.addAll(cita);
                    adaptador.notifyDataSetChanged();
                    mensaje("Se cargó lista principal");
                }else
                {
                    mensaje("Error-> " + "Error en la respuesta");
                }

            }

            @Override
            public void onFailure(Call<List<Citas>> call, Throwable t) {
                mensaje("Error-> " + "Error en la respuesta");
            }
        });
    }

    private void mensaje(String msg){
        Toast toast1 = Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG);
        toast1.show();
    }
}