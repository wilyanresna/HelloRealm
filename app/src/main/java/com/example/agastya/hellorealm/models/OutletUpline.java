package com.example.agastya.hellorealm.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by AgastyA on 1/26/2018.
 */

public class OutletUpline extends RealmObject implements Parcelable {

    @Required
    @PrimaryKey
    private String id;
    private String ouCode;
    private String territoryCode;
    private String districtCode;
    private int route;
    private String outletId;
    private String outletCode;
    private String outletName;
    private String address;
    private String village;
    private String district;
    private String subChannel;
    private RealmList<Nickname> nicknames;

    public OutletUpline() {
    }

    protected OutletUpline(Parcel in) {
        id = in.readString();
        ouCode = in.readString();
        territoryCode = in.readString();
        districtCode = in.readString();
        route = in.readInt();
        outletId = in.readString();
        outletCode = in.readString();
        outletName = in.readString();
        address = in.readString();
        village = in.readString();
        district = in.readString();
        subChannel = in.readString();
    }

    public static final Creator<OutletUpline> CREATOR = new Creator<OutletUpline>() {
        @Override
        public OutletUpline createFromParcel(Parcel in) {
            return new OutletUpline(in);
        }

        @Override
        public OutletUpline[] newArray(int size) {
            return new OutletUpline[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(ouCode);
        parcel.writeString(territoryCode);
        parcel.writeString(districtCode);
        parcel.writeInt(route);
        parcel.writeString(outletId);
        parcel.writeString(outletCode);
        parcel.writeString(outletName);
        parcel.writeString(address);
        parcel.writeString(village);
        parcel.writeString(district);
        parcel.writeString(subChannel);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOuCode() {
        return ouCode;
    }

    public void setOuCode(String ouCode) {
        this.ouCode = ouCode;
    }

    public String getTerritoryCode() {
        return territoryCode;
    }

    public void setTerritoryCode(String territoryCode) {
        this.territoryCode = territoryCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public int getRoute() {
        return route;
    }

    public void setRoute(int route) {
        this.route = route;
    }

    public String getOutletId() {
        return outletId;
    }

    public void setOutletId(String outletId) {
        this.outletId = outletId;
    }

    public String getOutletCode() {
        return outletCode;
    }

    public void setOutletCode(String outletCode) {
        this.outletCode = outletCode;
    }

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSubChannel() {
        return subChannel;
    }

    public void setSubChannel(String subChannel) {
        this.subChannel = subChannel;
    }

    public RealmList<Nickname> getNicknames() {
        return nicknames;
    }

    public void setNicknames(RealmList<Nickname> nicknames) {
        this.nicknames = nicknames;
    }
}