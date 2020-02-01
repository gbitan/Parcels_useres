package com.example.parcels_useres.Data.models;

import android.location.Location;

public class ParcelBuilder {
    private Parcel.ParcelKind parcelKind;
    private boolean isFragile;
    private Parcel.Weight w;
    private Location location;
    private String name = "defult";
    private String address = "exemple address";
    private String phone = "1234567890";
    private String email = "defulte@exemple";
    private Parcel.ParcelStatus parcelStatus;

    public ParcelBuilder setParcelKind(Parcel.ParcelKind parcelKind) {
        this.parcelKind = parcelKind;
        return this;
    }

    public ParcelBuilder setIsFragile(boolean isFragile) {
        this.isFragile = isFragile;
        return this;
    }

    public ParcelBuilder setW(Parcel.Weight w) {
        this.w = w;
        return this;
    }

    public ParcelBuilder setLocation(Location location) {
        this.location = location;
        return this;
    }

    public ParcelBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ParcelBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public ParcelBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public ParcelBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public ParcelBuilder setParcelStatus(Parcel.ParcelStatus parcelStatus) {
        this.parcelStatus = parcelStatus;
        return this;
    }

    public Parcel createParcel() {
        return new Parcel(parcelKind, isFragile, w, location, name, address, phone, email, parcelStatus);
    }
}