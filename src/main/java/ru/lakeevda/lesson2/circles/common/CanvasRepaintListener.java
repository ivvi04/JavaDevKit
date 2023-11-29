package ru.lakeevda.lesson2.circles.common;

import java.awt.*;

public interface CanvasRepaintListener {
    void onDrawFrame(MainCanvas mainCanvas, Graphics graphics, float deltaTime);
}
