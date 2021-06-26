package com.dynamicdevz.sellphones.model.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Phone implements Parcelable{
    private Manufacturer manufacturer;
    private String model;
    private double price;
    private int year;
    private int phoneid;

    protected Phone(Parcel in){
        model =in.readString();
        price =in.readDouble();
        year = in.readInt();
        phoneid = in.readInt();
    }

    public Phone(Manufacturer manufacturer, String model, double price,int year) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = price;
        this.year =year;
    }
    public Phone(int phoneid, Manufacturer manufacturer, String model, double price,int year) {
        this.phoneid =phoneid;
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = price;
        this.year =year;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i){
        parcel.writeString(model);
        parcel.writeDouble(price);
        parcel.writeInt(year);
        parcel.writeInt(phoneid);
    }
    public enum Manufacturer{
        SAMSUNG,
        LG,
        MOTOROLA,
        APPLE,
        VIVO,
        TCL_ALCATEL
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }
    public int getYear(){
        return year;
    }
    public int getPhoneid() {
        return phoneid;
    }



    public static final Creator<Phone> CREATOR = new Creator<Phone>() {
        @Override
        public Phone createFromParcel(Parcel in) {
            return new Phone(in);
        }

        @Override
        public Phone[] newArray(int size) {
            return new Phone[size];
        }
    };
}
