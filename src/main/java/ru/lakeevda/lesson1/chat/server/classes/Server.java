package ru.lakeevda.lesson1.chat.server.classes;

import ru.lakeevda.lesson1.chat.client.classes.Client;
import ru.lakeevda.lesson1.chat.server.interfaces.IServer;
import ru.lakeevda.lesson1.chat.server.repository.Repository;

import java.util.ArrayList;
import java.util.List;


public class Server {
    private boolean isWork;
    private final IServer server;
    private final List<Client> clientList;
    private final Repository repository;

    public Server(IServer server, Repository repository){
        this.server = server;
        this.repository = repository;
        clientList = new ArrayList<>();
    }

    public void start(){
        if (isWork){
            showMessage("Сервер уже запущен.");
        } else {
            isWork = true;
            showMessage("Сервер запущен.");
        }
    }

    public void stop(){
        if (!isWork){
            showMessage("Сервер уже остановлен.");
        } else {
            isWork = false;
            for(Client client : clientList){
                disconnectUser(client);
            }
            showMessage("Сервер остановлен.");
        }
    }

    public boolean connectUser(Client client){
        if (!isWork) return false;
        clientList.add(client);
        showMessage(client.getLogin() + " подключился к беседе.");
        return true;
    }

    public void disconnectUser(Client client){
        if (clientList.contains(client)) {
            clientList.remove(client);
            if (client != null) {
                client.disconnectFromServer();
                showMessage(client.getLogin() + " отключился от беседы.");
            }
        }
    }

    public void sendMessage(String message){
        if (!isWork){
            return;
        }
        showMessage(message);
        answerAll(message);
        saveInHistory(message);
    }

    public String getHistory(){
        return repository.readMessages().toString();
    }

    private void answerAll(String message){
        for (Client client: clientList){
            System.out.println(client);
            client.answerFromServer(message);
        }
    }

    private void showMessage(String message){
        server.showMessage(message + "\n");
    }

    private void saveInHistory(String messages){
        repository.writeMessages(messages);
    }
}
