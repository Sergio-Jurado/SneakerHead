package com.example.sneakerhead.entidades;

public class Sneakers {
    private int id;
    private String modelo;
    private String marca;
    private String talla;
    private String precio;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {return marca;}
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTalla() {
        return talla;
    }
    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getPrecio() {
        return precio;
    }
    public void setPrecio(String precio) {this.precio = precio;}
}
