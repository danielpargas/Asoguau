package com.interfaces.daniel.asoguau.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.interfaces.daniel.asoguau.R;
import com.interfaces.daniel.asoguau.libreria.MiJsonObjectRequest;
import com.interfaces.daniel.asoguau.libreria.VolleyAPI;
import com.interfaces.daniel.asoguau.utilidades.ProcesarImagen;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NuevaNoticia extends AppCompatActivity {

    private String encodedImage;
    private EditText titulo;
    private EditText descripcion;
    private Activity activity = this;


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
        titulo = (EditText) findViewById(R.id.texto_titulo);
        descripcion = (EditText) findViewById(R.id.texto_descripcion);



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
                    //Log.d("IMAGEN SELECCIONADA", path.getPath());

                    Bitmap imagen = null;

                    try {
                        imagen = MediaStore.Images.Media.getBitmap(getContentResolver(), path);

                        byte[] byteArray = ProcesarImagen.bitmapToArrayBytes(imagen);
                        ;

                        encodedImage = Base64.encodeToString(byteArray, Base64.NO_WRAP);
                        Log.d("IMAGEN", encodedImage);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //decodeBitmap(data.getDataString());
                    //ConvertitBase64(data.getDataString());
                    imageView.setImageBitmap(imagen);
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

        encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encodedImage;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nueva_noticia, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        final SharedPreferences preferences = getSharedPreferences("DatosUsuario", MODE_PRIVATE);


        switch (id) {
            case R.id.action_send:
                activity.finish();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "La noticia se esta enviando", Toast.LENGTH_LONG).show();
                    }
                }).run();
                VolleyAPI.getInstance(this).addToRequestQueue(new MiJsonObjectRequest(
                        Request.Method.POST,
                        VolleyAPI.URL_WEBSERVICE + VolleyAPI.URL_INSERTAR_NOTICIAS,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(activity, "Enviada con exito", Toast.LENGTH_LONG).show();
                                    }
                                }).run();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
//                                Log.d("ERROR JSON", error.getMessage());
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(activity, "Error al enviar la noticia", Toast.LENGTH_LONG).show();
                                    }
                                }).run();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parametros = new HashMap<String, String>();

                        parametros.put("idusuario", preferences.getString("idusuario", String.valueOf(0)));
                        parametros.put("titulo", titulo.getText().toString());
                        parametros.put("descripcion", descripcion.getText().toString());
                        parametros.put("imagen", encodedImage);

                        return parametros;
                    }
                });


                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
