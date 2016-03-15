package cart;

import java.util.ArrayList;

public class ShoppingCart implements UserService {

	ArrayList<Item> items = new ArrayList<Item>();

	public boolean addItem(Item item, int quantity) {
		for (int i = 0; i < quantity; i++)
			items.add(item);
		return true;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public double totalPrice() {
		double total = 0.0;
		for (Item i : items) {
			total += i.getPrice();
		}
		return total;
	}

	public boolean searchItem(Item item) {
		if (items.contains(item))
			return true;
		else
			return false;
	}

	public boolean searchItem(String name) {
		for (Item i : items) {
			if (i.name.equals(name))
				return true;
		}
		return false;
	}

	public boolean deleteItem(Item item, int quantity) {
		int quantityFounded = 0;
		String itemName = item.getName();
		for (Item i : items) {
			if (i.getName().equals(itemName))
				quantityFounded += 1;
		}
		if (items.contains(item) && quantityFounded >= quantity) {
			for (int i = 0; i < quantity; i++) {
				items.remove(item);
			}
			return true;
		} else
			return false;
	}

	public boolean deleteItem(String name, int quantity) {
		int quantityFounded = 0;
		for (Item i : items) {
			if (i.getName().equals(name))
				quantityFounded += 1;
		}
		for (Item i : items) {
			if (i.name.equals(name) && quantityFounded >= quantity) {
				for (int j = 0; j < quantity; j++)
					items.remove(j);
				return true;
			}
		}
		return false;
	}

	public boolean updateItem(Item item, String newName) {
		if (items.contains(item)) {
			for (Item i : items)
				i.setName(newName);
			return true;
		}
		return false;
	}
	
	public boolean updateItem(String oldName, String newName) {
		boolean changes = false;
		for (Item i: items){
			if (i.name.equals(oldName)) {
				i.setName(newName);
				changes = true;
			}
		}
		return changes;
	}
}
