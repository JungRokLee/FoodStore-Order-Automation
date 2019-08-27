package Foodstore_Package;


public class FoodStoreDto {

	private String id;
	private String pw;
	private String name;
	private String address;
	private String inform;
	public FoodStoreDto() {
		// TODO Auto-generated constructor stub
	}
	
	public FoodStoreDto(String id, String pw, String name, String address , String inform ) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.pw=pw;
		this.name=name;
		this.address = address;
		this.inform =  inform;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getInform() {
		return inform;
	}

	public void setInform(String inform) {
		this.inform = inform;
	}
}
