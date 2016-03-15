package cart;

public class UserServiceImp implements UserService {

	UserService cart = new ShoppingCart();

	@Override
	public boolean addItem(Item item, int quantity) {
		return cart.addItem(item, quantity);
	}

	@Override
	public boolean searchItem(Item item) {
		return cart.searchItem(item);
	}

	@Override
	public boolean deleteItem(Item item, int quantity) {
		return cart.deleteItem(item, quantity);
	}

	@Override
	public boolean updateItem(Item item, String newName) {
		return cart.updateItem(item, newName);
	}

}
