import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

// Basic test to check that it's working
public class MyPoolTest {
    private static final Logger logger = Logger.getLogger(MyPoolTest.class.getName());

    public static void main(String[] args) {
        // Configure logging
        Logger.getLogger("").setLevel(Level.ALL);

        // Create thread pool with parameters
        MyPool pool = new MyPool(
                2,  // corePoolSize
                4,  // maxPoolSize
                5,  // keepAliveTime
                TimeUnit.SECONDS,  // timeUnit
                10, // queueSize
                1   // minSpareThreads
        );

        // Generate some tasks
        for (int i = 0; i < 20; i++) {
            final int taskId = i;
            pool.execute(() -> {
                logger.info("Task " + taskId + " started");
                try {
                    // Simulate some work
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                logger.info("Task " + taskId + " completed");
            });
        }

        // Wait for some time to see the pool in action
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Shutdown the pool
        pool.shutdown();
    }
} 