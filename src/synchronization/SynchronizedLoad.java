package synchronization;

public class SynchronizedLoad {

    public void assign(int number) {
        try {
            // load from server
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("assigning number");
        number += 50;
        synchronized (this) {
            notify();
        }
    }

    public void decrease(int number) {
        if (number <= 0) {
            System.out.println("\n\nnumber is < 0");
            System.out.println("number is " + number);
            synchronized (this) {
                try {
                    System.out.println("waited");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.print("number - 20 ");
        number -= 20;
    }
}
