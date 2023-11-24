package ru.lakeevda.lesson1.homework.chat.server.history;

import ru.lakeevda.lesson1.homework.chat.server.repository.Repository;

import java.io.*;

public class History implements Repository<String> {
    private static final String LOG_FILE = "chat.log";
    private final File file = new File(LOG_FILE);

    @Override
    public void writeMessages(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readMessages() {
        if (file.exists()) {
            StringBuilder stringBuilder = new StringBuilder();
            try (FileReader reader = new FileReader(LOG_FILE)) {
                int c;
                while ((c = reader.read()) != -1) stringBuilder.append((char) c);
                stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
                return stringBuilder.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } return "";
    }
}
