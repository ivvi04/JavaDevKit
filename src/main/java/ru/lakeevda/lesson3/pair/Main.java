package ru.lakeevda.lesson3.pair;

public class Main {
    public static void main(String[] args) {
        PairClass<Integer, String> pair1 = new PairClass<>(12345, "Текст");
        System.out.println(pair1.getFirst());
        System.out.println(pair1.getSecond());
        System.out.println(pair1);
        PairRecord<Long, Float> pair2 = new PairRecord<>(24L, 12.34f);
        System.out.println(pair2.first());
        System.out.println(pair2.second());
        System.out.println(pair2);
        PairRecord<Integer, Double> pair3 = new PairRecord<>(null, null);
        System.out.println(pair3.first());
        System.out.println(pair3.second());
        System.out.println(pair3);
    }
}
