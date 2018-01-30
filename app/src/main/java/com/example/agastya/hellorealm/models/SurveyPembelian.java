package com.example.agastya.hellorealm.models;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by AgastyA on 1/24/2018.
 */

public class SurveyPembelian extends RealmObject implements Parcelable {
    @Required
    @PrimaryKey
    private String id;
    @Required
    private String outletId;
    private String shoppingFrequency;
    private String downline;
    private String faceUp;

    public SurveyPembelian() {
    }

    protected SurveyPembelian(Parcel in) {
        id = in.readString();
        outletId = in.readString();
        shoppingFrequency = in.readString();
        downline = in.readString();
        faceUp = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(outletId);
        dest.writeString(shoppingFrequency);
        dest.writeString(downline);
        dest.writeString(faceUp);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SurveyPembelian> CREATOR = new Creator<SurveyPembelian>() {
        @Override
        public SurveyPembelian createFromParcel(Parcel in) {
            return new SurveyPembelian(in);
        }

        @Override
        public SurveyPembelian[] newArray(int size) {
            return new SurveyPembelian[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOutletId() {
        return outletId;
    }

    public void setOutletId(String outletId) {
        this.outletId = outletId;
    }

    public String getShoppingFrequency() {
        return shoppingFrequency;
    }

    public void setShoppingFrequency(String shoppingFrequency) {
        this.shoppingFrequency = shoppingFrequency;
    }

    public String getDownline() {
        return downline;
    }

    public void setDownline(String downline) {
        this.downline = downline;
    }

    public String getFaceUp() {
        return faceUp;
    }

    public void setFaceUp(String faceUp) {
        this.faceUp = faceUp;
    }
}