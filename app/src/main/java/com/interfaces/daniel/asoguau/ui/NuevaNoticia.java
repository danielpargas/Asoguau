package com.interfaces.daniel.asoguau.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.interfaces.daniel.asoguau.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class NuevaNoticia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_noticia);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_close);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imageView = (ImageView) findViewById(R.id.imagen_noticia);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomarFoto();
            }
        });

        Glide.with(this)
                .load(R.drawable.noticias_redes)
                .centerCrop()
                .crossFade()
                .into(imageView);
    }


    private static int Tomar_Foto = 1;
    private static int Selecionar_Foto = 2;
    private String Nombre;

    private String APP_DIRECTORY = "myPictureApp/";
    private String MEDIA_DIRECTORY = APP_DIRECTORY + "media";
    private String TEMPORAL_PICTURE_NAME = "temporal.jpg";


    private final int PHOTO_CODE = 100;
    private final int SELECT_PICTURE = 200;

    private ImageView imageView;

    public void tomarFoto() {
        final CharSequence[] Opciones = {"Tomar foto", "Elegir de galeria", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Elige una opcion :");
        builder.setItems(Opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int seleccion) {
                if (Opciones[seleccion] == "Tomar foto") {
                    AbrirCamera();
                } else if (Opciones[seleccion] == "Elegir de galeria") {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent, "Selecciona app de imagen"), SELECT_PICTURE);
                } else if (Opciones[seleccion] == "Cancelar") {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    private void AbrirCamera() {
        File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        file.mkdirs();

        String path = Environment.getExternalStorageDirectory() + File.separator
                + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME;

        File newFile = new File(path);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
        startActivityForResult(intent, PHOTO_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case PHOTO_CODE:
                if (resultCode == RESULT_OK) {
                    String dir = Environment.getExternalStorageDirectory() + File.separator
                            + MEDIA_DIRECTORY + File.separator + TEMPORAL_PICTURE_NAME;
                    decodeBitmap(dir);
                    ConvertitBase64(dir);
                }
                break;

            case SELECT_PICTURE:
                if (resultCode == RESULT_OK) {
                    Uri path = data.getData();
                    imageView.setImageURI(path);
                }
                break;
        }

    }

    private void decodeBitmap(String dir) {
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeFile(dir);

        imageView.setImageBitmap(bitmap);
    }

    private String ConvertitBase64(String Url) {
        String Ruta = Url;
        File Imagen = new File(Ruta);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(Imagen);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Bitmap bm = BitmapFactory.decodeStream(fis);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();

        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encodedImage;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nueva_noticia, menu);
        return true;
    }

}
