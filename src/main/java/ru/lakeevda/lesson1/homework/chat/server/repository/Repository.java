package ru.lakeevda.lesson1.homework.chat.server.repository;

public interface Repository<T> {
    void writeMessages(T messages);
    T readMessages();
}
