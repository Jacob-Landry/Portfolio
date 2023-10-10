package com.jsmarier.tp1;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;
import com.jsmarier.tp1.R;

/**
 * Affiche un sondage
 */
public class SondageActivity extends MainActivity {

    private File fichierNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sondage);

        // Préparation du fichier
        File externalDir = getExternalFilesDir("RepData");
        fichierNotes = new File(externalDir, "notes.txt");
        try {
            fichierNotes.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        afficherMoyenne(obtenirStringNotes());
    }

    public void submit(View view) {
        TextView champNom = findViewById(R.id.nomSondage);
        TextView champNote = findViewById(R.id.noteSondage);
        TextView champCommentaire = findViewById(R.id.commentairesSondage);
        TextView champErreur = findViewById(R.id.labelErreurSondage);

        // Vérifie que tout les champs sont remplis
        if (champNom.getText().length() != 0 && champNote.getText().length() != 0 && champCommentaire.getText().length() != 0) {
            // Ajouter la note présente à la liste des notes
            String notesAnterieures = obtenirStringNotes();
            String notes = notesAnterieures + champNote.getText() + ",";

            // Retire le message d'erreur
            champErreur.setText("");

            ecrireNotesAuFichier(notes);
            afficherMoyenne(notes);
            viderChamps();

        } else {

            champErreur.setText("Tous les champs sont nécessaires");
        }

    }

    private String obtenirStringNotes() {
        StringBuilder stringBuilder = new StringBuilder();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(fichierNotes);
            byte[] bytes = fis.readAllBytes();

            // Ajoute les char du fichier à un string
            for (byte vByte : bytes) {
                stringBuilder.append((char) vByte);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);

        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return stringBuilder.toString();
    }

    private void ecrireNotesAuFichier(String notes) {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(fichierNotes);

            fos.write(notes.getBytes());

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (fos != null) {
                try {
                    fos.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void afficherMoyenne(String notes) {
        List<String> noteList = List.of(notes.split(","));
        // Convertir liste de strings en liste de int
        IntStream noteStream = noteList.stream().mapToInt(Integer::parseInt);

        int somme = 0;
        int montant = noteList.size();
        if (!noteList.get(0).equals(""))
            somme = noteStream.sum();

        TextView moyenneText = findViewById(R.id.noteMoyenneSondage);
        // Affiche le calcul de moyenne
        moyenneText.setText(String.valueOf(somme / montant));
    }

    private void viderChamps() {
        TextView champNom = findViewById(R.id.nomSondage);
        TextView champNote = findViewById(R.id.noteSondage);
        TextView champCommentaire = findViewById(R.id.commentairesSondage);

        champNom.setText("");
        champNote.setText("");
        champCommentaire.setText("");
    }
}