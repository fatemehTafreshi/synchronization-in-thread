package synchronization;

public class SynchronizationPrint {
    private final Object lock = "locking";

    public void printMessage(String threadname) {
        synchronized (lock) {
            System.out.print("[ ");
            System.out.print(threadname);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print(" ]");
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
