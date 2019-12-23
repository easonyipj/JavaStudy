package multithread.producerAndConsumer;

import java.util.LinkedList;

/**
 * TODO : 尝试用其他方法
 * https://blog.csdn.net/ldx19980108/article/details/81707751
 */
public class ProducerConsumer {

    private static LinkedList<String> list;
    private static volatile int num = 0;
    private static int MAX_SIZE = 10;

    static class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (list) {
                    while(list.size() == 0) {
                        System.out.println("仓库为空");
                        try {
                            list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    String product = list.pollFirst();
                    System.out.println("消费产品" + product);
                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    list.notifyAll();
                }
            }
        }
    }

    static class Producer implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (list) {
                    while(list.size() + 1 > MAX_SIZE) {
                        System.out.println("仓库已满");
                        try {
                            list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int num = ProducerConsumer.num++;
                    System.out.println("生产产品" + num);
                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    list.add(String.valueOf(num));
                    list.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        MAX_SIZE = 10;
        list = new LinkedList<String>();

        Thread producerA = new Thread(new Producer(), "producerA");
        Thread producerB = new Thread(new Producer(), "producerB");
        Thread producerC = new Thread(new Producer(), "producerC");

        Thread consumerA = new Thread(new Consumer(), "consumerA");
        Thread consumerB = new Thread(new Consumer(), "consumerB");

        producerA.start();
        Thread.sleep(10L);
        producerB.start();
        Thread.sleep(10L);
        producerC.start();
        Thread.sleep(10L);
        consumerA.start();
        Thread.sleep(10L);
        consumerB.start();
        Thread.sleep(10L);
    }
}
