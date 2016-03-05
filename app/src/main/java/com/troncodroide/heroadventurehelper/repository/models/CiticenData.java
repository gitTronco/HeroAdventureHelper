package com.troncodroide.heroadventurehelper.repository.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Tronco on 04/03/2016.
 */
public class CiticenData implements Parcelable{
    String name,thumbnail,hair_color;
    Integer id,age;
    Double weight,height;
    List<String> professions;
    List<String> friends;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.thumbnail);
        dest.writeString(this.hair_color);
        dest.writeValue(this.id);
        dest.writeValue(this.age);
        dest.writeValue(this.weight);
        dest.writeValue(this.height);
        dest.writeStringList(this.professions);
        dest.writeStringList(this.friends);
    }

    public CiticenData() {
    }

    protected CiticenData(Parcel in) {
        this.name = in.readString();
        this.thumbnail = in.readString();
        this.hair_color = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.age = (Integer) in.readValue(Integer.class.getClassLoader());
        this.weight = (Double) in.readValue(Double.class.getClassLoader());
        this.height = (Double) in.readValue(Double.class.getClassLoader());
        this.professions = in.createStringArrayList();
        this.friends = in.createStringArrayList();
    }

    public static final Creator<CiticenData> CREATOR = new Creator<CiticenData>() {
        public CiticenData createFromParcel(Parcel source) {
            return new CiticenData(source);
        }

        public CiticenData[] newArray(int size) {
            return new CiticenData[size];
        }
    };
}
