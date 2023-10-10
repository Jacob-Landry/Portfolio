package com.jsmarier.tp1;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jsmarier.tp1.ChoixVisiteActivity.Visites;
import com.jsmarier.tp1.voyageurs.Voyageur;
import com.jsmarier.tp1.voyageurs.VoyageurAdapteur;
import com.jsmarier.tp1.voyageurs.Voyageurs;

import java.util.List;

/**
 * Affiche la liste de toutes les visites et leur nombre de présences
 */
public class ListeVoyageurs extends MainActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_voyageur);

        updateFromIntent(getIntent());

        // Obtenir les infos nécessaires
        Voyageurs voyageurs = getIntent().getParcelableExtra("voyageurs", Voyageurs.class);
        List<Voyageur> voyageurList = voyageurs.getVoyageurList();

        ListView listView = findViewById(R.id.liste);

        VoyageurAdapteur voyageurAdapteur = new VoyageurAdapteur(this, R.layout.activity_voyageurs, voyageurList);

        // Remplir la liste
        listView.setAdapter(voyageurAdapteur);
    }
}
