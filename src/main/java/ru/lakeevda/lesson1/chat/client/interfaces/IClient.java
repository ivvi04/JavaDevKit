package ru.lakeevda.lesson1.chat.client.interfaces;

public interface IClient {
    void showMessage(String message);
    void disconnectFromServer();
}
