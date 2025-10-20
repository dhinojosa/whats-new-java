import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import module java.base;

void main() {
    Application application = new Application();
    application.run();
}

public record User(String firstName, String lastName, int age, String email) {
}

public record Product(
    String name,
    BigDecimal price,
    String category,
    int quantity
) {
}

static class OrderController {

    private final StableValue<Logger> logger = StableValue.of();

    Logger getLogger() {
        return logger.orElseSet(() -> {
            var logger = LoggerFactory.getLogger(UserService.class);
            logger.info("Loading Order Controller");
            return logger;
        });
    }

    void submitOrder(User user, List<Product> products) {
        getLogger().info("Order Started");
        getLogger().info("Order Submitted");
    }
}

static class UserService {
    private final StableValue<Logger> logger = StableValue.of();

    Logger getLogger() {
        return logger.orElseSet(() -> {
            var logger = LoggerFactory.getLogger(UserService.class);
            logger.info("Loading User Service");
            return logger;
        });
    }

    public User getUser(String email) {
        getLogger().info("Loading User");
        User user = new User("John", "Doe", 25, "jdoe@localhost.com");
        getLogger().info("Returning User");
        return user;
    }
}

static class ProductRepository {

    private final StableValue<Logger> logger = StableValue.of();

    Logger getLogger() {
        return logger.orElseSet(() -> {
            var logger = LoggerFactory.getLogger(ProductRepository.class);
            logger.info("Loading Product Logger");
            return logger;
        });
    }

    List<Product> getProducts() {
        getLogger().info("Loading Products");
        var products = List.of(
            new Product("MacBook Pro M3", new BigDecimal("1999.99"), "Latest Apple laptop with M3 chip", 3),
            new Product("Gaming Mouse", new BigDecimal("89.99"), "RGB gaming mouse with 16000 DPI", 4),
            new Product("Mechanical Keyboard", new BigDecimal("159.99"), "Cherry MX Blue switches with PBT keycaps", 1),
            new Product("4K Monitor", new BigDecimal("499.99"), "32-inch 4K HDR display", 2),
            new Product("Wireless Earbuds", new BigDecimal("199.99"), "Active noise cancelling with spatial audio", 5),
            new Product("Smartwatch Pro", new BigDecimal("349.99"), "Health tracking with cellular connectivity", 1),
            new Product("Graphics Card", new BigDecimal("799.99"), "8GB GDDR6 ray-tracing enabled GPU", 500),
            new Product("SSD Drive", new BigDecimal("129.99"), "1TB NVMe PCIe 4.0 storage", 2),
            new Product("Webcam HD", new BigDecimal("79.99"), "1080p with dual microphones", 10),
            new Product("Power Bank", new BigDecimal("49.99"), "20000mAh fast charging portable battery", 2)
        );
        getLogger().info("Returning Products");
        return products;
    }
}

static class Application {
    final StableValue<OrderController> ORDERS = StableValue.of();
    final StableValue<ProductRepository> PRODUCTS = StableValue.of();
    final StableValue<UserService> USERS = StableValue.of();

    private final StableValue<Logger> logger = StableValue.of();

    Logger getLogger() {
        return logger.orElseSet(() -> {
            var logger = LoggerFactory.getLogger(Application.class);
            logger.info("Loading Application Logger");
            return logger;
        });
    }

    public OrderController orders() {
        return ORDERS.orElseSet(OrderController::new);
    }

    public ProductRepository products() {
        return PRODUCTS.orElseSet(ProductRepository::new);
    }

    public UserService users() {
        return USERS.orElseSet(UserService::new);
    }

    public void run() {
        getLogger().info("Application started");
        orders().submitOrder(users().getUser("jdoe@localhost.com"), products().getProducts());
        getLogger().info("Application finished");
    }
}
