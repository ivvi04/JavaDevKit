package ru.lakeevda.lesson1.homework.chat.client.windows;

import ru.lakeevda.lesson1.homework.chat.client.classes.Client;
import ru.lakeevda.lesson1.homework.chat.client.interfaces.IClient;
import ru.lakeevda.lesson1.homework.chat.server.windows.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ClientWindow extends JFrame implements IClient {
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;
    JButton btnConnect = new JButton("Подключиться");
    JButton btnDisconnect = new JButton("Отключиться");
    JButton btnSend = new JButton("Отправить");
    JLabel lblLogin = new JLabel("Логин:");
    JLabel lblPassword = new JLabel("Пароль:");
    JLabel lblIP = new JLabel("IP:");
    JTextField txtFieldLogin = new JTextField("my_login");
    JTextField txtFieldPassword = new JTextField("my_password");
    JTextField txtFieldIP = new JTextField("127.0.0.1");
    JTextField txtFieldMessage = new JTextField();
    JTextArea areaMessage = new JTextArea();
    JPanel panLogin = new JPanel(new GridLayout(4, 2));
    JPanel panMessages = new JPanel(new GridLayout(1, 1));
    JPanel panClient = new JPanel(new GridLayout(2, 1));
    private Client client;

    public ClientWindow(ServerWindow server) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Клиент");
        setResizable(false);
        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect();
            }
        });
        btnDisconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disconnect();
            }
        });
        panLogin.add(lblLogin);
        panLogin.add(txtFieldLogin);
        panLogin.add(lblPassword);
        panLogin.add(txtFieldPassword);
        panLogin.add(lblIP);
        panLogin.add(txtFieldIP);
        panLogin.add(btnConnect);
        btnDisconnect.setEnabled(false);
        panLogin.add(btnDisconnect);
        add(panLogin, BorderLayout.NORTH);

        areaMessage.setEditable(false);
        panMessages.add(areaMessage);
        add(panMessages);

        panClient.add(txtFieldMessage);
        panClient.add(btnSend);
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        txtFieldMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
        });
        add(panClient, BorderLayout.SOUTH);
        client = new Client(this, server.getConnection());
        setVisible(true);
    }

    @Override
    public void showMessage(String msg) {
        areaMessage.append(msg);
    }

    @Override
    public void disconnectFromServer() {
        enableLoginPanel(true);
        client.disconnectFromServer();
    }

    public void enableLoginPanel(boolean enable) {
        txtFieldLogin.setEnabled(enable);
        txtFieldPassword.setEnabled(enable);
        txtFieldIP.setEnabled(enable);
        btnConnect.setEnabled(!btnConnect.isEnabled());
        btnDisconnect.setEnabled(!btnDisconnect.isEnabled());
    }

    public void connect() {
        if (client.connectToServer(txtFieldLogin.getText(),
                txtFieldPassword.getText(), txtFieldIP.getText())) {
            enableLoginPanel(false);
        }
    }

    public void disconnect() {
        client.disconnectFromServer();
    }

    private void sendMessage() {
        String message = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss")) + " " +
                txtFieldLogin.getText() + ": " +
                txtFieldMessage.getText() + "\n";
        client.sendMessage(message);
        txtFieldMessage.setText("");
    }

    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            disconnectFromServer();
        }
    }
}
