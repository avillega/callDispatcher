package com.avillegas.dispatcher;

import com.avillegas.workers.Worker;
import com.avillegas.workers.Workers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;

public class Dispatcher {

    private Logger logger = LoggerFactory.getLogger(Dispatcher.class);
    private Workers workers;

    public Dispatcher(Workers workers) {
        this.workers = workers;
    }

    /*
    *method that dispatch a call to one of the available workers. In case no worker is available
    *it blocks until one gets available.
    */
    public void dispatchCall(Call call){
        try {
            long millisToSleep = ThreadLocalRandom.current().nextInt(5000, 10000);
            Worker worker = getWorker(call);
            Thread.sleep(millisToSleep);
            logger.info("Call {} ended after {} milliseconds attended by a {} in thread {}",
                    call.getId(), millisToSleep, worker.getType().name() ,Thread.currentThread().getName());
            workers.add(worker);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private Worker getWorker(Call call) throws InterruptedException {
        long startWaitingForWorker = System.currentTimeMillis();
        Worker worker = workers.getFirst();
        long endWaitingForWorker = System.currentTimeMillis();
        logger.info("Call {} waited {} milliseconds for a {} to be available",
                call.getId() ,endWaitingForWorker-startWaitingForWorker, worker.getType().name());
        return worker;
    }
}
