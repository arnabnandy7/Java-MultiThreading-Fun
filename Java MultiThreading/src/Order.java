
public class Order {
	
	private int orderId;
	private String orderDesription;
	private String paymentInfo;
	private String paymentStatus;
	private String paymentErrorMsg;
	private String addressInfo;
	private String deliveryMethod;
	private String deliveryErrorMsg;
	private String emailInfo;
	private boolean isPaymentDone;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderDesription() {
		return orderDesription;
	}
	public void setOrderDesription(String orderDesription) {
		this.orderDesription = orderDesription;
	}
	public String getPaymentInfo() {
		return paymentInfo;
	}
	public void setPaymentInfo(String paymentInfo) {
		this.paymentInfo = paymentInfo;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getAddressInfo() {
		return addressInfo;
	}
	public void setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
	}
	public String getDeliveryMethod() {
		return deliveryMethod;
	}
	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}
	public String getEmailInfo() {
		return emailInfo;
	}
	public void setEmailInfo(String emailInfo) {
		this.emailInfo = emailInfo;
	}
	public boolean isPaymentDone() {
		return isPaymentDone;
	}
	public void setPaymentDone(boolean isPaymentDone) {
		this.isPaymentDone = isPaymentDone;
	}
	public String getDeliveryErrorMsg() {
		return deliveryErrorMsg;
	}
	public void setDeliveryErrorMsg(String deliveryErrorMsg) {
		this.deliveryErrorMsg = deliveryErrorMsg;
	}
	public String getPaymentErrorMsg() {
		return paymentErrorMsg;
	}
	public void setPaymentErrorMsg(String paymentErrorMsg) {
		this.paymentErrorMsg = paymentErrorMsg;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDesription=" + orderDesription + ", paymentInfo=" + paymentInfo
				+ ", paymentStatus=" + paymentStatus + ", paymentErrorMsg=" + paymentErrorMsg + ", addressInfo="
				+ addressInfo + ", deliveryMethod=" + deliveryMethod + ", deliveryErrorMsg=" + deliveryErrorMsg
				+ ", emailInfo=" + emailInfo + ", isPaymentDone=" + isPaymentDone + "]";
	}
	/*
	 * @Override public boolean equals(Object o) { if(o == null) { return false; }
	 * if (o == this) { return true; } if (getClass() != o.getClass()) { return
	 * false; }
	 * 
	 * Order e = (Order) o; return ( (this.getOrderId() == e.getOrderId()) &&
	 * (this.getOrderDesription().equals(e.getOrderDesription())) ); }
	 * 
	 * @Override public int hashCode() {
	 * 
	 * final int PRIME = 31; int result = 1; result = PRIME * result +
	 * getOrderId()+getOrderDesription().hashCode(); return result; }
	 */
	
	
	
	

}
