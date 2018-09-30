package com.thread.readwritelock;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ReadWriteLockEx
{

}

class Buffer {

    private final int capacity;
    private final Deque<String> recent;
    private int discarded;

    public Buffer(int capacity) {
        this.capacity = capacity;
        this.recent = new ArrayDeque<>(capacity);
    }

    public void putItem(String item) {
        while (recent.size() >= capacity) {
            recent.removeFirst();
            ++discarded;
        }
        recent.addLast(item);
    }

    public List<String> getRecent() {
        final ArrayList<String> result = new ArrayList<>();
        result.addAll(recent);
        return result;
    }

    public int getDiscardedCount() {
        return discarded;
    }

    public int getTotal() {
        return discarded + recent.size();
    }

    public void flush() {
        discarded += recent.size();
        recent.clear();
    }

}
