package com.interfaces.daniel.asoguau.utilidades;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by hanyou on 11/10/15.
 */
public class DialogoCarga {

    private ProgressDialog cdialogo;
    private Context context;

    public DialogoCarga(Context context) {
        this.context = context;
    }

    public void mostrarDialogo(String mensaje) {

        cdialogo = new ProgressDialog(context);
        cdialogo.setMessage(mensaje);
        cdialogo.isIndeterminate();
        cdialogo.setCancelable(false);
        cdialogo.show();

    }

    public void ocultarDialogo() {
        if (cdialogo.isShowing()) {
            cdialogo.dismiss();
        }
    }


}
