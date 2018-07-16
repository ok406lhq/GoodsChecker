package com.wolf.zero.greenroad.fragment;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 丁瑞 on 2017/4/13.
 */

public class MyBitmap implements Parcelable{
    String path;
    Bitmap bm;
    String title;

    public MyBitmap(Bitmap bm) {
        this.bm = bm;

    }
    public MyBitmap(Bitmap bm,String title) {
        this.bm = bm;
        this.title = title;

    }

    public MyBitmap(String path, Bitmap bm,String title) {
        this.path = path;
        this.bm = bm;
        this.title = title;
//
//        E/setsize: getContentProvider: /storage/emulated/0/DCIM/Camera/IMG_20170413_165655.jpg
//        E/setsize: getContentProvider: /storage/emulated/0/DCIM/Camera/IMG_20170413_165652.jpg
//        E/setsize: getContentProvider: /storage/emulated/0/DCIM/Camera/IMG_20170413_165641.jpg
//        E/setsize: getContentProvider: /storage/emulated/0/DCIM/Camera/IMG_20170413_165647.jpg
//        E/setsize: getContentProvider: /storage/emulated/0/DCIM/Camera/IMG_20170413_165643.jpg
//        E/setsize: getContentProvider: /storage/emulated/0/DCIM/Camera/IMG_20170413_165645.jpg
//        E/setsize: getContentProvider: /storage/emulated/0/DCIM/Camera/IMG_20170413_165650.jpg
//        E/setsize: getContentProvider: /storage/emulated/0/DCIM/Camera/IMG_20170413_165659.jpg
    }

    protected MyBitmap(Parcel in) {
        path = in.readString();
        bm = in.readParcelable(Bitmap.class.getClassLoader());
        title = in.readString();
    }

    public static final Creator<MyBitmap> CREATOR = new Creator<MyBitmap>() {
        @Override
        public MyBitmap createFromParcel(Parcel in) {
            return new MyBitmap(in);
        }

        @Override
        public MyBitmap[] newArray(int size) {
            return new MyBitmap[size];
        }
    };

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Bitmap getBm() {
        return bm;
    }

    public void setBm(Bitmap bm) {
        this.bm = bm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(path);
        dest.writeParcelable(bm, flags);
        dest.writeString(title);
    }

    @Override
    public String toString() {
        return "MyBitmap{" +
                "path='" + path + '\'' +
                ", bm=" + bm +
                ", title='" + title + '\'' +
                '}';
    }
}
