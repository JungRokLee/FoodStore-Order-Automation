package Food_Package;

import java.sql.Timestamp;

public class FoodDto {

	private String id;
	private String name;
	private int price;
	private String picture;
	private String inform;
	
	public FoodDto() {
		// TODO Auto-generated constructor stub
	}

	public FoodDto(String id, String name, int price, String picture, String inform) {
		this.id = id;
		this.name=name;
		this.price=price;
		this.picture=picture;
		this.inform=inform;

		
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getInform() {
		return inform;
	}

	public void setInform(String inform) {
		this.inform = inform;
	}


	
	
	
}
