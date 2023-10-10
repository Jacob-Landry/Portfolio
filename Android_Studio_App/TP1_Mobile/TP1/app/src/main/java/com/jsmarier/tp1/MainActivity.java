package com.jsmarier.tp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.jsmarier.tp1.ChoixVisiteActivity.Visite;
import com.jsmarier.tp1.ChoixVisiteActivity.ChoixVisitesActivity;
import com.jsmarier.tp1.ChoixVisiteActivity.Visites;
import com.jsmarier.tp1.VisiteConfirmees.VisitesConfirmeesActivity;
import com.jsmarier.tp1.voyageurs.Voyageur;
import com.jsmarier.tp1.voyageurs.Voyageurs;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Visites visites = new Visites(
            new Visite("L’université Harvard", "Cambridge", 120.99, new Voyageurs()),
            new Visite("croisière au poste de Boston", "Boston", 65.30, new Voyageurs()),
            new Visite("croisière de la statue de la liberté", "New York", 250.45, new Voyageurs())
    );
    private Voyageurs voyageurs = new Voyageurs(
            new Voyageur("Thierry", "Dupont", "d.thierry@gmail.com", "418 666-6666", new ArrayList<>()),
            new Voyageur("Thierry1", "Dupont1", "d.thierry@gmail.com1", "418 666-66661", new ArrayList<>()),
            new Voyageur("Thierry2", "Dupont2", "d.thierry@gmail.com2", "418 666-66662", new ArrayList<>()),
            new Voyageur("Thierry3", "Dupont3", "d.thierry@gmail.com3", "418 666-66663", new ArrayList<>()),
            new Voyageur("Thierry4", "Dupont4", "d.thierry@gmail.com4", "418 666-66664", new ArrayList<>()),
            new Voyageur("Thierry5", "Dupont5", "d.thierry@gmail.com5", "418 666-66665", new ArrayList<>()),
            new Voyageur("Thierry6", "Dupont6", "d.thierry@gmail.com6", "418 666-66666", new ArrayList<>())
    );

    protected void setVisites(Visites visites) {
        this.visites = visites;
    }

    protected void setVoyageurs(Voyageurs voyageurs) {
        this.voyageurs = voyageurs;
    }

    protected void updateFromIntent(Intent intent) {
        setVoyageurs(intent.getParcelableExtra("voyageurs", Voyageurs.class));
        setVisites(intent.getParcelableExtra("visites", Visites.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        Intent selectedIntent;

        // Changer d'activité et passer les données nécessaires
        if (itemId == R.id.MenuVoyageurs) {
            selectedIntent = new Intent(this, ListeVoyageurs.class);
        } else if (itemId == R.id.MenuChoixVisites) {
            selectedIntent = new Intent(this, ChoixVisitesActivity.class);
        } else if (itemId == R.id.MenuVisitesConfirmees) {
            selectedIntent = new Intent(this, VisitesConfirmeesActivity.class);
        } else if (itemId == R.id.MenuSondage) {
            selectedIntent = new Intent(this, SondageActivity.class);
        } else {
            return super.onOptionsItemSelected(item);
        }

        selectedIntent.putExtra("voyageurs", voyageurs);
        selectedIntent.putExtra("visites", visites);

        startActivity(selectedIntent);
        return true;
    }
}