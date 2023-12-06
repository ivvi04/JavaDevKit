package ru.lakeevda.lesson3.collection;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        var collection = new Collection<Integer>();
        Random random = new Random();

        System.out.print("Заполняем коллекцию: ");
        for (int i = 0; i < 15; ++i) {
            Integer item = random.nextInt(30);
            collection.add(item);
            System.out.print(item);
            System.out.print("  ");
        }
        System.out.println();
        System.out.print("Коллекция после заполнения: ");
        System.out.println(collection.toString());

        System.out.print("\nУдаляем элементы из коллекции: ");
        for (int i = 0; i < 5; ++i) {
            Integer item = random.nextInt(30);
            collection.removeAll(item);
            System.out.print(item);
            System.out.print("  ");
        }
        System.out.println();
        System.out.print("Коллекция после удаления: ");
        System.out.println(collection.toString());

        System.out.println();
        System.out.println("Выводим коллекцию с помощью итераторов: ");
        for (Integer item : collection) {
            System.out.print(item);
            System.out.print("  ");
        }
        System.out.println();
        var iter = collection.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next());
            System.out.print("  ");
        }
        System.out.println();
    }
}
