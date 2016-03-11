
public class UserServiceExecutable {

	public static void main(String[] args) {
		UserServiceImp imp = new UserServiceImp();

		Item cheese = new Item("Cheese", 98.06);
		Item bread = new Item("Bread", 10.15);
		Item meal = new Item("Meal", 15.20);
		Item beef = new Item("Beef", 35.50);

		try {
			imp.addItem(cheese, 2);
			imp.addItem(bread, 1);
			imp.addItem(meal, 3);
			imp.addItem(beef, 2);
			imp.searchItem(bread);
			System.out.println("The current service is working fine");
		} catch (Exception e) {
			System.out.println("Error executing the current service");
		}
	}

}
