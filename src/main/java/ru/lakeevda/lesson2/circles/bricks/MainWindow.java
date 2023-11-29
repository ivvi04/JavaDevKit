package ru.lakeevda.lesson2.circles.bricks;

import ru.lakeevda.lesson2.circles.common.Background;
import ru.lakeevda.lesson2.circles.common.CanvasRepaintListener;
import ru.lakeevda.lesson2.circles.common.Interactable;
import ru.lakeevda.lesson2.circles.common.MainCanvas;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame implements CanvasRepaintListener {
    private static final int POS_X = 200;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 600;
    private final Interactable[] interactables = new Interactable[10];

    private MainWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Bricks");
        interactables[0] = new Background() {
            @Override
            public void update(MainCanvas mainCanvas, float deltaTime) {
                super.update(mainCanvas, deltaTime);
            }

            @Override
            public void render(MainCanvas mainCanvas, Graphics graphics) {
                super.render(mainCanvas, graphics);
            }
        };
        for (int i = 1; i < interactables.length; i++) {
            interactables[i] = new Brick();
        }


        MainCanvas mainCanvas = new MainCanvas(this);
        add(mainCanvas);
        setVisible(true);
    }

//    @Override
    public void onDrawFrame(MainCanvas mainCanvas, Graphics graphics, float deltaTime) {
        update(mainCanvas, deltaTime);
        render(mainCanvas, graphics);
    }

    private void update(MainCanvas mainCanvas, float deltaTime) {
//        interactables[1].update(mainCanvas, deltaTime);
        for (int i = 0; i < interactables.length; i++) {
            interactables[i].update(mainCanvas, deltaTime);
        }
    }

    private void render(MainCanvas mainCanvas, Graphics graphics) {
//        interactables[1].render(mainCanvas, graphics);
        for (int i = 0; i < interactables.length; i++) {
            interactables[i].render(mainCanvas, graphics);
        }
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
