package com.avillegas;

import com.avillegas.dispatcher.Call;
import com.avillegas.dispatcher.Dispatcher;
import com.avillegas.workers.Worker;
import com.avillegas.workers.Workers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/*
 *Class that initializes the cocurrency level of the application
 */
public class CallCenter {
    private ExecutorService callsExecutor;
    private Dispatcher dispatcher;

    /*
     *The executor service is created with 10 threads. In case more than 10 calls are started those will be put on
     *hold until other threads finish their work
     */
    public CallCenter() {
        callsExecutor = Executors.newFixedThreadPool(10);
        dispatcher = new Dispatcher(initWorkers());
    }

    /* Starts the workers of the application*/
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

    /*
     *Method that dispatch an specified number of calls to the dispatcher. In case there are more Calls than
     * the fixedThredPoolSize those will be put on hold until threads get free
     */
    public void startCalls(int numberCalls) {
        IntStream.rangeClosed(1,numberCalls)
                .mapToObj(Call::new)
                .forEach(call -> {
                    callsExecutor.execute(() -> dispatcher.dispatchCall(call));
                });
        callsExecutor.shutdown();
    }
}
