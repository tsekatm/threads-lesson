# Java Threading Fundamentals

## 1. What is a Thread?

A thread is the smallest unit of execution within a process. In Java, it allows for concurrent execution of code, enabling your program to perform multiple tasks simultaneously.

## 2. Why Use Threads?

- Improved performance on multi-core processors
- Better responsiveness in applications
- Ability to perform background tasks without blocking the main program

## 3. Creating and Starting Threads

### Method 1: Extending the Thread class

```java
public class MyThread extends Thread {
    public void run() {
        System.out.println("Thread is running");
    }
}

// Usage
MyThread thread = new MyThread();
thread.start();
```

### Method 2: Implementing the Runnable interface (Preferred)

```java
public class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Runnable is running");
    }
}

// Usage
Thread thread = new Thread(new MyRunnable());
thread.start();
```

## 4. Thread Lifecycle

1. New: Thread object is created but not started
2. Runnable: Thread is ready to run
3. Running: Thread is executing
4. Blocked/Waiting: Thread is temporarily inactive
5. Terminated: Thread has completed execution

## 5. Thread Synchronization

When multiple threads access shared resources, we need to synchronize access to prevent race conditions.

### Using synchronized keyword

```java
public class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}
```

### Using synchronized blocks

```java
public void incrementBlock() {
    synchronized(this) {
        count++;
    }
}
```

## 6. Thread Communication

Threads can communicate using wait(), notify(), and notifyAll() methods.

```java
class SharedResource {
    private boolean isReady = false;

    public synchronized void produce() {
        isReady = true;
        notify();
    }

    public synchronized void consume() throws InterruptedException {
        while (!isReady) {
            wait();
        }
        isReady = false;
    }
}
```

## 7. Thread Pools

Thread pools manage a collection of worker threads, improving performance and resource management.

```java
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
```

## 8. Common Pitfalls

1. Deadlocks: Two or more threads waiting for each other to release resources
2. Race conditions: Unpredictable results due to the timing of thread execution
3. Thread starvation: A thread is unable to gain regular access to shared resources

## 9. Best Practices

1. Use thread pools for better resource management
2. Minimize shared state between threads
3. Use higher-level concurrency utilities (e.g., java.util.concurrent package)
4. Be aware of thread safety in library classes you use
5. Always use synchronization when accessing shared mutable state

Remember, mastering threading takes practice. Start with simple examples and gradually work your way up to more complex scenarios.
