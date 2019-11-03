import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTaskDriver {

	private static final String CREATE_ORDER = "CREATE_ORDER";
	private static final String ENRICH_ORDER = "ENRICH_ORDER";
	private static final String PAYMENT_ORDER = "PAYMENT_ORDER";
	private static final String DELIVER_ORDER = "DELIVER_ORDER";
	private static final String PAY_ERROR = "PAY_ERROR";
	private static final String DELIVERY_ERROR = "DELIVERY_ERROR";

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// Lets process 100 orders
		Random orderIdGenerator = new Random(Integer.MAX_VALUE);
		CompletableFutureTaskDriver driver = new CompletableFutureTaskDriver();
		Map<Integer, CompletableFuture<Order>> futureOrders = new HashMap<Integer, CompletableFuture<Order>>();
		for (int i = 0; i < 100; i++) {
			int currentOrderId = orderIdGenerator.nextInt();

			CompletableFuture<Order> processedOrder = CompletableFuture
					.supplyAsync(() -> driver.createOder(currentOrderId))

					.thenApply(order -> (driver.enrichOrder(order)))

					.thenApply(order -> {
						try {
							return (driver.paymentForOder(order));
						} catch (Exception exception) {
							// TODO Auto-generated catch block
							return driver.handlePaymentFaliure(exception, order);
						}
					})

					.thenApply(order -> {
						try {
							return (driver.deliverOrder(order));
						} catch (Exception exception) {
							// TODO Auto-generated catch block
							return driver.handleDeliveryFaliure(exception, order);
						}
					});

			futureOrders.put(currentOrderId, processedOrder);
		}

		System.out.println("Total Orders placed = " + futureOrders.size());
		while (futureOrders.size() > 0) {
			CompletableFuture<Object> processedOrder = CompletableFuture
					.anyOf(futureOrders.values().toArray(new CompletableFuture[futureOrders.size()]));
			Order processedData = (Order) processedOrder.get();
			futureOrders.remove(processedData.getOrderId());
			System.out.println(processedData);
		}
	}

	// Caller methods

	public Order createOder(int orderId) {
		CompletableFutureTask completableFutureTask = new CompletableFutureTask(CREATE_ORDER, orderId);
		return completableFutureTask.createOder();

	}

	public Order enrichOrder(Order order) {
		CompletableFutureTask completableFutureTask = new CompletableFutureTask(ENRICH_ORDER, order);
		return completableFutureTask.enrichOrder();
	}

	public Order paymentForOder(Order order) throws Exception {
		CompletableFutureTask completableFutureTask = new CompletableFutureTask(PAYMENT_ORDER, order);
		return completableFutureTask.paymentForOder();
	}

	public Order deliverOrder(Order order) throws Exception {
		CompletableFutureTask completableFutureTask = new CompletableFutureTask(DELIVER_ORDER, order);
		return completableFutureTask.deliverOrder();
	}

	public Order handlePaymentFaliure(Exception exception, Order order) {
		CompletableFutureTask completableFutureTask = new CompletableFutureTask(PAY_ERROR, order, exception);
		return completableFutureTask.handlePaymentFaliure();
	}

	public Order handleDeliveryFaliure(Exception exception, Order order) {
		CompletableFutureTask completableFutureTask = new CompletableFutureTask(DELIVERY_ERROR, order, exception);
		return completableFutureTask.handleDeliveryFaliure();
	}

}
