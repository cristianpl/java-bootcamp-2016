import static org.junit.Assert.*;

import org.junit.Test;

public class _ShoppingCartTester {

	@Test
	public void addCart() {
		@SuppressWarnings("unused")
		ShoppingCart cart = new ShoppingCart();
	}

	@Test
	public void addItem() {
		ShoppingCart cart = new ShoppingCart();
		Item cheese = new Item("Cheese", 5);
		assertEquals(true, cart.addItem(cheese, 1));
	}

	@Test
	public void searchItem() {
		ShoppingCart cart = new ShoppingCart();
		Item cheese = new Item("Cheese", 98.06);
		Item bread = new Item("Bread", 10.15);
		Item meal = new Item("Meal", 15.20);
		Item beef = new Item("Beef", 35.50);
		cart.addItem(cheese, 2);
		cart.addItem(bread, 1);
		cart.addItem(meal, 2);
		cart.addItem(beef, 3);
		assertEquals(true, cart.searchItem(cheese));
	}

	@Test
	public void deleteItem() {
		ShoppingCart cart = new ShoppingCart();
		Item cheese = new Item("Cheese", 98.06);
		Item bread = new Item("Bread", 10.15);
		Item meal = new Item("Meal", 15.20);
		Item beef = new Item("Beef", 35.50);
		cart.addItem(cheese, 2);
		cart.addItem(bread, 1);
		cart.addItem(meal, 2);
		cart.addItem(beef, 3);
		assertEquals(true, cart.deleteItem(beef, 3));
	}

	@Test
	public void calculatePrice() {
		ShoppingCart cart = new ShoppingCart();
		Item cheese = new Item("Cheese", 98.06);
		Item bread = new Item("Bread", 10.15);
		Item meal = new Item("Meal", 15.20);
		Item beef = new Item("Beef", 35.50);
		cart.addItem(cheese, 2);
		cart.addItem(bread, 1);
		cart.addItem(meal, 2);
		cart.addItem(beef, 3);
		assertEquals(343.17, cart.totalPrice(), 0.01);
	}

	@Test
	public void updateItem() {
		ShoppingCart cart = new ShoppingCart();
		Item cheese = new Item("Cheese", 98.06);
		Item bread = new Item("Bread", 10.15);
		cart.addItem(cheese, 2);
		cart.addItem(bread, 1);
		assertEquals(true, cart.updateItem(cheese, "Cream cheese"));
	}

	@Test
	public void buyItems() {
		ShoppingCart cart = new ShoppingCart();
		Item cheese = new Item("Cheese", 98.06);
		Item bread = new Item("Bread", 10.15);
		Item meal = new Item("Meal", 15.20);
		Item beef = new Item("Beef", 35.50);
		cart.addItem(cheese, 2);
		cart.addItem(bread, 1);
		cart.addItem(meal, 2);
		cart.addItem(beef, 3);
		/*
		 * In here, the carts total price should be $343.17, like in the
		 * calculatePrice test. If I delete some items from it, the price should
		 * be lower than this.
		 */
		cart.deleteItem(beef, 2);
		cart.deleteItem(cheese, 1);
		assertEquals(174.11, cart.totalPrice(), 0.01);
	}
}
