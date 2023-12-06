package ru.lakeevda.lesson3.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

public class Collection<E> implements Iterable<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int EXPAND_FACTOR = 150;
    private static final int SHRINK_THRESH = 20;
    private static final int SHRINK_FACTOR = 60;
    private Object[] data;
    private int count;
    private int shrinkCountThresh;

    public Collection() {
        count = 0;
        data = new Object[DEFAULT_CAPACITY];
    }

    public E get(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException(index);
        }
        return (E) data[index];
    }

    public int count() {
        return count;
    }

    public boolean isEmpty() {
        return 0 == count;
    }

    public void add(E item) {
        expandIfNecessary(1);
        data[count] = item;
        ++count;
    }

    public void add(E[] items) {
        Objects.requireNonNull(items);
        if (items.length > 0) {
            expandIfNecessary(items.length);
            int i = 0;
            while (i < items.length) {
                data[count + i] = items[i];
                ++i;
            }
            count += items.length;
        }
    }

    public boolean removeFirst(E item) {
        int itemIndex = findFirst(item);
        if (itemIndex < 0) {
            return false;
        }
        int i = itemIndex;
        while (i < count - 1) {
            data[i] = data[i + 1];
            ++i;
        }
        --count;
        data[count] = null;
        shrinkIfNecessary();
        return true;
    }

    public void removeAll(E item) {
        while (removeFirst(item)) {}
    }

    private void expandIfNecessary(int countToAdd) {
        int requiredCapacity = count + countToAdd;
        if (requiredCapacity <= data.length) {
            return;
        }
        int newCapacity = EXPAND_FACTOR * requiredCapacity / 100 + requiredCapacity;
        data = Arrays.copyOf(data, newCapacity);
        actualizeThreshold();
    }

    private void shrinkIfNecessary() {
        if (count >= shrinkCountThresh) {
            return;
        }
        int newCapacity = SHRINK_FACTOR * data.length / 100;
        data = Arrays.copyOf(data, newCapacity);
        actualizeThreshold();
    }

    private void actualizeThreshold() {
        shrinkCountThresh = data.length * SHRINK_THRESH / 100;
    }

    private int findFirst(E item) {
        for (int i = 0; i < count; ++i) {
            if (Objects.equals(item, data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return Arrays.stream(data).limit(count).map(Objects::toString).collect(Collectors.joining(", ", "[", "]"));
    }

    @Override
    public Iterator<E> iterator() {
        return this.new IteratorCollection();
    }

    private class IteratorCollection implements Iterator<E> {
        int index = 0;

        @Override
        public boolean hasNext() {
            return index < count;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E item = (E) data[index];
            ++index;
            return item;
        }
    }
}
