import java.util.concurrent.*;

/**
 * This class represents situation when deadlock occurs because CountDownLatch and ExecutorService will wait forever for each other.
 */

public class FifthDeadlockExample {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            Future<?> future = executorService.submit(() -> {
                System.out.println("generated task");
            });
            countDownLatch.countDown();
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}


