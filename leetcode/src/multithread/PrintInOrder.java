package multithread;

import java.util.concurrent.CountDownLatch;

/**
 * 顺序打印ABC
 */
public class PrintInOrder {

    private static final Object object = new Object();
    private static boolean A;
    private static boolean B;

    private static CountDownLatch cA = new CountDownLatch(1);
    private static CountDownLatch cB = new CountDownLatch(1);

    public static void printBySyn() {
        Thread printA = new Thread(() -> {
            synchronized (object) {
                System.out.println("A");
                A = true;
                object.notifyAll();
            }
        });

        Thread printB = new Thread(() -> {
            synchronized (object) {
                try {
                    while(!A) {
                        object.wait();
                    }
                    System.out.println("B");
                    B = true;
                    object.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread printC = new Thread(() -> {
            synchronized (object) {
                try {
                    while(!B) {
                        object.wait();
                    }
                    System.out.println("C");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        printB.start();
        printC.start();
        printA.start();
    }

    public static void printByCountDownLatch() {

        Thread printA = new Thread(() -> {
            System.out.println("A");
            cA.countDown();
        });

        Thread printB = new Thread(() -> {
            try {
                cA.await();
                System.out.println("B");
                cB.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread printC = new Thread(() -> {
            try {
                cB.await();
                System.out.println("C");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        printB.start();
        printC.start();
        printA.start();
    }

}
