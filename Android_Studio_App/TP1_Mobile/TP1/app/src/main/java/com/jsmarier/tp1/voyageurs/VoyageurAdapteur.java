package com.jsmarier.tp1.voyageurs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.jsmarier.tp1.R;

import java.util.List;

/**
 * Crée et manipule la vue de la liste des voyageurs
 */
public class VoyageurAdapteur extends ArrayAdapter<Voyageur> {

    int resource;
    Context context;

    public VoyageurAdapteur(@NonNull Context context, int resource, @NonNull List<Voyageur> objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.context = context;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Voyageur voyageur = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        // Obtient les champs
        TextView nomVoyageur = convertView.findViewById(R.id.Nom);
        TextView prenomVoyageur = convertView.findViewById(R.id.Prenom);
        TextView courrielVoyageur = convertView.findViewById(R.id.Courriel);

        // Definit les informaitons dans le ListView
        nomVoyageur.setText(voyageur.getNom());
        prenomVoyageur.setText(voyageur.getPrenom());
        courrielVoyageur.setText(voyageur.getCourriel());

        // Affiche la boite de dialogue quand une ligne est cliquee
        convertView.setOnClickListener((x) -> {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(x.getContext());

            alertBuilder.setTitle("Contact information")
                    .setMessage("Le telephone est : " + voyageur.getTelephone())
                    .setPositiveButton("OK", null)
                    .create().show();
        });

        return convertView;
    }
}
