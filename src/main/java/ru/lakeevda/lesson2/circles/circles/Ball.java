package ru.lakeevda.lesson2.circles.circles;

import ru.lakeevda.lesson2.circles.common.Sprite;
import ru.lakeevda.lesson2.circles.common.MainCanvas;

import java.awt.*;
import java.util.Random;

public class Ball extends Sprite {
    private static Random random = new Random();
    private final Color color;
    private float vX;
    private float vY;

    public Ball() {
        halfHeight = 20 + (float) (Math.random() * 50f);
        halfWidth = halfHeight;
        color = new Color(random.nextInt());
        vX = 100f + (float) (Math.random() * 200f);
        vY = 100f + (float) (Math.random() * 200f);
    }

    @Override
    public void update(MainCanvas mainCanvas, float deltaTime) {
        x += vX * deltaTime;
        y += vY * deltaTime;
        if (getLeft() < mainCanvas.getLeft()) {
            setLeft(mainCanvas.getLeft());
            vX = -vX;
        }
        if (getRight() > mainCanvas.getRight()) {
            setRight(mainCanvas.getRight());
            vX = -vX;
        }
        if (getTop() < mainCanvas.getTop()) {
            setTop(mainCanvas.getTop());
            vY = -vY;
        }
        if (getBottom() > mainCanvas.getBottom()) {
            setBottom(mainCanvas.getBottom());
            vY = -vY;
        }
    }

    @Override
    public void render(MainCanvas mainCanvas, Graphics graphics) {
        graphics.setColor(color);
        graphics.fillOval((int) getLeft(), (int) getTop(),
                (int) getWidth(), (int) getHeight());
    }
}
