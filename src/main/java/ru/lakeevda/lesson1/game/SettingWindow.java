package ru.lakeevda.lesson1.game;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Первое задание – добавить на экран компоновщик-сетку с одним столбцом и добавить над существующее
 * кнопкой следующие компоненты в заданном порядке: JLabel с заголовком «Выберите режим игры»,
 * сгруппированные в ButtonGroup переключаемые JRadioButton
 * с указанием режимов «Человек против компьютера» и «Человек против человека»,
 * JLabel с заголовком «Выберите размеры поля», JLabel с заголовком «Установленный размер поля:»,
 * JSlider со значениями 3..10, JLabel с заголовком «Выберите длину для победы»,
 * JLabel с заголовком «Установленная длина:», JSlider со значениями 3..10.
 */

/**
 * Добавить компонентам интерактивности, а именно, при перемещении ползунка слайдера
 * в соответствующих лейблах должны появляться текущие значения слайдеров.
 * Для этого необходимо добавить к слайдеру слушателя изменений (как это было сделано для действия кнопки).
 */

/**
 * В методе обработчика нажатия кнопки необходимо заменить константы в аргументе
 * вызова метода старта игры на текущие показания компонентов (какая радио-кнопка
 * активна, значение слайдера размеров поля, значение слайдера выигрышной длины).
 */

public class SettingWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 300;
    private static final int WINDOW_WIDTH = 350;
    private static final int INIT_VALUE = 3;
    JButton btnStart = new JButton("Новая игра");
    JLabel lblGameMode = new JLabel("Режим игры");
    JLabel lblHeight = new JLabel("Установленная ширина: " + INIT_VALUE);
    JLabel lblWidth = new JLabel("Установленная высота: " + INIT_VALUE);
    JLabel lblWinLength = new JLabel("Установленная длина победы: " + INIT_VALUE);
    JSlider sliderFieldHeight = new JSlider(3, 10);
    JSlider sliderFieldWidth = new JSlider(3, 10);
    JSlider sliderWinLength = new JSlider(3, 10);
    JRadioButton humanVsAi = new JRadioButton("Человек против компьютера");
    JRadioButton humanVsHuman = new JRadioButton("Человек против человека");
    ButtonGroup gameModeButtonsGroup = new ButtonGroup();
    JPanel panBottom, panEdit;

    public SettingWindow(GameWindow gameWindow) {
        setLocationRelativeTo(gameWindow);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Настройки");
        setResizable(false);
        panEdit = new JPanel(new GridLayout(0, 1));
        gameModeButtonsGroup.add(humanVsAi);
        gameModeButtonsGroup.add(humanVsHuman);
        panEdit.add(lblGameMode);
        panEdit.add(humanVsAi);
        panEdit.add(humanVsHuman);
        sliderFieldHeight.setValue(INIT_VALUE);
        sliderFieldWidth.setValue(INIT_VALUE);
        sliderWinLength.setValue(INIT_VALUE);
        panEdit.add(lblHeight);
        panEdit.add(sliderFieldHeight);
        panEdit.add(lblWidth);
        panEdit.add(sliderFieldWidth);
        panEdit.add(lblWinLength);
        panEdit.add(sliderWinLength);
        humanVsAi.setSelected(true);
        sliderFieldHeight.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lblHeight.setText("Установленная ширина: " + sliderFieldHeight.getValue());
            }
        });
        sliderFieldWidth.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lblWidth.setText("Установленная высота: " + sliderFieldWidth.getValue());
            }
        });
        sliderWinLength.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lblWinLength.setText("Установленная длина победы: " + sliderWinLength.getValue());
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.startNewGame(humanVsAi.isSelected()?0:1,
                        sliderFieldWidth.getValue(),
                        sliderFieldHeight.getValue(),
                        sliderWinLength.getValue());
                setVisible(false);
            }
        });
        add(panEdit, BorderLayout.NORTH);
        panBottom = new JPanel(new GridLayout(1, 1));
        panBottom.add(btnStart);
        add(panBottom, BorderLayout.SOUTH);
    }
}
