package com.avillegas.workers;

import com.avillegas.workers.Worker.Type;
import org.junit.Assert;
import org.junit.Test;

import static com.avillegas.workers.Worker.Type.*;


public class WorkersTest {

    Workers workers = new Workers();

    @Test
    public void testGetFirst() throws InterruptedException {
        Type[] expectedOrder = {OPERATOR, OPERATOR, SUPERVISOR, DIRECTOR};
        workers.add(new Worker(DIRECTOR));
        workers.add(new Worker(OPERATOR));
        workers.add(new Worker(SUPERVISOR));
        workers.add(new Worker(OPERATOR));
        Type[] workersType = getWorkersType(workers);
        Assert.assertArrayEquals(expectedOrder, workersType);
    }

    private Type[] getWorkersType(Workers workers) throws InterruptedException {
        Type[] ret = new Type[4];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = workers.getFirst().getType();
        }
        return ret;
    }
}
