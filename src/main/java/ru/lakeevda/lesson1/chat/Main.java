package ru.lakeevda.lesson1.chat;


import ru.lakeevda.lesson1.chat.client.windows.ClientWindow;
import ru.lakeevda.lesson1.chat.server.windows.ServerWindow;

/**
 * Создать простейшее окно управления сервером (по сути, любым), содержащее две кнопки
 * (JButton) – запустить сервер и остановить сервер. Кнопки должны просто логировать нажатие
 * (имитировать запуск и остановку сервера, соответственно) и выставлять внутри интерфейса
 * соответствующее булево isServerWorking.
 *
 * Создать окно клиента чата. Окно должно содержать JtextField для ввода логина, пароля,
 * IP-адреса сервера, порта подключения к серверу, область ввода сообщений, JTextArea
 * область просмотра сообщений чата и JButton подключения к серверу и отправки сообщения в чат.
 * Желательно сразу сгруппировать компоненты, относящиеся к серверу сгруппировать на JPanel
 * сверху экрана, а компоненты, относящиеся к отправке сообщения – на JPanel снизу
 */

public class Main {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow();
        new ClientWindow(serverWindow);
        new ClientWindow(serverWindow);
    }
}
