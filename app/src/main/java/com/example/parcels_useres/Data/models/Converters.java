package com.example.parcels_useres.Data.models;

import android.location.Location;

import androidx.room.TypeConverter;

public class Converters {
    @TypeConverter
    public static Parcel.ParcelKind getKind(Integer numeral){
        for(Parcel.ParcelKind  ds :  Parcel.ParcelKind .values()){
            if(ds.ordinal() == numeral){
                return ds;
            }
        }
        return null;
    }

    @TypeConverter
    public static int ParcelKIndToInetger(Parcel.ParcelKind  parcelKind) {
        return parcelKind.ordinal();
    }

    @TypeConverter
    public static Parcel.Weight getWeight(Integer numeral){
        for(Parcel.Weight ds :  Parcel.Weight .values()){
            if(ds.ordinal() == numeral){
                return ds;
            }
        }
        return null;
    }
    @TypeConverter
    public static int WeightToInetger(Parcel.Weight weight) {
        return weight.ordinal();
    }

    @TypeConverter
    public static Parcel.ParcelStatus getParcelStatus(Integer numeral){
        for(Parcel.ParcelStatus ds :  Parcel.ParcelStatus .values()){
            if(ds.ordinal() == numeral){
                return ds;
            }
        }
        return null;
    }
    @TypeConverter
    public static int ParcelStatusToInetger(Parcel.ParcelStatus parcelStatus) {
        return parcelStatus.ordinal();
    }

    @TypeConverter
    public static Location stringToLocation(String fromRoom) {
        if (fromRoom != "") {
            String[] latlong = fromRoom.split(",");
            double latitude = Double.parseDouble(latlong[0]);
            double longitude = Double.parseDouble(latlong[1]);
            Location location = new Location("");
            location.setLatitude(latitude);
            location.setLongitude(longitude);
            return location;
        }
        return null;
    }


    @TypeConverter
    public static String locationToString(Location location) {
        if (location!= null){
            return Location.convert(location.getLatitude(), Location.FORMAT_DEGREES) + "," + Location.convert(location.getLongitude(), Location.FORMAT_DEGREES);
        }
        return "";
    }


}