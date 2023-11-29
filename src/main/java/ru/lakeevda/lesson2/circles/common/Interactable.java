package ru.lakeevda.lesson2.circles.common;

import java.awt.*;

public interface Interactable {
    void update(MainCanvas mainCanvas, float deltaTime);
    void render(MainCanvas mainCanvas, Graphics graphics);
}
