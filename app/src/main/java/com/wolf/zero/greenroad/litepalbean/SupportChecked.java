package com.wolf.zero.greenroad.litepalbean;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2017/8/24.
 */

public class SupportChecked extends DataSupport implements Parcelable{

    private int lite_ID;


    private List<String> siteChecks;
    private String siteLogin;

    private int isRoom;
    private int isFree;
    private String conclusion;
    private String description;

    public SupportChecked() {
    }

    protected SupportChecked(Parcel in) {
        lite_ID = in.readInt();
        siteChecks = in.createStringArrayList();
        siteLogin = in.readString();
        isRoom = in.readInt();
        isFree = in.readInt();
        conclusion = in.readString();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(lite_ID);
        dest.writeStringList(siteChecks);
        dest.writeString(siteLogin);
        dest.writeInt(isRoom);
        dest.writeInt(isFree);
        dest.writeString(conclusion);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SupportChecked> CREATOR = new Creator<SupportChecked>() {
        @Override
        public SupportChecked createFromParcel(Parcel in) {
            return new SupportChecked(in);
        }

        @Override
        public SupportChecked[] newArray(int size) {
            return new SupportChecked[size];
        }
    };

    @Override
    public String toString() {
        return "SupportChecked{" +
                "lite_ID=" + lite_ID +
                ", siteChecks=" + siteChecks +
                ", siteLogin='" + siteLogin + '\'' +
                ", isRoom=" + isRoom +
                ", isFree=" + isFree +
                ", conclusion='" + conclusion + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public int getLite_ID() {
        return lite_ID;
    }

    public void setLite_ID(int lite_ID) {
        this.lite_ID = lite_ID;
    }

    public List<String> getSiteChecks() {
        return siteChecks;
    }

    public void setSiteChecks(List<String> siteChecks) {
        this.siteChecks = siteChecks;
    }

    public String getSiteLogin() {
        return siteLogin;
    }

    public void setSiteLogin(String siteLogin) {
        this.siteLogin = siteLogin;
    }

    public int getIsRoom() {
        return isRoom;
    }

    public void setIsRoom(int isRoom) {
        this.isRoom = isRoom;
    }

    public int getIsFree() {
        return isFree;
    }

    public void setIsFree(int isFree) {
        this.isFree = isFree;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
