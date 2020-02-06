package com.example.parcels_useres.Data.models;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import android.location.Location;

import com.google.firebase.database.Exclude;

@Entity(tableName= "parcel_table")
public class Parcel {
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

    public Parcel(){
        id=0;
        location = new Location("");
    }


    public Parcel(ParcelKind parcelKind, boolean isFragile, Weight w, Location location,
                  String name, String address, String phone, String email, ParcelStatus parcelStatus) {

        id=0;
        this.parcelKind = parcelKind;
        this.isFragile = isFragile;
        this.w = w;
        this.location = location;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.parcelStatus = parcelStatus;
    }


    public int getId() {
        return id;
    }

    public ParcelKind getParcelKind() {
        return parcelKind;
    }

    public void setParcelKind(ParcelKind parcelKind) {
        this.parcelKind = parcelKind;
    }

    public boolean isFragile() {
        return isFragile;
    }

    public void setFragile(boolean fragile) {
        isFragile = fragile;
    }

    public Weight getW() {
        return w;
    }

    public void setW(Weight w) {
        this.w = w;
    }

    @Exclude
    public Location getLocation() {
        return location;
    }

    public Double getLatitude(){
        return location.getLatitude();
    }

    public Double getLongitude(){
        return location.getLongitude();
    }

    public void setLatitude(double latitude){
        location.setLatitude(latitude);
    }

    public void setLongitude(double longitude){
        location.setLongitude(longitude);
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ParcelStatus getParcelStatus() {
        return parcelStatus;
    }

    public void setParcelStatus(ParcelStatus parcelStatus) {
        this.parcelStatus = parcelStatus;
    }

    public void setId(int id) {
        this.id = id;
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
