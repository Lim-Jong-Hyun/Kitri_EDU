package DataType;

public class Item {
	// å ��ȣ (seq_item �� �ڵ�����, primary key_
	private int p_num;

	// å �̸�
	private String p_name;

	// å ���ǳ⵵
	private String p_public;

	// å ����
	private String p_writer;

	// å ���ǻ�
	private String p_genre;

	// å ���ǻ�
	private String p_publisher;

	// å ���� (DB�� å ������ ������, DB�� å ������ ����ִ� ������ ������ ������ �����ε� �ϴ� å ������ �״�� �ְ� �ð�������
	// ������ �Ǹ� FILE �� ����ô�)
	private String p_summary;

	// �����̸�
	private String p_fileName;

	// å ����
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
		return "����:" + p_name + " / ���ǿ���:" + p_public.substring(0, 4) + " / ����:" + p_writer
				+ " / �帣:" + p_genre + " / ���ǻ�:" + p_publisher + " / ���:" + p_summary + " / å ����:" + p_fileName
				+ " / ����:" + p_price + "��";
	}

	public String toSubString() {

		return " / å�̸�:" + p_name + " / ������:" + p_public.substring(0, 4) + " / ����:" + p_writer + " / �帣:" + p_genre
				+ " / ���ǻ�:" + p_publisher;
	}

	@Override
	public String toString() {
		return "[å ��ȣ: " + p_num + "] ����:" + p_name + " / ���ǿ���:" + p_public.substring(0, 4) + " / ����:" + p_writer
				+ " / �帣:" + p_genre + " / ���ǻ�:" + p_publisher + " / ���:" + p_summary + " / å ����:" + p_fileName
				+ " / ����:" + p_price + "��";
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