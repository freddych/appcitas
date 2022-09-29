package com.example.retrofitcrud.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("apenom")
    @Expose
    private String apenom;

    @SerializedName("tipodoc")
    @Expose
    private String tipodoc;

    @SerializedName("documento")
    @Expose
    private String documento;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("perfil")
    @Expose
    private String perfil;

    @SerializedName("usuario")
    @Expose
    private String usuario;

    @SerializedName("password")
    @Expose
    private String password;

    public User(){
    }

    public User(String id,String apenom, String tipodoc, String documento, String email, String perfil, String usuario, String password){
        this.id = id;
        this.apenom = apenom;
        this.tipodoc = tipodoc;
        this.documento = documento;
        this.email = email;
        this.perfil = perfil;
        this.usuario = usuario;
        this.password = password;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getApenom() {
        return apenom;
    }

    public void setApenom(String apenom) {
        this.apenom = apenom;
    }

    public String getTipodoc() {
        return tipodoc;
    }

    public void setTipodoc(String tipodoc) {
        this.tipodoc = tipodoc;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
