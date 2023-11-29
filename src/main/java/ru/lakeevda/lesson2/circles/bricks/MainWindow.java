package ru.lakeevda.lesson2.circles.bricks;

import ru.lakeevda.lesson2.circles.circles.Ball;
import ru.lakeevda.lesson2.circles.common.Background;
import ru.lakeevda.lesson2.circles.common.CanvasRepaintListener;
import ru.lakeevda.lesson2.circles.common.Interactable;
import ru.lakeevda.lesson2.circles.common.MainCanvas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame implements CanvasRepaintListener {
    private static final int POS_X = 200;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 600;
    private final java.util.List<Interactable> interactables = new ArrayList<>();
    private final List<Background> backgrounds = new ArrayList<>();

    private MainWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Bricks");
        backgrounds.add(new Background() {
                            @Override
                            public void update(MainCanvas mainCanvas, float deltaTime) {
                                super.update(mainCanvas, deltaTime);
                            }

                            @Override
                            public void render(MainCanvas mainCanvas, Graphics graphics) {
                                super.render(mainCanvas, graphics);
                            }
                        }
        );
        for (int i = 0; i < 10; i++) {
            interactables.add(new Brick());
        }


        MainCanvas mainCanvas = new MainCanvas(this);
        add(mainCanvas);
        setVisible(true);
    }

    public void onDrawFrame(MainCanvas mainCanvas, Graphics graphics, float deltaTime) {
        update(mainCanvas, deltaTime);
        render(mainCanvas, graphics);
    }

    private void update(MainCanvas mainCanvas, float deltaTime) {
        for (Background background : backgrounds) {
            background.update(mainCanvas, deltaTime);
        }
        for (Interactable interactable : interactables) {
            interactable.update(mainCanvas, deltaTime);
        }
    }

    private void render(MainCanvas mainCanvas, Graphics graphics) {
        for (Background background : backgrounds) {
            background.render(mainCanvas, graphics);
        }
        for (Interactable interactable : interactables) {
            interactable.render(mainCanvas, graphics);
        }
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
