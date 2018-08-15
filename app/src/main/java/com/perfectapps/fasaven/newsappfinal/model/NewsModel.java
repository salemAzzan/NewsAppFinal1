package com.perfectapps.fasaven.newsappfinal.model;

/**
 * Created by Salem on 05/06/2018.
 */

public class NewsModel {
    String image;
    String title;
    String infos;

    public NewsModel(String image) {

        this.image = image;
    }

    public NewsModel() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }
}
