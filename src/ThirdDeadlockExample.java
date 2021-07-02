/**
 * This class represents deadlock situation when we make thread waiting for itself in another thread.
 * @author Svitlana Tkachuk
 */

public class ThirdDeadlockExample {
    public synchronized static void deadlock() {
        try {
            Thread t = new Thread(ThirdDeadlockExample::deadlock);
            t.start();
            t.join();
        } catch (Exception ex) {}
    }
}
