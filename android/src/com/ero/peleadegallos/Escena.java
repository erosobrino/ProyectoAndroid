package com.ero.peleadegallos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;

import java.io.IOException;

public class Escena {
    Context context;
    int idEscena;
    int anchoPantalla, altoPantalla;
    Bitmap fondo;

    Paint pBoton;
    Paint paintTextoBotones;//Paint para los textos de los botones
    Paint paintIconos;
    RectF volverMenu;
    Typeface fuenteTexto;
    Typeface fuenteIconos;

    boolean volumen;
    boolean vibracion;

    public Escena(Context context, int idEscena, int anchoPantalla, int altoPantalla, boolean volumen, boolean vibracion) {
        this.context = context;
        this.idEscena = idEscena;
        this.anchoPantalla = anchoPantalla;
        this.altoPantalla = altoPantalla;
        this.volumen = volumen;
        this.vibracion = vibracion;

        fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.suelo);
        fondo = Bitmap.createScaledBitmap(fondo, anchoPantalla, altoPantalla, false);

        pBoton = new Paint();
        pBoton.setColor(Color.GREEN);

        volverMenu = new RectF(0, 0, altoPantalla / 7, altoPantalla / 7);

        fuenteTexto = Typeface.createFromAsset(context.getAssets(), "fonts/Beelova.otf");
        paintTextoBotones = new Paint();
        paintTextoBotones.setTypeface(fuenteTexto);
        paintTextoBotones.setColor(Color.BLUE);

        fuenteIconos = Typeface.createFromAsset(context.getAssets(), "fonts/fa-solid-900.ttf");
        paintIconos = new Paint();
        paintIconos.setTypeface(fuenteIconos);
        paintIconos.setColor(Color.BLUE);
        paintIconos.setTextSize(altoPantalla / 8);
    }

    public int onTouchEvent(MotionEvent event) {
//        int pointerIndex = event.getActionIndex();        //Obtenemos el índice de la acción
//        int pointerID = event.getPointerId(pointerIndex); //Obtenemos el Id del pointer asociado a la acción
        int accion = event.getActionMasked();             //Obtenemos el tipo de pulsación
        switch (accion) {
//            case MotionEvent.ACTION_DOWN:           // Primer dedo toca
//            case MotionEvent.ACTION_POINTER_DOWN:  // Segundo y siguientes tocan
//                break;

            case MotionEvent.ACTION_UP:                     // Al levantar el último dedo
            case MotionEvent.ACTION_POINTER_UP:  // Al levantar un dedo que no es el último
                if (pulsa(volverMenu, event) && idEscena != 0) return 0;
                break;

//            case MotionEvent.ACTION_MOVE: // Se mueve alguno de los dedos
//
//                break;
//            default:
//                Log.i("Otra acción", "Acción no definida: " + accion);
        }

        return idEscena;
    }

    // Actualizamos la física de los elementos en pantalla
    public void actualizarFisica() {

    }

    // Rutina de dibujo en el lienzo. Se le llamará desde el hilo
    public void dibujar(Canvas c) {
        try {
            c.drawColor(Color.CYAN);
            c.drawBitmap(fondo, 0, 0, null);
            if (idEscena != 0) {
                Double posYHome = altoPantalla / 8.5;
                c.drawText(context.getText(R.string.home).toString(), 0, posYHome.floatValue(), paintIconos);
            }

        } catch (Exception e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
        }
    }

    public boolean pulsa(RectF boton, MotionEvent event) {
        if (boton.contains((int) (event.getX()), (int) (event.getY()))) {
            Log.i("boton", boton.left + "");
            return true;
        } else {
            return false;
        }
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setIdEscena(int idEscena) {
        this.idEscena = idEscena;
    }

    public void setAnchoPantalla(int anchoPantalla) {
        this.anchoPantalla = anchoPantalla;
    }

    public void setAltoPantalla(int altoPantalla) {
        this.altoPantalla = altoPantalla;
    }

    public void setFondo(Bitmap fondo) {
        this.fondo = fondo;
    }

    public Context getContext() {
        return context;
    }

    public int getIdEscena() {
        return idEscena;
    }

    public int getAnchoPantalla() {
        return anchoPantalla;
    }

    public int getAltoPantalla() {
        return altoPantalla;
    }

    public Bitmap getFondo() {
        return fondo;
    }
}
