package ru.lakeevda.lesson1.homework.chat.server.windows;

import ru.lakeevda.lesson1.homework.chat.server.classes.Server;
import ru.lakeevda.lesson1.homework.chat.server.history.History;
import ru.lakeevda.lesson1.homework.chat.server.interfaces.IServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Создать простейшее окно управления сервером (по сути, любым), содержащее две кнопки
 * (JButton) – запустить сервер и остановить сервер. Кнопки должны просто логировать нажатие
 * (имитировать запуск и остановку сервера, соответственно) и выставлять внутри интерфейса
 * соответствующее булево isServerWorking.
 */
public class ServerWindow extends JFrame implements IServer {
    private static final int WINDOW_HEIGHT = 555;
    private static final int WINDOW_WIDTH = 507;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;
    JButton btnStart = new JButton("Запустить сервер");
    JButton btnStop = new JButton("Остановить сервер");
    JPanel panMessages = new JPanel(new GridLayout(1, 1));
    JPanel panButton = new JPanel(new GridLayout(1, 2));

    JTextArea areaLog;
    boolean isWorking;
    private Server server;

    public ServerWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Сервер");
        setResizable(false);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isWorking) {
                    isWorking = true;
                    btnStart.setEnabled(false);
                    btnStop.setEnabled(true);
                    server.start();
                }
            }
        });
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isWorking) {
                    isWorking = false;
                    btnStart.setEnabled(true);
                    btnStop.setEnabled(false);
                    server.stop();
                }
            }
        });
        areaLog = new JTextArea();
        panMessages.add(areaLog, BorderLayout.NORTH);
        add(panMessages);
        panButton.add(btnStart);
        btnStop.setEnabled(false);
        panButton.add(btnStop);
        add(panButton, BorderLayout.SOUTH);
        setVisible(true);
        server = new Server(this, new History());
    }

    public Server getConnection() {
        return server;
    }

    @Override
    public void showMessage(String msg) {
        areaLog.append(msg);
    }
}
