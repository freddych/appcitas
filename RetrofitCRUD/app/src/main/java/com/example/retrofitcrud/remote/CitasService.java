package com.example.retrofitcrud.remote;
import com.example.retrofitcrud.model.Citas;


import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
public interface CitasService {
    @GET("Citas/")
    Call<List<Citas>> getCitas();

    @POST("Citas/")
    Call<Citas> addCita(@Body Citas citas);

    @PUT("Citas/{id}")
    Call<Citas> updateCita(@Path("id") String id, @Body Citas citas);

    @DELETE("Citas/{id}")
    Call<Citas> deleteCita(@Path("id") String id);
}
