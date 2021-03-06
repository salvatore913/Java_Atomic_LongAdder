import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

public class Main {
    private static final long SIZE = 20;
    private static final int LOW_BOUND = 10;
    private static final int HIGH_BOUND = 1000;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService =
                Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        LongAdder proceeds = new LongAdder();
        new Random().ints(SIZE, LOW_BOUND, HIGH_BOUND).boxed().collect(Collectors.toList())
                .forEach(i -> executorService.submit(() -> proceeds.add(i)));
        new Random().ints(SIZE, LOW_BOUND, HIGH_BOUND).boxed().collect(Collectors.toList())
                .forEach(i -> executorService.submit(() -> proceeds.add(i)));
        new Random().ints(SIZE, LOW_BOUND, HIGH_BOUND).boxed().collect(Collectors.toList())
                .forEach(i -> executorService.submit(() -> proceeds.add(i)));
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("\nОбщая выручка по трем магазинам составила: " + proceeds.sum());
        executorService.shutdown();
    }
}