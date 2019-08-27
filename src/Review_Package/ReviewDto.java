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
	}

	public ReviewDto(String id, String name, String title, String inform,Timestamp date, int num) {
		this.id=id;
		this.name=name;
		this.title=title;
		this.inform=inform;
		this.date = date;
		this.num = num;
	}

	/* 리뷰 작성자의 id를 위한 getter와 Setter */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	/* 리뷰 작성자의 이름을 위한 getter와 Setter */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/* 리뷰의 제목을 위한 getter와 Setter */
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/* 리뷰의 내용을 위한 getter와 Setter */
	public String getInform() {
		return inform;
	}

	public void setInform(String inform) {
		this.inform = inform;
	}

	/* 리뷰가 작성된 날짜를 위한 getter와 Setter */
	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	/* 리뷰 번호를 위한 getter와 Setter */
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
}
