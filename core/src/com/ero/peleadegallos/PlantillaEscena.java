package com.ero.peleadegallos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class PlantillaEscena implements Screen {

    public JuegoPrincipal juego;
    int anchoPantalla=Gdx.graphics.getWidth();
    int altoPantalla=Gdx.graphics.getHeight();

    public PlantillaEscena(JuegoPrincipal juego) {
        this.juego = juego;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
