package com.jsmarier.tp1.VisiteConfirmees;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.jsmarier.tp1.ChoixVisiteActivity.Visite;
import com.jsmarier.tp1.ChoixVisiteActivity.Visites;
import com.jsmarier.tp1.MainActivity;
import com.jsmarier.tp1.R;

import java.util.List;

/**
 * Affiche la liste de toutes les visites et leur nombre de présences
 */
public class VisitesConfirmeesActivity extends MainActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_activitee);

        updateFromIntent(getIntent());

        // Obtenir les infos nécessaires
        List<Visite> listeDVisites = getIntent().getParcelableExtra("visites", Visites.class).getVisiteList();

        ListView listView = findViewById(R.id.Liste_Activitees);

        ListViewVisiteAdapteur activiteeAdapteur = new ListViewVisiteAdapteur(this, R.layout.activity_visites_confirmees, listeDVisites);

        //Remplir la liste
        listView.setAdapter(activiteeAdapteur);
    }
}