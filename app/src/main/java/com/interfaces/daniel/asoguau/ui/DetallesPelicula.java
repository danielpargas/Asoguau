package com.interfaces.daniel.asoguau.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.interfaces.daniel.asoguau.R;
import com.interfaces.daniel.asoguau.libreria.VolleyAPI;
import com.interfaces.daniel.asoguau.modelo.Noticia;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by hanyou on 17/09/15.
 */
public class DetallesPelicula extends AppCompatActivity {


    private static final String EXTRA_NOMBRE = "com.interfaces.daniel.asoguau.toolbarapp.name";
    private static final String EXTRA_DESCRIPCION = "com.interfaces.daniel.asoguau.toolbarapp.descripcion";
    private static final String EXTRA_HORARIO = "com.interfaces.daniel.asoguau.toolbarapp.horario";
    private static final String EXTRA_DRAWABLE = "com.interfaces.daniel.asoguau.drawable";

    private String nombre;

    /**
     * Inicia una nueva instancia de la actividad
     *
     * @param activity Contexto desde donde se lanzará
     * @param title    Item a procesar
     */
    public static void createInstance(Activity activity, Noticia title) {
        Intent intent = getLaunchIntent(activity, title);
        activity.startActivity(intent);
    }

    /**
     * Construye un Intent a partir del contexto y la actividad
     * de detalle.
     *
     * @param context  Contexto donde se inicia
     * @param noticia Identificador de la chica
     * @return Intent listo para usar
     */
    public static Intent getLaunchIntent(Context context, Noticia noticia) {
        Intent intent = new Intent(context, DetallesPelicula.class);
        intent.putExtra(EXTRA_NOMBRE, noticia.getTitulo());
        intent.putExtra(EXTRA_DESCRIPCION, noticia.getDescripcion());
        //Arrays.copyOf(noticia.getHorarios().toArray(), noticia.getHorarios().toArray().length, String[].class);
        //intent.putExtra(EXTRA_HORARIO, Arrays.copyOf(noticia.getHorarios().toArray(), noticia.getHorarios().toArray().length, String[].class));
        intent.putExtra(EXTRA_DRAWABLE, noticia.getIdnoticia());
        return intent;
    }

    private Activity activity;

    private static final String PERMISSION = "publish_actions";
    private static final Location SEATTLE_LOCATION = new Location("") {
        {
            setLatitude(47.6097);
            setLongitude(-122.3331);
        }
    };

    private final String PENDING_ACTION_BUNDLE_KEY =
            "com.example.hellofacebook:PendingAction";

    private Button postStatusUpdateButton;
    private Button postPhotoButton;
    private ProfilePictureView profilePictureView;
    private TextView greeting;
    private PendingAction pendingAction = PendingAction.NONE;
    private boolean canPresentShareDialog;
    private boolean canPresentShareDialogWithPhotos;
    private ProfileTracker profileTracker;


    private enum PendingAction {
        NONE,
        POST_PHOTO,
        POST_STATUS_UPDATE
    }

    private FacebookCallback<Sharer.Result> shareCallback = new FacebookCallback<Sharer.Result>() {
        @Override
        public void onSuccess(Sharer.Result result) {

        }

        @Override
        public void onCancel() {
            Log.d("HelloFacebook", "Canceled");
        }

        @Override
        public void onError(FacebookException error) {
            Log.d("HelloFacebook", String.format("Errores: %s", error.toString()));
            String title = getString(R.string.error);
            String alertMessage = error.getMessage();
            showResult(title, alertMessage);
        }

        private void showResult(String title, String alertMessage) {
            new AlertDialog.Builder(DetallesPelicula.this)
                    .setTitle(title)
                    .setMessage(alertMessage)
                    .setPositiveButton(R.string.ok, null)
                    .show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalles_pelicula);


        FacebookSdk.sdkInitialize(this.getApplicationContext());

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        handlePendingAction();
                        updateUI();
                    }

                    @Override
                    public void onCancel() {
                        if (pendingAction != PendingAction.NONE) {
                            showAlert();
                            pendingAction = PendingAction.NONE;
                        }
                        updateUI();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        if (pendingAction != PendingAction.NONE
                                && exception instanceof FacebookAuthorizationException) {
                            showAlert();
                            pendingAction = PendingAction.NONE;
                        }
                        updateUI();
                    }

                    private void showAlert() {
                        new AlertDialog.Builder(DetallesPelicula.this)
                                .setTitle(R.string.cancelled)
                                .setMessage(R.string.permission_not_granted)
                                .setPositiveButton(R.string.ok, null)
                                .show();
                    }
                });

        shareDialog = new ShareDialog(this);
        shareDialog.registerCallback(
                callbackManager,
                shareCallback);

        if (savedInstanceState != null) {
            String name = savedInstanceState.getString(PENDING_ACTION_BUNDLE_KEY);
            pendingAction = PendingAction.valueOf(name);
        }

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                updateUI();
                // It's possible that we were waiting for Profile to be populated in order to
                // post a status update.
                handlePendingAction();
            }
        };

        //profilePictureView = (ProfilePictureView) findViewById(R.id.profilePicture);
        //greeting = (TextView) findViewById(R.id.greeting);

        //      postStatusUpdateButton = (Button) findViewById(R.id.postStatusUpdateButton);
        //    postStatusUpdateButton.setOnClickListener(new View.OnClickListener() {
        //public void onClick(View view) {
        //      onClickPostStatusUpdate();
        //    }
        //  });

        //     postPhotoButton = (Button) findViewById(R.id.postPhotoButton);
        //   postPhotoButton.setOnClickListener(new View.OnClickListener() {
        //     public void onClick(View view) {
        //       onClickPostPhoto();
        // }
        //});

        // Can we present the share dialog for regular links?
        canPresentShareDialog = ShareDialog.canShow(
                ShareLinkContent.class);

        // Can we present the share dialog for photos?
        canPresentShareDialogWithPhotos = ShareDialog.canShow(
                SharePhotoContent.class);


        activity = this;
        setToolbar();// Añadir action bar
        if (getSupportActionBar() != null) // Habilitar up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        nombre = i.getStringExtra(EXTRA_NOMBRE);
        String descripcion = i.getStringExtra(EXTRA_DESCRIPCION);
        idDrawable = i.getIntExtra(EXTRA_DRAWABLE, -1);


        TextView textViewDescripcion = (TextView) findViewById(R.id.descripcion_completa_pelicula);
        textViewDescripcion.setText(descripcion);

        TextView textViewHorario = (TextView) findViewById(R.id.horario_pelicula);

        CardView cardView = (CardView) findViewById(R.id.horario);
        cardView.setVisibility(View.INVISIBLE);

        textViewHorario.setText("");

        CollapsingToolbarLayout collapser =
                (CollapsingToolbarLayout) findViewById(R.id.collapser);
        collapser.setTitle(nombre); // Cambiar título

        loadImageParallax(idDrawable);// Cargar Imagen
        Log.d("IDNOTICIA", String.valueOf(idDrawable));

        // Setear escucha al FAB
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showSnackBar("Compra");
                        ProcesarCompra.createInstance((Activity) v.getContext(), nombre);
                    }
                }
        );


    }

    private int idDrawable;

    private void updateUI() {
        boolean enableButtons = AccessToken.getCurrentAccessToken() != null;

        postStatusUpdateButton.setEnabled(enableButtons || canPresentShareDialog);
        postPhotoButton.setEnabled(enableButtons || canPresentShareDialogWithPhotos);

        Profile profile = Profile.getCurrentProfile();
        if (enableButtons && profile != null) {
            profilePictureView.setProfileId(profile.getId());
            greeting.setText(getString(R.string.hello_user, profile.getFirstName()));
        } else {
            profilePictureView.setProfileId(null);
            greeting.setText(null);
        }
    }

    private void setToolbar() {
        // Añadir la Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });

    }

    /**
     * Se carga una imagen aleatoria para el detalle
     */
    private ImageView imagen;
    private void loadImageParallax(int id) {
        imagen = (ImageView) findViewById(R.id.image_paralax);
        // Usando Glide para la carga asíncrona
        Glide.with(this)
                .load(VolleyAPI.URL_CARPETA_IMAGENES_NOTICIAS + "/" + id + ".jpg")
                .centerCrop()
                .into(imagen);
    }

    /**
     * Proyecta una {@link Snackbar} con el string usado
     *
     * @param msg Mensaje
     */
    private void showSnackBar(String msg) {
        Snackbar
                .make(findViewById(R.id.coordinator), msg, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detalles_pelicula, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                showSnackBar("Se abren los ajustes");
                //Bitmap image = Bit
                //SharePhoto photo = new SharePhoto.Builder()
                //      .setBitmap(image)
                //    .build();
                return true;
            /*
            case R.id.action_add:
                showSnackBar("Añadir a contactos");
                return true;
            case R.id.action_favorite:
                showSnackBar("Añadir a favoritos");
                return true;
                */

            case R.id.action_share:

                Log.d("hola", "hola");
//
// share();
                onClickPostPhoto();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }


    CallbackManager callbackManager;
    ShareDialog shareDialog;


    public void share() {
        Bundle bundle = new Bundle();
        bundle.putString("caption", "Hola");
        bundle.putString("decription", "Hola");
        bundle.putString("link", "https://wwww.google.com");
        bundle.putString("name", "Hola");
        bundle.putString("picture", "https://shush.mobi/bla.png");

        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle("Hola")
                    .setContentDescription("Holasdadada")
                    .setContentUrl(Uri.parse("https://www.google.comm"))
                    .build();

            shareDialog.show(linkContent);

        }


    }

    private void onClickPostPhoto() {
        performPublish(PendingAction.POST_PHOTO, canPresentShareDialogWithPhotos);
    }

    private void postPhoto() {
        //Bitmap image = BitmapFactory.decodeResource(this.getResources(), idDrawable);

        Bitmap image = ((GlideBitmapDrawable) imagen.getDrawable()).getBitmap();

        SharePhoto sharePhoto = new SharePhoto.Builder().setBitmap(image).build();
        ArrayList<SharePhoto> photos = new ArrayList<>();
        photos.add(sharePhoto);

        SharePhotoContent sharePhotoContent =
                new SharePhotoContent.Builder().setPhotos(photos).build();
        if (canPresentShareDialogWithPhotos) {
            shareDialog.show(sharePhotoContent);
        } else if (hasPublishPermission()) {
            ShareApi.share(sharePhotoContent, shareCallback);
        } else {
            pendingAction = PendingAction.POST_PHOTO;
            // We need to get new permissions, then complete the action when we get called back.
            LoginManager.getInstance().logInWithPublishPermissions(
                    this,
                    Arrays.asList(PERMISSION));
        }
    }

    private boolean hasPublishPermission() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null && accessToken.getPermissions().contains("publish_actions");
    }

    private void performPublish(PendingAction action, boolean allowNoToken) {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null || allowNoToken) {
            pendingAction = action;
            handlePendingAction();
        }
    }

    private void handlePendingAction() {
        PendingAction previouslyPendingAction = pendingAction;
        // These actions may re-set pendingAction if they are still pending, but we assume they
        // will succeed.
        pendingAction = PendingAction.NONE;

        switch (previouslyPendingAction) {
            case NONE:
                break;
            case POST_PHOTO:
                postPhoto();
                break;
            case POST_STATUS_UPDATE:
                //postStatusUpdate();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}