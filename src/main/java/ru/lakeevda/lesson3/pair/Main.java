package ru.lakeevda.lesson3.pair;

public class Main {
    public static void main(String[] args) {
        var pair1 = new PairClass<>(12345, "Текст");
        System.out.println(pair1.getFirst());
        System.out.println(pair1.getSecond());
        System.out.println(pair1);
        var pair2 = new PairRecord<>(24L, 12.34f);
        System.out.println(pair2.first());
        System.out.println(pair2.second());
        System.out.println(pair2);
        var pair3 = new PairRecord<Integer, Double>(null, null);
        System.out.println(pair3.first());
        System.out.println(pair3.second());
        System.out.println(pair3);
    }
}
