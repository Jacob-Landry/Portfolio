package com.jsmarier.tp1.ChoixVisiteActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jsmarier.tp1.MainActivity;
import com.jsmarier.tp1.R;
import com.jsmarier.tp1.voyageurs.Voyageur;
import com.jsmarier.tp1.voyageurs.Voyageurs;

import java.util.List;

/**
 * Affiche un menu de sélection de voyageurs et de visite afin de gèrer les présence
 */
public class ChoixVisitesActivity extends MainActivity {

    private ListVoyageursAdapteur adapterVoyageur;
    private ChoixActiviteeAdapteur adapteurActivitee;
    RadioButton radioButtonpresence;
    TextView montantCumuleTextView;
    Spinner spinnerNomPrenomVoyageurs;
    Spinner spinnerChoixActivitee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_visites_actifity);

        updateFromIntent(getIntent());

        //remplire list
        List<Voyageur> listeVoyageurs = getIntent().getParcelableExtra("voyageurs", Voyageurs.class).getVoyageurList();
        List<Visite> listeDVisites = getIntent().getParcelableExtra("visites", Visites.class).getVisiteList();

        //Créer adapteur
        adapterVoyageur = new ListVoyageursAdapteur(this, android.R.layout.simple_spinner_item, listeVoyageurs);
        adapteurActivitee = new ChoixActiviteeAdapteur(this, android.R.layout.simple_spinner_item, listeDVisites);

        // Obtenir les infos nécessaires
        this.spinnerNomPrenomVoyageurs = (Spinner) findViewById(R.id.spinner_Nom_Prenom_Voyageurs);
        this.spinnerChoixActivitee = (Spinner) findViewById(R.id.spinner_Choix_Activitee);
        this.radioButtonpresence = (RadioButton) findViewById(R.id.boutonPresenceVoyageurs);
        this.montantCumuleTextView = (TextView) findViewById(R.id.Montant_Cumulee_Activitee);

        this.spinnerNomPrenomVoyageurs.setAdapter(adapterVoyageur);
        this.spinnerChoixActivitee.setAdapter(adapteurActivitee);

        Visite visiteSelectionne = listeDVisites.get(spinnerChoixActivitee.getSelectedItemPosition());
        Voyageur voyageurSelectionne = listeVoyageurs.get(spinnerNomPrenomVoyageurs.getSelectedItemPosition());

        //met a jour les information afficher
        updateButtonState(voyageurSelectionne, visiteSelectionne);
        updatePrixCumule(voyageurSelectionne, listeDVisites);

        //lorsqu'on change de voyageurs
        this.spinnerNomPrenomVoyageurs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Voyageur voyageurCourant = adapterVoyageur.getItem(position);
                Visite visiteCourante = listeDVisites.get(spinnerChoixActivitee.getSelectedItemPosition());

                //met a jour les information afficher
                updateButtonState(voyageurCourant, visiteCourante);
                updatePrixCumule(voyageurCourant, listeDVisites);

                //Change l'element selectionnée
                Toast.makeText(ChoixVisitesActivity.this, voyageurCourant.getNom() + " " + voyageurCourant.getPrenom(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //lorsqu'on change d'Activitée
        this.spinnerChoixActivitee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Visite visiteCourante = adapteurActivitee.getItem(position);
                Voyageur voyageurCourant = listeVoyageurs.get(spinnerNomPrenomVoyageurs.getSelectedItemPosition());

                //met a jour les information afficher
                updateButtonState(voyageurCourant, visiteCourante);
                updatePrixCumule(voyageurCourant, listeDVisites);

                //Change l'element selectionnée
                Toast.makeText(ChoixVisitesActivity.this, visiteCourante.getNom(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        radioButtonpresence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Voyageur voyageurCourant = listeVoyageurs.get(spinnerNomPrenomVoyageurs.getSelectedItemPosition());
                Visite visiteCourante = listeDVisites.get(spinnerChoixActivitee.getSelectedItemPosition());

                //Vérifie si le boutton est cochée. Si oui, ajoute un voyageur a l'activitée
                if (radioButtonpresence.isChecked()) {

                    //Met a jour les données local
                    voyageurCourant.getListeDActiviteePresent().add(visiteCourante.getNom());
                    visiteCourante.getVoyageursConfirmes().getVoyageurList().add(voyageurCourant);

                    //Met a jour les données de MainActivity
                    setVisites(new Visites(listeDVisites));
                    setVoyageurs(new Voyageurs(listeVoyageurs));
                }

                //met a jour les information afficher
                updateButtonState(voyageurCourant, visiteCourante);
                updatePrixCumule(voyageurCourant, listeDVisites);
            }
        });
    }

    /**
     * Calcule et mets a jour le montant cumulée
     *
     * @param voyageurCourant
     * @param listeDVisites
     */
    private void updatePrixCumule(Voyageur voyageurCourant, List<Visite> listeDVisites) {
        double prixCumule = 0;

        for (Visite visite : listeDVisites) {
            for (String nomVisite : voyageurCourant.getListeDActiviteePresent()) {
                if (visite.getNom().equals(nomVisite)) prixCumule += visite.getPrix();
            }
        }

        montantCumuleTextView.setText(prixCumule + " $");
    }

    /**
     * Vérifie que le voyageur sélectionnée n'est pas deja présent à la visite sélectionnée.
     * Si oui, change l'état du boutton
     *
     * @param voyageurCourant
     * @param visiteCourante
     */
    private void updateButtonState(Voyageur voyageurCourant, Visite visiteCourante) {
        boolean bouttonEstActive = true;

        if (voyageurCourant.getListeDActiviteePresent().size() != 0) {
            for (String nomVisite : voyageurCourant.getListeDActiviteePresent()) {
                if (nomVisite.equals(visiteCourante.getNom())) bouttonEstActive = false;
            }
        }

        radioButtonpresence.setClickable(bouttonEstActive);
        radioButtonpresence.setChecked(!bouttonEstActive);
    }
}