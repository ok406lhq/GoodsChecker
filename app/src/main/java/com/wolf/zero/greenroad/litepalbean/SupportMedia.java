package com.wolf.zero.greenroad.litepalbean;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2017/8/23.
 */

public class SupportMedia extends DataSupport implements Parcelable {
    private int lite_ID;

    private List<String> pictureTypes;
    private List<String> paths;

    private  List<Long> mDurations;
    private  List<Integer> nums;
    private  List<Integer> mimeTypes;
    private  List<Integer> widths;
    private  List<Integer> heights;
    private  List<Integer> positions;

    public SupportMedia() {
    }

    protected SupportMedia(Parcel in) {
        lite_ID = in.readInt();
        pictureTypes = in.createStringArrayList();
        paths = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(lite_ID);
        dest.writeStringList(pictureTypes);
        dest.writeStringList(paths);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SupportMedia> CREATOR = new Creator<SupportMedia>() {
        @Override
        public SupportMedia createFromParcel(Parcel in) {
            return new SupportMedia(in);
        }

        @Override
        public SupportMedia[] newArray(int size) {
            return new SupportMedia[size];
        }
    };

    @Override
    public String toString() {
        return "SupportMedia{" +
                "lite_ID=" + lite_ID +
                ", pictureTypes=" + pictureTypes +
                ", paths=" + paths +
                ", mDurations=" + mDurations +
                ", nums=" + nums +
                ", mimeTypes=" + mimeTypes +
                ", widths=" + widths +
                ", heights=" + heights +
                ", positions=" + positions +
                '}';
    }

    public int getLite_ID() {
        return lite_ID;
    }

    public void setLite_ID(int lite_ID) {
        this.lite_ID = lite_ID;
    }

    public List<String> getPictureTypes() {
        return pictureTypes;
    }

    public void setPictureTypes(List<String> pictureTypes) {
        this.pictureTypes = pictureTypes;
    }

    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }

    public List<Long> getDurations() {
        return mDurations;
    }

    public void setDurations(List<Long> durations) {
        mDurations = durations;
    }

    public List<Integer> getNums() {
        return nums;
    }

    public void setNums(List<Integer> nums) {
        this.nums = nums;
    }

    public List<Integer> getMimeTypes() {
        return mimeTypes;
    }

    public void setMimeTypes(List<Integer> mimeTypes) {
        this.mimeTypes = mimeTypes;
    }

    public List<Integer> getWidths() {
        return widths;
    }

    public void setWidths(List<Integer> widths) {
        this.widths = widths;
    }

    public List<Integer> getHeights() {
        return heights;
    }

    public void setHeights(List<Integer> heights) {
        this.heights = heights;
    }

    public List<Integer> getPositions() {
        return positions;
    }

    public void setPositions(List<Integer> positions) {
        this.positions = positions;
    }
}
