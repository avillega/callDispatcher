package com.avillegas.workers;

import com.avillegas.workers.Worker;

import java.util.concurrent.PriorityBlockingQueue;

/*
* Wrapper over a PriorityBlockingQueue of Worker
*/
public class Workers {
    private PriorityBlockingQueue<Worker> workers = new PriorityBlockingQueue<>();

    public void add(Worker worker) {
        workers.add(worker);
    }

    public Worker getFirst() throws InterruptedException {
        return workers.take();
    }

    public boolean isEmpty() {
        return workers.isEmpty();
    }
}
