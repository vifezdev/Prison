package gg.convict.prison.position.workload;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class WorkloadRunnable implements Runnable {

    private static final double MAX_MILLIS_PER_TICK = 3.5;
    private static final int MAX_NANOS_PER_TICK = (int) (MAX_MILLIS_PER_TICK * 1E6);

    private final Deque<Workload> workloadDeque = new ArrayDeque<>();

    @Override
    public void run() {
        Workload nextWorkload;
        long stopTime = System.nanoTime() + MAX_NANOS_PER_TICK;

        while (System.nanoTime() < stopTime
                && (nextWorkload = workloadDeque.poll()) != null)
            nextWorkload.compute();
    }

    public void register(Workload... workloads) {
        workloadDeque.addAll(Arrays.asList(workloads));
    }

}