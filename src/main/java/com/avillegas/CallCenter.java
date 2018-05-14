package com.avillegas;

import com.avillegas.dispatcher.Call;
import com.avillegas.dispatcher.Dispatcher;
import com.avillegas.workers.Worker;
import com.avillegas.workers.Workers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class CallCenter {
    private ExecutorService callsExecutor;
    private Dispatcher dispatcher;

    public CallCenter() {
        callsExecutor = Executors.newFixedThreadPool(10);
        dispatcher = new Dispatcher(initWorkers());
    }


    public Workers initWorkers() {
        Workers workers = new Workers();
        workers.add(new Worker(Worker.Type.OPERATOR));
        workers.add(new Worker(Worker.Type.OPERATOR));
        workers.add(new Worker(Worker.Type.OPERATOR));
        workers.add(new Worker(Worker.Type.OPERATOR));
        workers.add(new Worker(Worker.Type.SUPERVISOR));
        workers.add(new Worker(Worker.Type.SUPERVISOR));
        workers.add(new Worker(Worker.Type.DIRECTOR));
        return workers;

    }


    public void startCalls(int numberCalls) {
        IntStream.rangeClosed(1,numberCalls)
                .mapToObj(i -> new Call(i))
                .forEach(call -> {
                    callsExecutor.execute(() -> dispatcher.dispatchCall(call));
                });
        callsExecutor.shutdown();
    }
}
