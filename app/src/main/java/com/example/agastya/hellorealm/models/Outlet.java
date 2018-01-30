package com.example.agastya.hellorealm.models;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by AgastyA on 1/24/2018.
 */

public class Outlet extends RealmObject implements Parcelable {
    @Required
    @PrimaryKey
    private String id;
    @Required
    private String outletId;
    private String outletName;
    private String subChannel;

    public Outlet() {
    }

    protected Outlet(Parcel in) {
        id = in.readString();
        outletId = in.readString();
        outletName = in.readString();
        subChannel = in.readString();
    }

    public static final Creator<Outlet> CREATOR = new Creator<Outlet>() {
        @Override
        public Outlet createFromParcel(Parcel in) {
            return new Outlet(in);
        }

        @Override
        public Outlet[] newArray(int size) {
            return new Outlet[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(outletId);
        parcel.writeString(outletName);
        parcel.writeString(subChannel);
    }

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

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public String getSubChannel() {
        return subChannel;
    }

    public void setSubChannel(String subChannel) {
        this.subChannel = subChannel;
    }
}
