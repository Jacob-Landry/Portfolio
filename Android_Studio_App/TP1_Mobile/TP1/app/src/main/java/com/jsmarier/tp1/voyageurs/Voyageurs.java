package com.jsmarier.tp1.voyageurs;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Un ensemple de plusieurs Voyageur
 */
public class Voyageurs implements Parcelable {

    public static final Creator<Voyageurs> CREATOR = new Creator<Voyageurs>() {
        @Override
        public Voyageurs createFromParcel(Parcel in) {
            return new Voyageurs(in);
        }

        @Override
        public Voyageurs[] newArray(int size) {
            return new Voyageurs[size];
        }
    };

    List<Voyageur> voyageurList;

    public Voyageurs(List<Voyageur> voyageurList) {
        this.voyageurList = voyageurList;
    }

    public Voyageurs (Voyageur ... voyageurs ) {
        this(new ArrayList<>());

        voyageurList.addAll(Arrays.asList(voyageurs));
    }

    public Voyageurs(Parcel in) {
        this.voyageurList = in.createTypedArrayList(Voyageur.CREATOR);
    }

    public List<Voyageur> getVoyageurList() {
        return voyageurList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeTypedList(voyageurList);
    }
}
