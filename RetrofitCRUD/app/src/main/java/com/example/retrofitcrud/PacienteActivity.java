package com.example.retrofitcrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.retrofitcrud.adapter.PacienteAdapter;
import com.example.retrofitcrud.model.User;
import com.example.retrofitcrud.remote.APIUtils;
import com.example.retrofitcrud.remote.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PacienteActivity extends AppCompatActivity {

    private List<User> usuarioList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PacienteAdapter mAdapter;

    UserService api = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);

        recyclerView = (RecyclerView) findViewById(R.id.listRecyclerView);

        mAdapter = new PacienteAdapter(usuarioList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());

        //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        api = APIUtils.getUserService();

        lista();
    }

    private void lista() {
        Call<List<User>> call = api.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    List<User> user = response.body();
                    usuarioList.clear();
                    usuarioList.addAll(user);
                    mAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }
}