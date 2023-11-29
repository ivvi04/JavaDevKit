package ru.lakeevda.lesson2.circles.bricks;

import ru.lakeevda.lesson2.circles.common.MainCanvas;
import ru.lakeevda.lesson2.circles.common.Sprite;

import java.awt.*;
import java.util.Random;

public class Brick extends Sprite {
    private static Random random = new Random();
    private final Color color;
    private float vX;
    private float vY;

    public Brick() {
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
        graphics.drawRect((int) getLeft(), (int) getTop(),
                (int) getWidth(), (int) getHeight());
    }
}
