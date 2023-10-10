package com.jsmarier.tp1.ChoixVisiteActivity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.jsmarier.tp1.voyageurs.Voyageur;
import com.jsmarier.tp1.voyageurs.Voyageurs;

import java.util.ArrayList;
import java.util.List;

/**
 * Une activitée avec les Voyageurs y participant
 */
public class Visite implements Parcelable {

    public static final Creator<Visite> CREATOR = new Creator<>() {
        @Override
        public Visite createFromParcel(Parcel in) {
            return new Visite(in);
        }

        @Override
        public Visite[] newArray(int size) {
            return new Visite[size];
        }
    };

    private String nom;
    private String lieu;
    private double prix;
    Voyageurs voyageursConfirmes;

    public Visite(String nom, String lieu, double prix, Voyageurs voyageursConfirmes) {
        this.nom = nom;
        this.lieu = lieu;
        this.prix = prix;
        this.voyageursConfirmes = voyageursConfirmes;
    }

    public Visite(Parcel in) {
        nom = in.readString();
        lieu = in.readString();
        prix = in.readDouble();
        voyageursConfirmes = in.readParcelable(Voyageurs.class.getClassLoader(), Voyageurs.class);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Voyageurs getVoyageursConfirmes() {
        return voyageursConfirmes;
    }

    public void setVoyageursConfirmes(Voyageurs voyageursConfirmes) {
        this.voyageursConfirmes = voyageursConfirmes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(nom);
        parcel.writeString(lieu);
        parcel.writeDouble(prix);
        parcel.writeParcelable(voyageursConfirmes, 0);
    }
}
