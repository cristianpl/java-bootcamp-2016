
public class Item {

	String name;
	double price = 0.0;

	public Item(String name) {
		this.name = name;
	}

	public Item(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
