package com.example.retrofitcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitcrud.model.Citas;
import com.example.retrofitcrud.remote.APIUtils;
import com.example.retrofitcrud.remote.CitasService;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CitasActivity extends AppCompatActivity {
EditText txtfecha, txtHora, txtComentario;
TextView tvIdUsuario,tvIdCitas;
EditText txtPaciente;
Calendar calendar;
Spinner spEspecialidad, spMedicos;
Button btnSave, btnBack;

CitasService citasService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas);

        //Asignar variables a  los objetos de activity_citas
        tvIdCitas = (TextView) findViewById(R.id.tvIdCita);
        tvIdUsuario = (TextView) findViewById(R.id.tvIdUsuario);
        txtPaciente = (EditText) findViewById(R.id.edNombrePaciente);
        txtfecha = findViewById(R.id.edFecha);
        txtHora = (EditText) findViewById(R.id.edHora);
        txtComentario = (EditText) findViewById(R.id.edMultiComentario);
        spMedicos = (Spinner) findViewById(R.id.spMedico);
        spEspecialidad = (Spinner) findViewById(R.id.spEspecialidad);
        btnSave = (Button) findViewById(R.id.btnRegistrar);
        btnBack = (Button) findViewById(R.id.btnAtras);


        citasService = APIUtils.getCitaService();

        //Se agrega lo que viene desde CitasAdapter
        Bundle extras = getIntent().getExtras();
        String citaid = extras.getString("cita_citaid");
        String idusuario = extras.getString("cita_idusuario");
        String fecha = extras.getString("cita_fecha");
        String hora = extras.getString("cita_hora");
        String nombrepaciente = extras.getString("cita_nombrepaciente");
        String comentario = extras.getString("cita_comentario");
        String especialidad = extras.getString("cita_especialidad");
        String medico = extras.getString("cita_medico");

        tvIdCitas.setText(citaid);
        tvIdUsuario.setText(idusuario);
        txtPaciente.setText(nombrepaciente);
        txtfecha.setText(fecha);
        txtHora.setText(hora);
        txtComentario.setText(comentario);
        spEspecialidad.setSelection(1); //Temporal, cambiar luego
        spMedicos.setSelection(1); //Temporal, cambiar luego



        //Cargar Especialidad
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lista_especialidades, android.R.layout.simple_spinner_item);
        spEspecialidad.setAdapter(adapter);

        //Cargar Perfil
        ArrayAdapter<CharSequence> adapterP = ArrayAdapter.createFromResource(this, R.array.lista_medicos, android.R.layout.simple_spinner_item);
        spMedicos.setAdapter(adapterP);

        //agregar fecha

        Calendar calendar  = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateCalendar();
            }

            private void updateCalendar(){
                String Format ="dd/MM/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(Format, Locale.US);

                txtfecha.setText(sdf.format(calendar.getTime()));
            }
        };
        txtfecha.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new DatePickerDialog(CitasActivity.this,date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        if (citaid != null && citaid.trim().length() > 0) {
           tvIdCitas.setFocusable(false);
        } else {
            tvIdCitas.setVisibility(View.INVISIBLE);
            tvIdUsuario.setVisibility(View.INVISIBLE);

        }

        //Registrar agenda
        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Citas c = new Citas();
                c.setIdusuario(tvIdUsuario.getText().toString());
                c.setNombrepaciente(txtPaciente.getText().toString());
                c.setFecha(txtfecha.getText().toString());
                c.setHora(txtHora.getText().toString());
                c.setEspecialidad(spEspecialidad.getSelectedItem().toString());
                c.setMedico(spMedicos.getSelectedItem().toString());
                c.setComentario(txtComentario.getText().toString());

                //Validar si es Registrar o actualizar
                if (citaid != null && citaid.trim().length() > 0) {
                    //update User
                    Toast.makeText(CitasActivity.this, "entrando a update", Toast.LENGTH_SHORT).show();
                    updateCita(citaid, c);

                } else {
                    //Add user
                    Toast.makeText(CitasActivity.this, "entrando a Insert", Toast.LENGTH_SHORT).show();
                    addCitas(c);
                }
            }
        });

        //Boton atras
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CitasActivity.this, CitasMainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addCitas(Citas c){
        Call<Citas> call = citasService.addCita(c);
        call.enqueue(new Callback<Citas>() {
            @Override
            public void onResponse(Call<Citas> call, Response<Citas> response) {
                if(response.isSuccessful()){
                    Toast.makeText(CitasActivity.this, "Cita médica creada satisfactoriamente", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CitasActivity.this, "Error al actualizar cita", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Citas> call, Throwable t) {
                Toast.makeText(CitasActivity.this, "Error al isnsertar datos", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void updateCita(String id, Citas u){
        Call<Citas> call = citasService.updateCita(id, u);
        call.enqueue(new Callback<Citas>() {
            @Override
            public void onResponse(Call<Citas> call, Response<Citas> response) {
                if(response.isSuccessful()){
                    Toast.makeText(CitasActivity.this, "Cita Actualizado satisfactoriamente", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CitasActivity.this, "no se pudo hacer update", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Citas> call, Throwable t) {
                Log.e("Error: ", t.getMessage());
            }
        });


    }

}