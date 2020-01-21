package com.example.parcels_useres.Data.models;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import android.location.Location;
@Entity(tableName= "parcel_table")
public class Parcel {
    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate= true)
    private int id;
    private ParcelKind parcelKind;
    private boolean isFragile;
    private Weight w;
    private Location location;
    private String name;
    private String address;
    private String phone;
    private String email;
    private ParcelStatus parcelStatus;

    public int getId() {
        return id;
    }

    public ParcelKind getParcelKind() {
        return parcelKind;
    }

    public boolean isFragile() {
        return isFragile;
    }

    public Weight getW() {
        return w;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public ParcelStatus getParcelStatus() {
        return parcelStatus;
    }

    public void setParcelKind(ParcelKind parcelKind) {
        this.parcelKind = parcelKind;
    }

    public void setFragile(boolean fragile) {
        isFragile = fragile;
    }

    public void setW(Weight w) {
        this.w = w;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setParcelStatus(ParcelStatus parcelStatus) {
        this.parcelStatus = parcelStatus;
    }


    public enum Weight {
        LESS_THEN_500_G,LESS_THEN_KG,LESS_THEN_5_KG,LESS_THEN_20_KG;
    }

    public enum ParcelKind {
        ENVELOPE,LITTEL_PARCEL,BIG_PARCEL;
    }

    public enum ParcelStatus {
        WAIT,HAVE_DELIVER,ON_WAY,ACCEPT;
    }


}
