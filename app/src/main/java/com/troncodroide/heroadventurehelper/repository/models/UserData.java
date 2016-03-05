package com.troncodroide.heroadventurehelper.repository.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class UserData implements Parcelable {
    int id;
    String name;
    String email;
    String last_name;
    String postcode;
    int num_addresses;
    int num_credit_cards;
    int num_orders;
    boolean suscription;
    String url_img;

    public String getUrl_img() {
        return url_img;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPostcode() {
        return postcode;
    }

    public boolean isSuscription() {
        return suscription;
    }


    public UserData() {
    }

    public UserData(boolean sample) {
        Random ran = new Random();
        this.id = ran.nextInt(100);
        this.name = "Jhon";
        this.last_name = "Doe";
        this.email = "Sample@sample.com";
        this.url_img = "http://lorempixel.com/400/400/abstract/";
        this.postcode = "10000";
        this.suscription = true;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setNum_addresses(int num_addresses) {
        this.num_addresses = num_addresses;
    }

    public void setNum_credit_cards(int num_credit_cards) {
        this.num_credit_cards = num_credit_cards;
    }

    public void setNum_orders(int num_orders) {
        this.num_orders = num_orders;
    }

    public void setSuscription(boolean suscription) {
        this.suscription = suscription;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.num_addresses);
        dest.writeInt(this.num_credit_cards);
        dest.writeInt(this.num_orders);
        dest.writeByte(suscription ? (byte) 1 : (byte) 0);
        dest.writeString(this.name);
        dest.writeString(this.last_name);
        dest.writeString(this.email);
        dest.writeString(this.postcode);
        dest.writeString(this.url_img);
    }

    protected UserData(Parcel in) {
        this.id = in.readInt();
        this.num_addresses= in.readInt();
        this.num_credit_cards = in.readInt();
        this.num_orders = in.readInt();
        this.suscription = in.readByte() != 0;
        this.name = in.readString();
        this.last_name = in.readString();
        this.email = in.readString();
        this.postcode = in.readString();
        this.url_img = in.readString();
    }

    public static final Creator<UserData> CREATOR = new Creator<UserData>() {
        public UserData createFromParcel(Parcel source) {
            return new UserData(source);
        }

        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };
}
