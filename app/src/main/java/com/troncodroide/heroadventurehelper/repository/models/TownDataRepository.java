package com.troncodroide.heroadventurehelper.repository.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.Random;

public class TownDataRepository implements Parcelable {
    String name;
    List<CiticenDataRepository> citicens;

    public TownDataRepository(String name, List<CiticenDataRepository> citicens) {
        this.name = name;
        this.citicens = citicens;
    }

    public String getName() {
        return name;
    }

    public List<CiticenDataRepository> getCiticens() {
        return citicens;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeTypedList(citicens);
    }

    protected TownDataRepository(Parcel in) {
        this.name = in.readString();
        this.citicens = in.createTypedArrayList(CiticenDataRepository.CREATOR);
    }

    public static final Creator<TownDataRepository> CREATOR = new Creator<TownDataRepository>() {
        public TownDataRepository createFromParcel(Parcel source) {
            return new TownDataRepository(source);
        }

        public TownDataRepository[] newArray(int size) {
            return new TownDataRepository[size];
        }
    };
}
