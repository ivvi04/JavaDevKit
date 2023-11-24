package ru.lakeevda.lesson1.chat.client.classes;


import ru.lakeevda.lesson1.chat.client.interfaces.IClient;
import ru.lakeevda.lesson1.chat.server.classes.Server;

public class Client {
    private boolean connected;
    String login;
    String password;
    String ip;
    private final IClient client;
    private final Server server;

    public Client(IClient client, Server server) {
        this.client = client;
        this.server = server;
    }

    public boolean connectToServer(String login, String password, String ip) {
        this.login = login;
        this.password = password;
        this.ip = ip;
        if (server.connectUser(this)) {
            showMessage("Вы успешно подключились!\n");
            connected = true;
            String log = server.getHistory();
            if (log != null) {
                showMessage(log);
            }
            return true;
        } else {
            showMessage("Подключение не удалось");
            return false;
        }
    }

    public void disconnectFromServer() {
        if (connected) {
            connected = false;
            client.disconnectFromServer();
            server.disconnectUser(this);
            showMessage("Вы отключены от сервера!");
        }
    }

    public void answerFromServer(String message) {
        showMessage(message);
    }

    public void sendMessage(String message) {
        if (connected) {
            if (!message.isEmpty()) {
                server.sendMessage(login + ": " + message);
            }
        } else {
            showMessage("Нет подключения к серверу");
        }
    }

    public String getLogin() {
        return login;
    }

    public String getIp() {
        return ip;
    }

    private void showMessage(String message) {
        client.showMessage(message + "\n");
    }
}
