package com.software.nafl.sqlite_test.Modelos;

/**
 * Created by Neury on 3/23/2017.
 */

public class Vehiculo {


    private int id;
    private String compania_2403;
    private String ficha_2403;
    private Integer fecha_2403;
    private String latitud_2403;
    private String longitud_2403;
    private String creado_por_2403;

    public Vehiculo(){

    }


    public Vehiculo(String pCompania_2403, String pFicha_2403, Integer pFecha_2403, String pLatitud_2403, String pLongitud_2403, String pCreado_por_2403){

        this.setCompania_2403(pCompania_2403);
        this.setFicha_2403(pFicha_2403);
        this.setFecha_2403(pFecha_2403);
        this.setLatitud_2403(pLatitud_2403);
        this.setLongitud_2403(pLongitud_2403);
        this.setCreado_por_2403(pCreado_por_2403);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Integer getFecha_2403() {
        return fecha_2403;
    }

    public void setFecha_2403(Integer fecha_2403) {
        this.fecha_2403 = fecha_2403;
    }
    public String getFicha_2403() {
        return ficha_2403;
    }

    public void setFicha_2403(String ficha_2403) {
        this.ficha_2403 = ficha_2403;
    }

    public String getCompania_2403() {
        return compania_2403;
    }

    public void setCompania_2403(String compania_2403) {
        this.compania_2403 = compania_2403;
    }

    public String getLatitud_2403() {
        return latitud_2403;
    }

    public void setLatitud_2403(String latitud_2403) {
        this.latitud_2403 = latitud_2403;
    }

    public String getLongitud_2403() {
        return longitud_2403;
    }

    public void setLongitud_2403(String longitud_2403) {
        this.longitud_2403 = longitud_2403;
    }

    public String getCreado_por_2403() {
        return creado_por_2403;
    }

    public void setCreado_por_2403(String creado_por_2403) {
        this.creado_por_2403 = creado_por_2403;
    }
}
