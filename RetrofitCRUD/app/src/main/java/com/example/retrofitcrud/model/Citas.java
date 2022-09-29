package com.example.retrofitcrud.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Citas {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("fecha")
    @Expose
    private String fecha;
    @SerializedName("hora")
    @Expose
    private String hora;
    @SerializedName("idusuario")
    @Expose
    private String idusuario;
    @SerializedName("nombrepaciente")
    @Expose
    private String nombrepaciente;
    @SerializedName("comentario")
    @Expose
    private String comentario;
    @SerializedName("especialidad")
    @Expose
    private String especialidad;
    @SerializedName("medico")
    @Expose
    private String medico;

    public Citas(){
    }
    public Citas(String id, String fecha, String hora,String idusuario, String nombrepaciente,String comentario,String especialidad, String medico){
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.idusuario = idusuario;
        this.nombrepaciente=nombrepaciente;
        this.comentario=comentario;
        this.especialidad=especialidad;
        this.medico=medico;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombrepaciente() {
        return nombrepaciente;
    }

    public void setNombrepaciente(String nombrepaciente) {
        this.nombrepaciente = nombrepaciente;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }
}
