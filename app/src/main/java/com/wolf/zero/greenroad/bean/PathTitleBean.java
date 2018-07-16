package com.wolf.zero.greenroad.bean;

/**
 * Created by Administrator on 2017/8/23.
 */

public class PathTitleBean {
    String photo_type;
    String path;
    String title;

    public PathTitleBean(String photo_type, String path, String title) {
        this.photo_type = photo_type;
        this.path = path;
        this.title = title;
    }

    public PathTitleBean() {
    }

    @Override
    public String toString() {
        return "PathTitleBean{" +
                "photo_type='" + photo_type + '\'' +
                ", path='" + path + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public String getPhoto_type() {
        return photo_type;
    }

    public void setPhoto_type(String photo_type) {
        this.photo_type = photo_type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
