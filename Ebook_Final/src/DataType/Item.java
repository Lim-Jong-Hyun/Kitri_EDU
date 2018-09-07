package DataType;

public class Item {
	// 책 번호 (seq_item 으 자동생성, primary key_
	private int p_num;

	// 책 이름
	private String p_name;

	// 책 출판년도
	private String p_public;

	// 책 저자
	private String p_writer;

	// 책 출판사
	private String p_genre;

	// 책 출판사
	private String p_publisher;

	// 책 내용 (DB에 책 내용을 넣을지, DB에 책 내용이 담겨있는 파일의 제목을 넣을지 미정인데 일단 책 내용을 그대로 넣고 시간적으로
	// 여유가 되면 FILE 로 만듭시다)
	private String p_summary;

	// 파일이름
	private String p_fileName;

	// 책 가격
	private int p_price;

	public Item() {
	}

	public Item(int p_num, String p_name, String p_public, String p_writer, String p_genre, String p_publisher,
			String p_summary, String p_fileName, int p_price) {
		this.p_num = p_num;
		this.p_name = p_name;
		this.p_public = p_public;
		this.p_writer = p_writer;
		this.p_genre = p_genre;
		this.p_publisher = p_publisher;
		this.p_summary = p_summary;
		this.p_fileName = p_fileName;
		this.p_price = p_price;
	}
	
	public String toSubString2() {
		return "제목:" + p_name + " / 출판연도:" + p_public.substring(0, 4) + " / 저자:" + p_writer
				+ " / 장르:" + p_genre + " / 출판사:" + p_publisher + " / 요약:" + p_summary + " / 책 파일:" + p_fileName
				+ " / 가격:" + p_price + "원";
	}

	public String toSubString() {

		return " / 책이름:" + p_name + " / 출판일:" + p_public.substring(0, 4) + " / 저자:" + p_writer + " / 장르:" + p_genre
				+ " / 출판사:" + p_publisher;
	}

	@Override
	public String toString() {
		return "[책 번호: " + p_num + "] 제목:" + p_name + " / 출판연도:" + p_public.substring(0, 4) + " / 저자:" + p_writer
				+ " / 장르:" + p_genre + " / 출판사:" + p_publisher + " / 요약:" + p_summary + " / 책 파일:" + p_fileName
				+ " / 가격:" + p_price + "원";
	}

	public int getP_num() {
		return p_num;
	}

	public String getP_name() {
		return p_name;
	}

	public String getP_public() {
		return p_public;
	}

	public String getP_writer() {
		return p_writer;
	}

	public String getP_genre() {
		return p_genre;
	}

	public String getP_publisher() {
		return p_publisher;
	}

	public String getP_summary() {
		return p_summary;
	}

	public String getP_fileName() {
		return p_fileName;
	}

	public int getP_price() {
		return p_price;
	}

	public void setP_num(int p_num) {
		this.p_num = p_num;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public void setP_public(String p_public) {
		this.p_public = p_public;
	}

	public void setP_writer(String p_writer) {
		this.p_writer = p_writer;
	}

	public void setP_genre(String p_genre) {
		this.p_genre = p_genre;
	}

	public void setP_publisher(String p_publisher) {
		this.p_publisher = p_publisher;
	}

	public void setP_summary(String p_summary) {
		this.p_summary = p_summary;
	}

	public void setP_fileName(String p_fileName) {
		this.p_fileName = p_fileName;
	}

	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
}