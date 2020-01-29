import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
/*
One way to fix the program is to increase the amount of permits the semaphore has,
        another way is to decrease the amount of threads
        the third way is to release the permit immediately after the thread has printed "semaphore acquired"
        if(permit) {
        System.out.println("Semaphore acquired");
        semaphore.release();
        }

 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        Semaphore semaphore = new Semaphore(5);

        Runnable longRunningTask = () -> {
            boolean permit = false;
            try {

                permit = semaphore.tryAcquire(6, TimeUnit.SECONDS);
                if(permit) {
                    System.out.println("Semaphore acquired");
                    Thread.sleep(5000);
                }
                else
                    System.out.println("Could not acquire semaphore");
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            } finally {
                semaphore.release();
            }
        };
        try {
            for (int i = 0; i < 10; i++)

            executor.submit(longRunningTask);

            executor.shutdown();
        }
        catch (Exception ex){
            System.out.println("ex");
        }
    }
}