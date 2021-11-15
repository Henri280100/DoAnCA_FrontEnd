package com.example.ume_frontend.Model;

public class Upload {
    private String imageUrl;

    public Upload(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public Upload(){}

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
