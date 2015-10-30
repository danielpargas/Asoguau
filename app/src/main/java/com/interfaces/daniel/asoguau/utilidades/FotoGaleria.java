package com.interfaces.daniel.asoguau.utilidades;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by AndresLacruz on 28/10/2015.
 */
public class FotoGaleria extends AppCompatActivity {

    private static int Tomar_Foto = 1;
    private static int Selecionar_Foto = 2;
    private String Nombre;

    private String APP_DIRECTORY = "myPictureApp/";
    private String MEDIA_DIRECTORY = APP_DIRECTORY + "media";
    private String TEMPORAL_PICTURE_NAME = "temporal.jpg";


    private final int PHOTO_CODE = 100;
    private final int SELECT_PICTURE = 200;

    private ImageView imageView;

    public void Tomar_Foto(FloatingActionButton Accion, final Context Contexto) {
        Accion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] Opciones = {"Tomar foto", "Elegir de galeria", "Cancelar"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(Contexto);
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
        });
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


    //Funcion Pendite por validar.

    /*public Image Base64ToImage(String base64String)
    {
        // Convert Base64 String to byte[]

        byte[] imageBytes = ConvertitBase64() .FromBase64String(base64String);
        MemoryStream ms = new MemoryStream(imageBytes, 0,
                imageBytes.Length);

        // Convert byte[] to Image
        ms.Write(imageBytes, 0, imageBytes.Length);
        Image image = Image.FromStream(ms, true);
        return image;
    }*/
}