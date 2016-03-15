package cart;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartController {

	ShoppingCart shoppingCart;

	@RequestMapping("/")
	public ShoppingCart index() {
		shoppingCart = new ShoppingCart();
		return shoppingCart;
	}

	@RequestMapping("/addItem")
	public Item addItem(@RequestParam String name, @RequestParam double price, 
						@RequestParam int quantity) {
		Item item = new Item(name, price);
		shoppingCart.addItem(item, quantity);
		return item;
	}

	@RequestMapping("/getItems")
	public ArrayList<Item> getItems() {
		return shoppingCart.getItems();
	}
	
	@RequestMapping("/deleteItem")
	public boolean deleteItem(@RequestParam String name, @RequestParam int quantity) {
		return shoppingCart.deleteItem(name, quantity);
	}
	
	@RequestMapping("/searchItem")
	public boolean searchItem(@RequestParam String name) {
		return shoppingCart.searchItem(name);
	}
	
	@RequestMapping("/updateItem")
	public boolean updateItem(@RequestParam String oldName, @RequestParam String newName) {
		return shoppingCart.updateItem(oldName, newName);
	}
	
	@RequestMapping("/totalPrice")
	public double totalPrice() {
		return shoppingCart.totalPrice();
	}
}
