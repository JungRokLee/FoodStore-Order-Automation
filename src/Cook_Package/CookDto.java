package Cook_Package;

import java.sql.Timestamp;

public class CookDto {

	private String id;
	private int table_num;
	private String food_name;
	private int count;
	private int price;
	private int total;
	private Timestamp date;
	
	public CookDto() {
		// TODO Auto-generated constructor stub
	}

	public CookDto(String id, int table_num, String food_name, int count, int price,  int total, Timestamp date) {
		this.id=id;
		this.table_num=table_num;
		this.food_name=food_name;
		this.count=count;
		this.price=price;
		this.date = date;
		this.total = total;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getFood_name() {
		return food_name;
	}

	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}

	public int getTable_num() {
		return table_num;
	}

	public void setTable_num(int table_num) {
		this.table_num = table_num;
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
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
