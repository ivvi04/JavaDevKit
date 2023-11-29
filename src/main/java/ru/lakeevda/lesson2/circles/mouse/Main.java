package ru.lakeevda.lesson2.circles.mouse;

public class Main {
    public interface MouseListener {
        void mouseUp();
        void mouseDown();
    }

    public static class MouseAdapter implements MouseListener {
        @Override
        public void mouseUp() {

        }

        @Override
        public void mouseDown() {

        }
    }

    private static void addMouseListener(MouseListener mouseListener) {
        mouseListener.mouseDown();
        mouseListener.mouseUp();
    }

    public static void main(String[] args) {
        MouseAdapter mouseAdapter = new MouseAdapter();
        addMouseListener(mouseAdapter);
        addMouseListener(new MouseAdapter());
        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseUp() {

            }

            @Override
            public void mouseDown() {

            }
        };
        addMouseListener(mouseListener);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseUp() {

            }

            @Override
            public void mouseDown() {

            }
        });
    }
}
