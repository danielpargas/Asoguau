package com.interfaces.daniel.asoguau.utilidades;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/**
 * Created by hanyou on 27/10/15.
 */
public class ProcesarImagen {

    public static byte[] bitmapToArrayBytes(Bitmap imagen) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imagen.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }


}
