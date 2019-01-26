package com.ero.peleadegallos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

public class Escena {
    Context context;
    int idEscena;
    int anchoPantalla, altoPantalla;
    Bitmap fondo;
    Paint pTexto, pTexto2, pBoton, pBoton2;
    Rect rMenu;

    public Escena(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        this.context = context;
        this.idEscena = idEscena;
        this.anchoPantalla = anchoPantalla;
        this.altoPantalla = altoPantalla;
        pTexto = new Paint();
        pTexto2 = new Paint();
        pTexto.setColor(Color.RED);
        pTexto.setTextAlign(Paint.Align.CENTER);
        pTexto.setTextSize((int) (altoPantalla / 7 * 1.5));

        pTexto2.setTextAlign(Paint.Align.CENTER);
        pTexto2.setTextSize(altoPantalla / 5);
        pTexto2.setColor(Color.WHITE);

        pBoton = new Paint();
        pBoton.setColor(Color.GREEN);

        pBoton2 = new Paint();
        pBoton2.setColor(Color.CYAN);

        rMenu = new Rect(anchoPantalla - anchoPantalla / 7, 0, anchoPantalla, anchoPantalla / 7);
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
                if (pulsa(rMenu, event) && idEscena != 0) return 0;
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
            if (idEscena != 0) c.drawRect(rMenu, pBoton);
        } catch (Exception e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
        }
    }

    public boolean pulsa(Rect boton, MotionEvent event) {
        if (boton.contains((int) (event.getX()), (int) (event.getY()))) {
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
