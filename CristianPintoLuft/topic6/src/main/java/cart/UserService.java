package cart;

public interface UserService {

	public boolean addItem(Item item, int quantity);

	public boolean searchItem(Item item);

	public boolean deleteItem(Item item, int quantity);

	public boolean updateItem(Item item, String newName);
}
