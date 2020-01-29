public class ThreadProgram extends Thread {
Thread t0;

    public static void main(String[] args) {
        Thread t0 = new Thread("Thread-0");
        Thread tMain = new Thread("main");
        t0.start();
        tMain.start();
        for (int i = 0; i < 5; i++) {
            System.out.println(tMain.getName()+ " is in control.");
        }
        Thread.yield();
        for (int i = 0; i < 5; i++) {
            System.out.println(t0.getName()+ " is in control.");
        }
    }

    @Override
    public void run() {

    }
}
