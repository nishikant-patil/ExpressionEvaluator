package ExpressionEvaluator.model;


public class Cart {
	private Item item;
	private double cartValue;
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public double getCartValue() {
		return cartValue;
	}
	public void setCartValue(double cartValue) {
		this.cartValue = cartValue;
	}
}
