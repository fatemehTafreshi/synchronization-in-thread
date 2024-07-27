import synchronization.SynchronizationPrint;
import synchronization.SynchronizedLoad;

public class Main {

    public static void main(String[] args) {

//      synchronization in thread when print a clause
        Thread thread1 = new Thread(new MyRunable("thread1"));
        Thread thread2 = new Thread(new MyRunable("thread2"));

        thread1.start();
        thread2.start();


//      simulate fetch data from server
        SynchronizedLoad loadObj = new SynchronizedLoad();
        int number = 0;
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //wait util thread1 and thread2 finished
                    thread1.join();
                    thread2.join();

                    loadObj.decrease(number);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //wait util thread1 and thread2 finished
                    thread1.join();
                    thread2.join();

                    loadObj.assign(number);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        thread3.start();
        thread4.start();

    }

    static private class MyRunable implements Runnable {
        private final String threadname;
        private final SynchronizationPrint printObj = new SynchronizationPrint();

        public MyRunable(String threadname) {
            this.threadname = threadname;
        }

        @Override
        public void run() {
            printObj.printMessage(threadname);

        }
    }


}