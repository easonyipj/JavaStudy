package multithread;

public class Sychronized {
    public static void main() {
        synchronized (Sychronized.class) {
            System.out.println("main");
        }
        staticMethod();
    }

    public synchronized static void staticMethod() {
        System.out.println("staticMethod");
    }
}
