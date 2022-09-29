package com.example.retrofitcrud.remote;

public class APIUtils {

    private APIUtils(){
    };

    public static final String API_URL = "https://rest-citasmedicas.azurewebsites.net/api/";

    public static UserService getUserService(){
        return RetrofitClient.getClient(API_URL).create(UserService.class);
    }

    public static CitasService getCitaService(){
        return RetrofitClient.getClient(API_URL).create(CitasService.class);
    }

}
