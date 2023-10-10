package com.jsmarier.tp1.voyageurs;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.jsmarier.tp1.ChoixVisiteActivity.Visite;

import java.util.ArrayList;
import java.util.List;

/**
 * Un Voyageur avec une liste des activitées au quel il participe
 */
public class Voyageur  implements Parcelable {

    public static final Creator<Voyageur> CREATOR = new Creator<Voyageur>() {
        @Override
        public Voyageur createFromParcel(Parcel in) {
            return new Voyageur(in);
        }

        @Override
        public Voyageur[] newArray(int size) {
            return new Voyageur[size];
        }
    };

    private String nom;
    private String prenom;
    private String courriel;
    private String telephone;
    private List<String> listeDeVisitePresent;

    public Voyageur(String nom, String prenom, String courriel, String telephone, List<String> listeDeVisitePresent) {
        this.nom = nom;
        this.prenom = prenom;
        this.courriel = courriel;
        this.telephone = telephone;
        this.listeDeVisitePresent = listeDeVisitePresent;
    }

    public Voyageur(Parcel in) {
        nom = in.readString();
        prenom = in.readString();
        courriel = in.readString();
        telephone = in.readString();
        listeDeVisitePresent = in.createStringArrayList();
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getCourriel() {
        return courriel;
    }

    public String getTelephone() {
        return telephone;
    }

    public List<String> getListeDActiviteePresent() {
        return listeDeVisitePresent;
    }

    public void setListeDActiviteePresent(List<String> listeDVisitePresent) {
        this.listeDeVisitePresent = listeDVisitePresent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(nom);
        parcel.writeString(prenom);
        parcel.writeString(courriel);
        parcel.writeString(telephone);
        parcel.writeStringList(listeDeVisitePresent);
    }


}
