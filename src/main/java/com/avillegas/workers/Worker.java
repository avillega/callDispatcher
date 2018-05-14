package com.avillegas.workers;

public class Worker implements Comparable<Worker> {
    private Type type;

    public Worker(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public int compareTo(Worker o) {
        return Integer.compare(this.type.priority, o.type.priority);
    }

    public enum Type {
        OPERATOR(0), SUPERVISOR(1), DIRECTOR(2);

        private final int priority;
        Type(int priority) {
            this.priority = priority;
        }


    }
}
