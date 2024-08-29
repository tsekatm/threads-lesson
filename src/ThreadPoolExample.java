
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            Runnable worker = new WorkerThread("Task " + i);
            executor.execute(worker);
        }

        executor.shutdown();
    }
}

class WorkerThread implements Runnable {
    private String task;

    WorkerThread(String task) {
        this.task = task;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Task = " + task);
        try {
            Thread.sleep(2000); // Simulate a task that takes 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " End.");
    }
}