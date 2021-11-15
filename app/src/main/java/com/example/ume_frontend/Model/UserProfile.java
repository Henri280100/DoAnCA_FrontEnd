package com.example.ume_frontend.Model;

public class UserProfile {
    private String urlAvarta;
    private String idUser;

    public UserProfile(String urlAvarta, String idUser) {
        this.urlAvarta = urlAvarta;
        this.idUser = idUser;
    }

    public String getUrlAvarta() {
        return urlAvarta;
    }

    public void setUrlAvarta(String urlAvarta) {
        this.urlAvarta = urlAvarta;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
