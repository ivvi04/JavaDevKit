package ru.lakeevda.lesson2.circles.common;

import ru.lakeevda.lesson2.circles.common.Interactable;
import ru.lakeevda.lesson2.circles.common.MainCanvas;

import java.awt.*;

public abstract class Background implements Interactable {
    private float time;
    private static final float AMPLITUDE = 255f / 2f;
    private Color color;

    @Override
    public void update(MainCanvas mainCanvas, float deltaTime) {
        time += deltaTime;
        int red = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time * 2f));
        int green = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time * 3f));
        int blue = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time));
        color = new Color(red, green, blue);
    }

    @Override
    public void render(MainCanvas mainCanvas, Graphics graphics) {
        mainCanvas.setBackground(color);
    }
}
