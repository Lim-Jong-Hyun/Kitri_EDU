package DataType;

public class PurchasedItem {
	// pk, 시퀀스 자동배정
	private int o_num;

	// User 의 m_num 으로부터 foreign key
	private int m_num;

	// Item 의 p_num 으로부터 foreign key
	private int p_num;

	// 구매여부
	private boolean p_paid;

	// 구매날짜
	private String purchasedDate;
	
	// Item 의 제품이름
	private String p_name;

	public PurchasedItem() {
	}

	public String mainString() {
		String str = "";
		if (p_paid == true) {
			str = "지불";
		} else {
			str = "미지불";
			purchasedDate = "미지불";
		}

		return "[ 주문번호 " + o_num + " ]" + " 사용자 번호: " + m_num + " / 책 번호: " + p_num + " / 책 이름: " + p_name + " / 지불여부:" + str + " / 구매날짜:"
				+ purchasedDate + "]";
	}

	public String subString() {

		return "책 번호: " + p_num + " / 책 이름: " + p_name + " / 구매날짜:" + purchasedDate;
	}

	public PurchasedItem(int o_num, int m_num, int p_num, boolean p_paid, String purchasedDate, String p_name) {
		this.o_num = o_num;
		this.m_num = m_num;
		this.p_num = p_num;
		this.p_paid = p_paid;
		this.purchasedDate = purchasedDate;
		this.p_name = p_name;
	}

	public String toString2() {
		String str = "";
		if (p_paid == true) {
			str = "지불";
		} else {
			str = "미지불";
			purchasedDate = "미지불";
		}

		return " 책 번호: " + p_num + " / 책 이름: " + p_name + " / 지불여부:" + str + " / 구매날짜:" + purchasedDate;
	}

	public String toString3() {
		String str = "";
		if (p_paid == true) {
			str = "지불";
		} else {
			str = "미지불";
			purchasedDate = "미지불";
		}

		return "[" + p_num + " 번]" + " 책 번호: " + p_num + " / 책 이름: " + p_name + " / 지불여부:" + str + " / 구매날짜:" + purchasedDate;
	}

	@Override
	public String toString() {
		String str = "";
		if (p_paid == true) {
			str = "지불";
		} else {
			str = "미지불";
			purchasedDate = "미지불";
		}

		return "[ 주문번호 " + o_num + " ]" + " 책 번호: " + p_num + " / 책 이름: " + p_name + " / 지불여부:" + str + " / 구매날짜:" + purchasedDate;
	}

	
	
	public int getM_num() {
		return m_num;
	}

	public String getP_name() {
		return p_name;
	}

	public void setM_num(int m_num) {
		this.m_num = m_num;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public int getO_num() {
		return o_num;
	}

	public int getm_num() {
		return m_num;
	}

	public int getP_num() {
		return p_num;
	}

	public boolean isP_paid() {
		return p_paid;
	}

	public String getPurchasedDate() {
		return purchasedDate;
	}

	public void setO_num(int o_num) {
		this.o_num = o_num;
	}

	public void setm_num(int m_num) {
		this.m_num = m_num;
	}

	public void setP_num(int p_num) {
		this.p_num = p_num;
	}

	public void setP_paid(boolean p_paid) {
		this.p_paid = p_paid;
	}

	public void setPurchasedDate(String purchasedDate) {
		this.purchasedDate = purchasedDate;
	}

}
