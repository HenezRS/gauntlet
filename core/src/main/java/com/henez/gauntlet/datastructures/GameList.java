package com.henez.gauntlet.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class GameList<T> extends ArrayList<T> {

    public T random() {
        if (size() == 0)
            return null;
        return get(Numbers.nextIntBetween(0, size() - 1));
    }

    public T getOrNull(int i) {
        try {
            return get(i);
        } catch (IndexOutOfBoundsException ignored) {
            return null;
        }
    }

    public void addAll(T... item) {
        this.addAll(Arrays.asList(item));
    }

    public GameList<T> addAllAndReturn(T... item) {
        this.addAll(Arrays.asList(item));
        return this;
    }

    public T first() {
        return get(0);
    }

    public T last() {
        return get(size() - 1);
    }

    public void removeFirst() {
        remove(0);
    }

    public void reverse() {
        Collections.reverse(this);
    }

    public GameList<T> subList(int fromIndex, int toIndex) {
        if(fromIndex<0) {
            fromIndex = 0;
        }
        if(toIndex>size()) {
            toIndex = size();
        }
        return super.subList(fromIndex,toIndex).stream().collect(Collectors.toCollection(GameList::new));
    }
}
