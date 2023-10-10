package com.jsmarier.tp1.ChoixVisiteActivity;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jsmarier.tp1.voyageurs.Voyageur;

import java.util.List;

/**
 * Crée et manipule la vue du spinner des Voyageurs
 */
public class ListVoyageursAdapteur extends ArrayAdapter<Voyageur> {
    private Context context;
    private List<Voyageur> voyageurs;

    public ListVoyageursAdapteur(Context context, int textViewResourceId, List<Voyageur> voyageurs) {
        super(context, textViewResourceId, voyageurs);
        this.voyageurs = voyageurs;
        this.context = context;
    }

    @Override
    public int getCount() {
        return voyageurs.size();
    }

    @Override
    public Voyageur getItem(int position) {
        return voyageurs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(voyageurs.get(position).getNom());

        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(voyageurs.get(position).getNom());

        return label;
    }
}
