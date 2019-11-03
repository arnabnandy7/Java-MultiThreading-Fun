import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class CompletableFutureTask implements Callable<Order> {

	// Static informations
	private static final List<String> PAYMENT_METHODS = Arrays.asList("Cash on Delivery", "Credit/Debit Card Payment",
			"No Cost EMI", "Net Banking Transfer");
	private static final List<String> ADDRESS_INFO = Arrays.asList("BB Street,Kolkata", "Tenpur Bagnan", "Pune City",
			"Mumbai GTP Block", "Unitech New Town", "Hariyana Next Building");
	private static final List<String> ORDER_DESC = Arrays.asList("Lux Soap", "Playstation 4", "May Payne PC Version",
			"Moto G5 S 32GB", "MI A3 16+4 GB", "64 inch Samsung UHD TV");
	private static final List<String> DELIVERY_METHOD = Arrays.asList("DTDC Couriour", "Bluedart Couriour",
			"Amazon Prime", "DHL Couriour");
	private static final String CREATE_ORDER = "CREATE_ORDER";
	private static final String ENRICH_ORDER = "ENRICH_ORDER";
	private static final String PAYMENT_ORDER = "PAYMENT_ORDER";
	private static final String DELIVER_ORDER = "DELIVER_ORDER";
	private static final String PAY_ERROR = "PAY_ERROR";
	private static final String DELIVERY_ERROR = "DELIVERY_ERROR";

	// Properties
	private String taskType;
	private int orderId;
	private Order order;
	private Exception exception;

	// Constructors
	public CompletableFutureTask(String taskType, Order order, Exception exception) {
		super();
		this.taskType = taskType;
		this.order = order;
		this.exception = exception;
	}

	public CompletableFutureTask(String taskType, Order order) {
		super();
		this.taskType = taskType;
		this.order = order;
	}

	public CompletableFutureTask(String taskType, int orderId) {
		super();
		this.taskType = taskType;
		this.orderId = orderId;
	}

	// For creating the Order processing

	public Order createOder() {
		mimicProcessingTime();
		Order order = new Order();
		order.setOrderId(orderId);
		order.setOrderDesription(ORDER_DESC.get(new SecureRandom().nextInt(ORDER_DESC.size())));
		return order;

	}

	public Order enrichOrder() {
		mimicProcessingTime();
		if (null != order) {
			order.setAddressInfo(ADDRESS_INFO.get(new SecureRandom().nextInt(ADDRESS_INFO.size())));
			order.setPaymentInfo(PAYMENT_METHODS.get(new SecureRandom().nextInt(PAYMENT_METHODS.size())));
		}
		return order;
	}

	public Order paymentForOder() throws Exception {
		mimicProcessingTime();
		// Lets say Cash on Delivery is not supported
		if (null != order) {
			if ("Cash on Delivery".equals(order.getPaymentInfo())) {
				order.setPaymentDone(false);
				order.setPaymentStatus("PAYMENT REJECTED");
				throw new Exception("COD is not available now");
			} else {
				order.setPaymentStatus("PAYMENT SUCCESSFUL");
				order.setPaymentDone(true);
			}
		}
		return order;
	}

	public Order deliverOrder() throws Exception {
		mimicProcessingTime();
		// Lets say Only successful orders get delivered
		if (null != order) {
			if (!order.isPaymentDone()) {
				order.setPaymentInfo("Not Delivered");
				order.setEmailInfo("Failure Email to be sent with Details");
				throw new Exception("As Payment Failed Delivery aborted");
			} else {
				order.setEmailInfo("Success Email to be sent with Details");
				order.setDeliveryMethod(DELIVERY_METHOD.get(new SecureRandom().nextInt(DELIVERY_METHOD.size())));
			}
		}
		return order;
	}

	public Order handlePaymentFaliure() {
		mimicProcessingTime();
		if (order != null && exception != null) {
			order.setPaymentErrorMsg(exception.getMessage());
		}
		return order;
	}

	public Order handleDeliveryFaliure() {
		mimicProcessingTime();
		if (order != null && exception != null) {
			order.setDeliveryErrorMsg(exception.getMessage());
		}
		return order;
	}

	public void mimicProcessingTime() {
		Random random = new Random();
		try {
			Thread.sleep(random.nextInt(5000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;

	}

	@Override
	public Order call() throws Exception {
		Order currentOrderState = null;
		switch (taskType) {
		case CREATE_ORDER:
			currentOrderState = createOder();
			break;
		case ENRICH_ORDER:
			currentOrderState = enrichOrder();
			break;
		case PAYMENT_ORDER:
			currentOrderState = paymentForOder();
			break;
		case DELIVER_ORDER:
			currentOrderState = deliverOrder();
			break;
		case PAY_ERROR:
			currentOrderState = handlePaymentFaliure();
			break;
		case DELIVERY_ERROR:
			currentOrderState = handleDeliveryFaliure();
			break;
		default:
			break;
		}
		return currentOrderState;
	}

}
