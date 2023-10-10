package com.jsmarier.tp1.ChoixVisiteActivity;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Crée et manipule la vue du spinner choix activitée
 */
public class ChoixActiviteeAdapteur extends ArrayAdapter<Visite> {
    private Context context;
    private List<Visite> visites;

    public ChoixActiviteeAdapteur(Context context, int textViewResourceId, List<Visite> visites) {
        super(context, textViewResourceId, visites);
        this.visites = visites;
        this.context = context;
    }

    @Override
    public int getCount() {
        return visites.size();
    }

    @Override
    public Visite getItem(int position) {
        return visites.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(visites.get(position).getNom());

        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(visites.get(position).getNom());

        return label;
    }
}
