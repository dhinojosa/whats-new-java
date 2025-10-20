import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import module java.base;

void main() throws InterruptedException {
    Application application = new Application();
    runMainThreadThreeTimes(application);
    runVirtualThreadOneTime(application);
}

private static void runVirtualThreadOneTime(Application application) throws InterruptedException {
    Thread virtualThread = Thread.ofVirtual().name("new-virtual-thread-1").start(() -> {
        application.orders().submitOrder();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    });
    virtualThread.join();
}

private static void runMainThreadThreeTimes(Application application) {
    try {
        application.orders().submitOrder();
        Thread.sleep(100);
        application.orders().submitOrder();
        Thread.sleep(100);
        application.orders().submitOrder();
        Thread.sleep(100);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
}

class Application {
    final Supplier<Logger> logger = StableValue.supplier(() ->
        LoggerFactory.getLogger(Application.class));

    private final int POOL_SIZE = 10;

    private final List<OrderController> ORDERS
        = StableValue.list(POOL_SIZE, x -> {
        logger.get().info("Retrieving Item");
        logger.get().info("x: {}", x);
        return new OrderController();
    });

    public OrderController orders() {
        var threadId = Thread.currentThread().threadId();
        logger.get().info("Thread Id: {}", threadId);
        long index = Thread.currentThread().threadId() % POOL_SIZE;
        return ORDERS.get((int) index);
    }
}

class OrderController {
    private final StableValue<Logger> logger = StableValue.of();

    Logger getLogger() {
        return logger.orElseSet(() ->
            LoggerFactory.getLogger(OrderController.class));
    }

    void submitOrder() {
        getLogger().info("Order Submitted");
    }
}
