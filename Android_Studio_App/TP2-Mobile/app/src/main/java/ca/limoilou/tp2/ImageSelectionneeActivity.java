package ca.limoilou.tp2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ImageSelectionneeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_selectionnee);

        int[] musiques = new int[]{
                R.raw.are_you_happy,
                R.raw.axli,
                R.raw.give_em_the_love_instrumental,
                R.raw.like_its_like_not_true,
                R.raw.pink_floyd_brain_damage_eclipse,
                R.raw.the_name_is_corrupt_good_snare,
                R.raw.wish_you_were_here,
                R.raw.binders_2,
                R.raw.fmin_160bpm,
                R.raw.u_knwo_wut,
                R.raw.zombi,
        };
        int min = 0;
        int max = musiques.length;

        MediaPlayer mediaPlayer = MediaPlayer.create(ImageSelectionneeActivity.this,
                musiques[(int) (Math.random() * (max - min) + min)]);
        mediaPlayer.start();

        TextView captionText = findViewById(R.id.CaptionText);
        String selectionCaption = getIntent().getStringExtra("selection caption");
        captionText.setText(selectionCaption);

        ImageView imageSelectionnee = findViewById(R.id.Image_Selectionnee);
        byte[] imageByteArray = getIntent().getByteArrayExtra("image_byte");
        Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
        imageSelectionnee.setImageBitmap(imageBitmap);

        Button boutonPrecedent = findViewById(R.id.Bouton_Precedent);

        boutonPrecedent.setOnClickListener(v -> {
            mediaPlayer.stop();
            finish();
        });
    }
}
