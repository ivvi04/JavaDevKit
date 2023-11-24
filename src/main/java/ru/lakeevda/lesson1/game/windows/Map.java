package ru.lakeevda.lesson1.game.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Map extends JPanel {
    private static final Random RANDOM = new Random();
    private final int DOT_PADDING = 1;
    private int gameOverType;
    private final int STATE_DRAW = 0;
    private final int STATE_WIN_HUMAN = 1;
    private final int STATE_WIN_AI = 2;
    private final String MSG_WIN_HUMAN = "Победил игрок!";
    private final String MSG_WIN_AI = "Победил компьютер!";
    private final String MSG_DRAW = "Ничья!";
    private final int HUMAN_DOT = 1;
    private final int AI_DOT = 2;
    private final int EMPTY_DOT = 0;
    private int fieldSizeY;
    private int fieldSizeX;
    private int winLength;
    private int[][] field;
    private int panelWidth;
    private int panelHeight;
    private int cellHeight;
    private int cellWidth;
    private boolean isGameOver;
    private boolean isInitialized;

    public Map() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                update(e);
            }
        });
        isInitialized = false;
    }

    private void update(MouseEvent e) {
        if (isGameOver || !isInitialized) return;
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;
//        System.out.printf("x=%d, y=%d\n", cellX, cellY);
        if (!isValidCell(cellX, cellY) || isNotEmptyCell(cellX, cellY)) return;
        field[cellY][cellX] = HUMAN_DOT;
        if (checkEndGame(cellX, cellY, HUMAN_DOT, STATE_WIN_HUMAN)) return;
        aiTurn();
        repaint();
    }

    private boolean checkEndGame(int cellX, int cellY, int dot, int gameOverType) {
        if (checkWin(cellX, cellY, dot)) {
            this.gameOverType = gameOverType;
            isGameOver = true;
            repaint();
            return true;
        }
        if (isMapFull()) {
            this.gameOverType = STATE_DRAW;
            isGameOver = true;
            repaint();
            return true;
        }
        return false;
    }

    private void initMap() {
        field = new int[fieldSizeY][fieldSizeX];
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }

    public void startNewGame(int mode, int fSzX, int fSzY, int wLen) {
//        System.out.printf("Mode: %d;\nSize: x=%d, y=%d;\nWin Length: %d\n",
//                mode, fSzX, fSzY, wLen);
        fieldSizeX = fSzX;
        fieldSizeY = fSzY;
        winLength = wLen;
        initMap();
        isGameOver = false;
        isInitialized = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g) {
        if (!isInitialized) return;
        panelWidth = getWidth();
        panelHeight = getHeight();

        cellHeight = panelHeight / fieldSizeY;
        cellWidth = panelWidth / fieldSizeX;
        g.setColor(Color.BLACK);
        for (int h = 0; h < fieldSizeY; h++) {
            int y = h * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }
        for (int w = 0; w < fieldSizeX; w++) {
            int x = w * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == EMPTY_DOT) continue;
                if (field[y][x] == HUMAN_DOT) {
                    g.setColor(Color.BLUE);
                    g.fillOval(x * cellWidth + DOT_PADDING,
                            y * cellHeight + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2,
                            cellHeight - DOT_PADDING * 2);
                } else if (field[y][x] == AI_DOT) {
                    g.setColor(new Color(0xff0000));
                    g.fillOval(x * cellWidth + DOT_PADDING,
                            y * cellHeight + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2,
                            cellHeight - DOT_PADDING * 2);
                } else {
                    throw new RuntimeException("Unexpected value " + field[y][x] +
                            " in cell: x=" + x + " y=" + y);
                }
            }

        }
        if (isGameOver) showMessageGameOver(g);
    }

    private void showMessageGameOver(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 200, getWidth(), 70);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Times new roman", Font.BOLD, 48));
        switch (gameOverType) {
            case STATE_DRAW -> g.drawString(MSG_DRAW, 180, getHeight() / 2);
            case STATE_WIN_AI -> g.drawString(MSG_WIN_AI, 20, getHeight() / 2);
            case STATE_WIN_HUMAN -> g.drawString(MSG_WIN_HUMAN, 70, getHeight() / 2);
            default -> throw new RuntimeException("Unexpected gameOver state: " + gameOverType);
        }
    }

    private boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    private boolean isNotEmptyCell(int x, int y) {
        return field[y][x] != EMPTY_DOT;
    }

    private void aiTurn() {
//        if (turnAiWinCell()) return;
//        if (turnHumanWinCell()) return;
        int x, y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (isNotEmptyCell(x, y));
        field[y][x] = AI_DOT;
        /**
         * Добавить проверку выйгрыша после хода AI
         */
        if (checkEndGame(x, y, AI_DOT, STATE_WIN_AI)) return;
    }

    private boolean turnAiWinCell() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (!isNotEmptyCell(j, i)) {
                    field[i][j] = AI_DOT;
                    if (checkWin(i, j, AI_DOT)) return true;
                    field[i][j] = EMPTY_DOT;
                }
            }
        }
        return false;
    }

    private boolean turnHumanWinCell() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (!isNotEmptyCell(j, i)) {
                    field[i][j] = HUMAN_DOT;
                    if (checkWin(i, j, HUMAN_DOT)) {
                        field[i][j] = HUMAN_DOT;
                        return true;
                    }
                    field[i][j] = EMPTY_DOT;
                }
            }
        }
        return false;
    }

    private boolean checkWin(int cellX, int cellY, int c) {
        /**
         * Проверка по горизонтали
         */
        int cntWin = 1;
        for (int i = 1; cellX + i < fieldSizeX; i++) if (field[cellY][cellX + i] == c) cntWin++; else break;
        for (int i = 1; cellX - i >= 0; i++) if (field[cellY][cellX - i] == c) cntWin++; else break;
        if (cntWin >= winLength) return true;

        /**
         * Проверка по вертикали
         */
        cntWin = 1;
        for (int i = 1; cellY + i < fieldSizeY; i++) if (field[cellY + i][cellX] == c) cntWin++; else break;
        for (int i = 1; cellY - i >= 0; i++) if (field[cellY - i][cellX] == c) cntWin++; else break;
        if (cntWin >= winLength) return true;

        /**
         * Проверка по диагонали слева
         */
        cntWin = 1;
        for (int i = 1; cellY + i < fieldSizeY && cellX + i < fieldSizeX; i++) if (field[cellY + i][cellX + i] == c) cntWin++; else break;
        for (int i = 1; cellY - i >= 0 && cellX - i >= 0; i++) if (field[cellY - i][cellX - i] == c) cntWin++; else break;
        if (cntWin >= winLength) return true;

        /**
         * Проверка по диагонали справа
         */
        cntWin = 1;
        for (int i = 1; cellY - i >= 0 && cellX + i < fieldSizeX; i++) if (field[cellY - i][cellX + i] == c) cntWin++; else break;
        for (int i = 1; cellY + i < fieldSizeY && cellX - i >= 0; i++) if (field[cellY + i][cellX - i] == c) cntWin++; else break;
        if (cntWin >= winLength) return true;

        return false;
    }

    private boolean isMapFull() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field[i][j] == EMPTY_DOT) return false;
            }
        }
        return true;
    }
}
