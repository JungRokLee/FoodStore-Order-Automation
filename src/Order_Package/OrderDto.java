package Order_Package;

import java.sql.Timestamp;

public class OrderDto {

	private String id;
	private int table_num;
	private String food_name;
	private int price;
	private int count;
	private Timestamp date;
	private int total;
	
	public OrderDto() {
		// TODO Auto-generated constructor stub
	}

	public OrderDto(String id, int table_num, String food_name, int price, int count, Timestamp date, int total) {
		this.id = id;
		this.table_num=table_num;
		this.food_name=food_name;
		this.price=price;
		this.count = count;
		this.date = date;
		this.total = total;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getTable_num() {
		return table_num;
	}

	public void setTable_num(int table_num) {
		this.table_num = table_num;
	}

	public String getFoodname() {
		return food_name;
	}

	public void setFoodname(String food_name) {
		this.food_name = food_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getTotal() {
		return price * count;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
