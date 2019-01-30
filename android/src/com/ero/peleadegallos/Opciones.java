package com.ero.peleadegallos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;

class Opciones extends Escena {

    public Opciones(Context context, int nuevaEscena, int anchoPantalla, int altoPantalla) {
        super(context, nuevaEscena, anchoPantalla, altoPantalla);
    }

    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);
        if (volumen) {
            c.drawText(String.format("%-20s %2s", context.getText(R.string.volumen).toString(), context.getText(R.string.volumenON).toString()), anchoPantalla / 7, altoPantalla / 6, paintIconos);
        } else {
            c.drawText(String.format("%s %2s", context.getText(R.string.volumen).toString(), context.getText(R.string.volumenOFF).toString()), anchoPantalla / 7, altoPantalla / 6, paintIconos);
        }
        if (vibracion) {
            c.drawText(String.format("%-20s %2s", context.getText(R.string.vibrar).toString(), context.getText(R.string.vibrarON).toString()), anchoPantalla / 7, altoPantalla / 6 * 2, paintIconos);
        } else {
            c.drawText(String.format("%s %2s", context.getText(R.string.vibrar).toString(), context.getText(R.string.vibrarOFF).toString()), anchoPantalla / 7, altoPantalla / 6 * 2, paintIconos);
        }
    }
}
