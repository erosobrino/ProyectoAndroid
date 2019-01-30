package com.ero.peleadegallos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

public class MenuInicio extends Escena {

    Rect ayuda, opciones, juego, logros;
    int alto, ancho, ancho2;
    String strJugar = context.getText(R.string.jugar).toString();
    String strOpciones = context.getText(R.string.opciones).toString();
    String strLogros = context.getText(R.string.logros).toString();
    String strAyuda = context.getText(R.string.ayuda).toString();
    String strCreditos = context.getText(R.string.creditos).toString();
    int xJugarText, xOpcionesText, xLogrosText, xAyudaText;
    int yJugarText, yOpcionesText, yLogrosText, yAyudaText;
    int tamañoPaint1, tamañoPaint2;
    Rect cajaTexto;

    public MenuInicio(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);
        alto = altoPantalla / 7;
        ancho = anchoPantalla / 7;

        tamañoPaint1 = altoPantalla / 5;
        tamañoPaint2 = altoPantalla / 20;

        juego = new Rect(ancho, alto, ancho * 6, alto * 3);
        opciones = new Rect(ancho, alto * 4, ancho * 2, alto * 5);
        logros = new Rect(ancho * 3, alto * 4, ancho * 4, alto * 5);
        ayuda = new Rect(ancho * 5, alto * 4, ancho * 6, alto * 5);

        cajaTexto = new Rect();//usado para medir tamaño de textos

        paintTextoBotones.setTextSize(tamañoPaint1);

        paintTextoBotones.getTextBounds(strJugar, 0, strJugar.length(), cajaTexto);
        xJugarText = juego.left + (juego.width() - cajaTexto.width()) / 2;
        yJugarText = juego.centerY() + (juego.height() - cajaTexto.height()) / 2;

        paintTextoBotones.setTextSize(tamañoPaint2);

        paintTextoBotones.getTextBounds(strOpciones, 0, strOpciones.length(), cajaTexto);
        xOpcionesText = opciones.left + (opciones.width() - cajaTexto.width()) / 2;
        yOpcionesText = opciones.centerY() + (opciones.height() - cajaTexto.height()) / 2;

        paintTextoBotones.getTextBounds(strLogros, 0, strLogros.length(), cajaTexto);
        xLogrosText = logros.left + (logros.width() - cajaTexto.width()) / 2;
        yLogrosText = logros.centerY() + (logros.height() - cajaTexto.height()) / 2;

        paintTextoBotones.getTextBounds(strAyuda, 0, strAyuda.length(), cajaTexto);
        xAyudaText = ayuda.left + (ayuda.width() - cajaTexto.width()) / 2;
        yAyudaText = ayuda.centerY() + (ayuda.height() - cajaTexto.height()) / 2;
    }


    // Actualizamos la física de los elementos en pantalla
    public void actualizarFisica() {

    }

    // Rutina de dibujo en el lienzo. Se le llamará desde el hilo
    public void dibujar(Canvas c) {
        try {
            super.dibujar(c);
            c.drawRect(juego, pBoton);
            c.drawRect(opciones, pBoton);
            c.drawRect(logros, pBoton);
            c.drawRect(ayuda, pBoton);

            paintTextoBotones.setTextSize(tamañoPaint1);
            c.drawText(strJugar, xJugarText, yJugarText, paintTextoBotones);

            paintTextoBotones.setTextSize(tamañoPaint2);
            c.drawText(strOpciones, xOpcionesText, yOpcionesText, paintTextoBotones);
            c.drawText(strLogros, xLogrosText, yLogrosText, paintTextoBotones);
            c.drawText(strAyuda, xAyudaText, yAyudaText, paintTextoBotones);


        } catch (Exception e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
        }
    }

    public int onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();        //Obtenemos el índice de la acción
        int pointerID = event.getPointerId(pointerIndex); //Obtenemos el Id del pointer asociado a la acción
        int accion = event.getActionMasked();             //Obtenemos el tipo de pulsación
        switch (accion) {
            case MotionEvent.ACTION_DOWN:           // Primer dedo toca
            case MotionEvent.ACTION_POINTER_DOWN:  // Segundo y siguientes tocan
                break;

            case MotionEvent.ACTION_UP:                     // Al levantar el último dedo
            case MotionEvent.ACTION_POINTER_UP:  // Al levantar un dedo que no es el último
                if (pulsa(juego, event)) return 1;
                else if (pulsa(opciones, event)) return 99;
                else if (pulsa(logros, event)) return 98;
                else if (pulsa(ayuda, event)) return 97;
                break;

            case MotionEvent.ACTION_MOVE: // Se mueve alguno de los dedos

                break;
            default:
                Log.i("Otra acción", "Acción no definida: " + accion);
        }

        return idEscena;
    }
}
