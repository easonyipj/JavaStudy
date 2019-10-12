package multithread;

public class PrintABC {

    static class Print implements Runnable{

        private String name;
        private Object pre;
        private Object self;
        private int count = 10;

        Print(String name, Object pre, Object self) {
            this.name = name;
            this.pre = pre;
            this.self = self;
        }

        @Override
        public void run() {
            while(count > 0) {
                synchronized (pre) {
                    synchronized (self) {
                        System.out.println(name);
                        count--;
                        self.notify();
                    }
                    try {
                        pre.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        Thread printA = new Thread(new Print("A", c, a), "printA");
        Thread printB = new Thread(new Print("B", a, b), "printB");
        Thread printC = new Thread(new Print("C", b, c), "printC");

        printA.start();
        Thread.sleep(10);
        printB.start();
        Thread.sleep(10);
        printC.start();


    }

}
