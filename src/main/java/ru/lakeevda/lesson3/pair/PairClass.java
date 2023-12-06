package ru.lakeevda.lesson3.pair;

public class PairClass<U, V> {
    private final U first;
    private final V second;

    public PairClass(U first, V second) {
        this.first = first;
        this.second = second;
    }

    public U getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return String.format("PairClass{first=%s, second=%s}",
                first,
                second);
    }
}
