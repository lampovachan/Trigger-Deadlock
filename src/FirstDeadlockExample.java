/**
 * This class represents typical situation when deadlock occurs.
 * @author Svitlana Tkachuk
 */


public class FirstDeadlockExample {

    public static Object lock = new Object();
    public static Object lockNew = new Object();

    public static void main(String args[]) {
        ThreadDemo demo = new ThreadDemo();
        ThreadDemoNew demo1 = new ThreadDemoNew();
        demo.start();
        demo1.start();
    }

    private static class ThreadDemo extends Thread {
        public void run() {
            synchronized (lock) {
                System.out.println("Thread 1: Holding lock 1...");

                try { Thread.sleep(10); }
                catch (InterruptedException e) {}
                System.out.println("Thread 1: Waiting for lock 2...");

                synchronized (lockNew) {
                    System.out.println("Thread 1: Holding lock 1 and 2...");
                }
            }
        }
    }
    private static class ThreadDemoNew extends Thread {
        public void run() {
            synchronized (lockNew) {
                System.out.println("Thread 2: Holding lock 2...");

                try { Thread.sleep(10); }
                catch (InterruptedException e) {}
                System.out.println("Thread 2: Waiting for lock 1...");

                synchronized (lock) {
                    System.out.println("Thread 2: Holding lock 1 and 2...");
                }
            }
        }
    }
}
