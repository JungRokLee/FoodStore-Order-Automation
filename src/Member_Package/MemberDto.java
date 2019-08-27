package Member_Package;


public class MemberDto {

	private String id;
	private String pw;
	private String name;
	private String address;
	private int status;

	public MemberDto() {
		// TODO Auto-generated constructor stub
	}
	
	public MemberDto(String id, String pw, String name, String address, int status) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.pw=pw;
		this.name=name;
		this.address = address;
		this.status = 0;
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
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
