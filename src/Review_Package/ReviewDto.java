package Review_Package;

import java.sql.Timestamp;

public class ReviewDto {

	private String id;
	private String name;
	private String title;
	private String inform;
	private Timestamp date;
	private int num;
	public ReviewDto() {
		// TODO Auto-generated constructor stub
	}

	public ReviewDto(String id, String name, String title, String inform,Timestamp date, int num) {
		this.id=id;
		this.name=name;
		this.title=title;
		this.inform=inform;
		this.date = date;
		this.num = num;
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
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getInform() {
		return inform;
	}

	public void setInform(String inform) {
		this.inform = inform;
	}
	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
