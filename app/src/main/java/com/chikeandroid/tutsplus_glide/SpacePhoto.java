package com.chikeandroid.tutsplus_glide;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Chike on 2/11/2017.
 */

public class SpacePhoto implements Parcelable {

    private String mUrl;
    private String mTitle;

    public SpacePhoto(String url, String title) {
        mUrl = url;
        mTitle = title;
    }

    protected SpacePhoto(Parcel in) {
        mUrl = in.readString();
        mTitle = in.readString();
    }

    public static final Creator<SpacePhoto> CREATOR = new Creator<SpacePhoto>() {
        @Override
        public SpacePhoto createFromParcel(Parcel in) {
            return new SpacePhoto(in);
        }

        @Override
        public SpacePhoto[] newArray(int size) {
            return new SpacePhoto[size];
        }
    };

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public static  SpacePhoto[] getSpacePhotos() {

        return new SpacePhoto[]{





                new SpacePhoto("https://i.imgur.com/VMTzaqt.jpg","image"),
                new SpacePhoto("https://i.imgur.com/R8mm9Cz.jpg","image"),



                new SpacePhoto("https://i.imgur.com/wxnx3qZ.jpg","image"),
                new SpacePhoto("https://i.imgur.com/mhK6NzM.jpg","image"),
                new SpacePhoto("https://i.imgur.com/9NRbMgT.jpg","image"),
                new SpacePhoto("https://i.imgur.com/xU5wov5.jpg","image"),
                new SpacePhoto("https://i.imgur.com/0V343Xt.jpg","image"),
                new SpacePhoto("https://i.imgur.com/x1XzsQu.jpg","image"),
                new SpacePhoto("https://i.imgur.com/9Sn3cKy.jpg","image"),
                new SpacePhoto("https://i.imgur.com/hI36per.jpg","image"),

                new SpacePhoto("https://i.imgur.com/xjbhdKo.jpg","image"),
                new SpacePhoto("https://i.imgur.com/Lz2zkEA.jpg","image"),
                new SpacePhoto("https://i.imgur.com/nuGoFRF.jpg","image"),

                new SpacePhoto("https://i.imgur.com/FBspyId.jpg","image"),
                new SpacePhoto("https://i.imgur.com/xX5EgRJ.jpg","image"),
                new SpacePhoto("https://i.imgur.com/SCff5r0.jpg","image"),
                new SpacePhoto("https://i.imgur.com/gx27W8K.jpg","image"),
                new SpacePhoto("https://i.imgur.com/RGFOKuK.jpg","image"),
                new SpacePhoto("https://i.imgur.com/eTwvXcC.jpg","image"),
                new SpacePhoto("https://i.imgur.com/Z9g8aVU.jpg","image"),

                new SpacePhoto("https://i.imgur.com/MWVCMdU.jpg","image"),

                new SpacePhoto("https://i.imgur.com/ewvpIaM.jpg","image"),
                new SpacePhoto("https://i.imgur.com/iUlCodi.jpg","image"),
                new SpacePhoto("https://i.imgur.com/CeyXbZx.jpg","image"),
                new SpacePhoto("https://i.imgur.com/8DZQUkZ.jpg","image"),
                new SpacePhoto("https://i.imgur.com/a2axp2q.jpg","image"),
                new SpacePhoto("https://i.imgur.com/n24E1CY.jpg","image"),
                new SpacePhoto("https://i.imgur.com/efVfyVf.jpg","image"),









                new SpacePhoto("https://i.imgur.com/ghF0bfy.jpg","image"),

                new SpacePhoto("https://i.imgur.com/OizGPCW.jpg","image"),
                new SpacePhoto("https://i.imgur.com/Hjg73f2.jpg","image"),

                new SpacePhoto("https://i.imgur.com/r51zRbM.jpg","image"),
                new SpacePhoto("https://i.imgur.com/cMU99HW.jpg","image"),

                new SpacePhoto("https://i.imgur.com/CDeyiJ7.jpg","image"),
                new SpacePhoto("https://i.imgur.com/gtmK3Ha.jpg","image"),
                new SpacePhoto("https://i.imgur.com/5sQnyMW.jpg","image"),
                new SpacePhoto("https://i.imgur.com/BqzznpB.jpg","image"),

              
                new SpacePhoto("https://i.imgur.com/g3ijvCy.jpg","image"),
                new SpacePhoto("https://i.imgur.com/tZt8QMb.jpg","image"),

                new SpacePhoto("https://i.imgur.com/aVaLzhC.jpg","image"),
                new SpacePhoto("https://i.imgur.com/hBA7U5w.png","image"),
                new SpacePhoto("https://i.imgur.com/pGvXOq3.png","image"),




        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mUrl);
        parcel.writeString(mTitle);
    }
}
