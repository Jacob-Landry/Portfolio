package com.jsmarier.tp1.ChoixVisiteActivity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Un ensemple de plusieurs Visite
 */
public class Visites implements Parcelable {

    public static final Creator<Visites> CREATOR = new Creator<>() {
        @Override
        public Visites createFromParcel(Parcel in) {
            return new Visites(in);
        }

        @Override
        public Visites[] newArray(int size) {
            return new Visites[size];
        }
    };

    List<Visite> visiteList;

    public Visites(List<Visite> visiteList) {
        this.visiteList = visiteList;
    }

    public Visites (Visite... visites ) {
        this(new ArrayList<>());

        visiteList.addAll(Arrays.asList(visites));
    }

    public Visites(Parcel in) {
        this.visiteList = in.createTypedArrayList(Visite.CREATOR);
    }

    public List<Visite> getVisiteList() {
        return visiteList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeTypedList(visiteList);
    }
}