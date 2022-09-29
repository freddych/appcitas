package com.example.retrofitcrud.remote;

import com.example.retrofitcrud.model.User;
import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
    @GET("Usuarios/")
    Call<List<User>> getUsers();

    @POST("Usuarios/")
    Call<User> addUser(@Body User user);

    @PUT("Usuarios/{id}")
    Call<User> updateUser(@Path("id") String id, @Body User user);



    @DELETE("Usuarios/{id}")
    Call<User> deleteUser(@Path("id") String id);
}
