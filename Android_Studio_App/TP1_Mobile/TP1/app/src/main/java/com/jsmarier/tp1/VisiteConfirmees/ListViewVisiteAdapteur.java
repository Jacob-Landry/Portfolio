package com.jsmarier.tp1.VisiteConfirmees;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jsmarier.tp1.ChoixVisiteActivity.Visite;
import com.jsmarier.tp1.R;

import java.util.List;

/**
 * Crée et manipule la vue de la liste des Visites
 */
public class ListViewVisiteAdapteur extends ArrayAdapter<Visite> {
    int resource;
    Context context;

    public ListViewVisiteAdapteur(@NonNull Context context, int resource, @NonNull List<Visite> objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.context = context;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Visite visite = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        // Obtient les champs
        TextView nomActivitee = convertView.findViewById(R.id.Titre);
        TextView lieuActivitee = convertView.findViewById(R.id.Lieu);
        TextView prixActivitee = convertView.findViewById(R.id.Montant);
        TextView presenceConfirmesActivitee = convertView.findViewById(R.id.Presences_Confirmes);

        // Definit les informaitons dans le ListView
        nomActivitee.setText(visite.getNom());
        lieuActivitee.setText(visite.getLieu());
        prixActivitee.setText(visite.getPrix() + " $");
        presenceConfirmesActivitee.setText(Integer.toString(visite.getVoyageursConfirmes().getVoyageurList().size()));

        return convertView;
    }
}
