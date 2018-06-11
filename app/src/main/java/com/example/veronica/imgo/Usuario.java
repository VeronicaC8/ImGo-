package com.example.veronica.imgo;

public class Usuario {
    private int idUsuario;
    private int idRol;
    private String userName;
    private String password;

    public Usuario() {
    }

    public Usuario(int idUsuario, int idRol, String userName, String password) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
        this.userName = userName;
        this.password = password;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
