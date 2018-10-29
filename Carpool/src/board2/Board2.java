package board2;

import java.sql.Date;

public class Board2 {

	private int num;
	private int type;
	private Date w_date;
	private String title;
	private String content;
	private String writer;
	
	public Board2() {
		
	}

	public Board2(int num, int type, Date w_date, String title, String content, String writer) {
		super();
		this.num = num;
		this.type = type;
		this.w_date = w_date;
		this.title = title;
		this.content = content;
		this.writer = writer;
	}

	public int getNum() {
		return num;
	}

	public int getType() {
		return type;
	}

	public Date getW_date() {
		return w_date;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getWriter() {
		return writer;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setW_date(Date w_date) {
		this.w_date = w_date;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	@Override
	public String toString() {
		return "Board2 [num=" + num + ", type=" + type + ", w_date=" + w_date + ", title=" + title + ", content="
				+ content + ", writer=" + writer + "]";
	}
}