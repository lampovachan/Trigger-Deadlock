import java.util.concurrent.Semaphore;

/**
 * This class represents deadlock situation when the thread blocks because acquire() make thread wait for permission which is 0 forever.
 * It's the example of deadlock in not multithreading environment that's why it was not detected by Visual VM.
 * @author Svitlana Tkachuk
 */
public class SecondDeadlockExample {
    public static void main (String[] args) throws InterruptedException {
        new Semaphore(0).acquire();
    }
}
