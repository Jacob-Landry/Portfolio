package ca.limoilou.tp2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private Button boutonSuivant;
    private RelativeLayout rlayout;
    private Intent currentIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.imageView1 = (ImageView) findViewById(R.id.imageView1);
        this.imageView2 = (ImageView) findViewById(R.id.imageView2);
        this.imageView3 = (ImageView) findViewById(R.id.imageView3);
        this.imageView4 = (ImageView) findViewById(R.id.imageView4);
        this.boutonSuivant = (Button) findViewById(R.id.BoutonSuivant);
        this.rlayout = (RelativeLayout) findViewById(R.id.mainlayout);

        int[] images = new int[]{
                R.drawable.i1,
                R.drawable.i2,
                R.drawable.i3,
                R.drawable.i4,
                R.drawable.i5,
                R.drawable.i6,
                R.drawable.i7,
                R.drawable.i8,
                R.drawable.i9,
                R.drawable.i10,
                R.drawable.i11,
                R.drawable.i12,
                R.drawable.i13,
                R.drawable.i14,
        };

        rlayout.setOnClickListener(v -> {
            int min = 0;
            int max = images.length;

            imageView1.setImageResource(images[(int) (Math.random() * (max - min) + min)]);
            imageView2.setImageResource(images[(int) (Math.random() * (max - min) + min)]);
            imageView3.setImageResource(images[(int) (Math.random() * (max - min) + min)]);
            imageView4.setImageResource(images[(int) (Math.random() * (max - min) + min)]);
        });

        imageView1.setOnClickListener(v -> {
            deSelectionnerImage();
            imageView1.setSelected(true);
            imageView1.setBackgroundResource(R.drawable.image_border);


        });

        imageView2.setOnClickListener(v -> {
            deSelectionnerImage();
            imageView2.setSelected(true);
            imageView2.setBackgroundResource(R.drawable.image_border);

        });

        imageView3.setOnClickListener(v -> {
            deSelectionnerImage();
            imageView3.setSelected(true);
            imageView3.setBackgroundResource(R.drawable.image_border);

        });

        imageView4.setOnClickListener(v -> {
            deSelectionnerImage();
            imageView4.setSelected(true);
            imageView4.setBackgroundResource(R.drawable.image_border);
        });

        boutonSuivant.setOnClickListener(v -> {
            currentIntent = new Intent(v.getContext(), ImageSelectionneeActivity.class);

            if (imageView1.isSelected()) {
                putImageDansIntent(imageView1, "Vous avez sélectionnée la première image");
                startActivity(currentIntent);

            } else if (imageView2.isSelected()) {
                putImageDansIntent(imageView2, "Vous avez sélectionnée la deuxième image");
                startActivity(currentIntent);

            } else if (imageView3.isSelected()) {
                putImageDansIntent(imageView3, "Vous avez sélectionnée la troisième image");
                startActivity(currentIntent);

            } else if (imageView4.isSelected()) {
                putImageDansIntent(imageView4, "Vous avez sélectionnée la quatrième image");
                startActivity(currentIntent);

            } else {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(v.getContext());

                alertBuilder.setTitle("Attention!!!")
                        .setMessage("Aucune image n'a été selectionnée")
                        .setPositiveButton("OK", null)
                        .create().show();
            }
        });
    }

    private void deSelectionnerImage() {
        imageView1.setSelected(false);
        imageView2.setSelected(false);
        imageView3.setSelected(false);
        imageView4.setSelected(false);

        imageView1.setBackgroundResource(R.drawable.image_default);
        imageView2.setBackgroundResource(R.drawable.image_default);
        imageView3.setBackgroundResource(R.drawable.image_default);
        imageView4.setBackgroundResource(R.drawable.image_default);
    }

    private void putImageDansIntent(ImageView image, String caption) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
        currentIntent.putExtra("image_byte", bs.toByteArray());
        currentIntent.putExtra("selection caption", caption);
    }
}