import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class represents the deadlock which is by the way may not happen sometimes but if you recompile this code, it may occurs.
 * The reason is that sometimes one person has the batteries while another person has the flashlight which prevents them
 * from turning on the flashlight causing a deadlock.
 * This example uses Lock instead of explicit call of synchronized().
 */
public class FourthDeadlockExample {
    public static class Table {

        private static Lock Flashlight = new ReentrantLock();
        private static Lock Batteries = new ReentrantLock();

        public static void giveFlashLightAndBatteries() {
            try {
                Flashlight.lock();
                Batteries.lock();
                System.out.println("Lights on");
            } finally {
                Batteries.unlock();
                Flashlight.unlock();
            }
        }

        public static void giveBatteriesAndFlashLight() {
            try {
                Batteries.lock();
                Flashlight.lock();
                System.out.println("Lights on");
            } finally {
                Flashlight.unlock();
                Batteries.unlock();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                Table.giveFlashLightAndBatteries();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                Table.giveBatteriesAndFlashLight();
            }
        }).start();
    }
}
