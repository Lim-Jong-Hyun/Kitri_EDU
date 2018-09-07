package DataType;

public class PurchasedItem {
	// pk, ������ �ڵ�����
	private int o_num;

	// User �� m_num ���κ��� foreign key
	private int m_num;

	// Item �� p_num ���κ��� foreign key
	private int p_num;

	// ���ſ���
	private boolean p_paid;

	// ���ų�¥
	private String purchasedDate;
	
	// Item �� ��ǰ�̸�
	private String p_name;

	public PurchasedItem() {
	}

	public String mainString() {
		String str = "";
		if (p_paid == true) {
			str = "����";
		} else {
			str = "������";
			purchasedDate = "������";
		}

		return "[ �ֹ���ȣ " + o_num + " ]" + " ����� ��ȣ: " + m_num + " / å ��ȣ: " + p_num + " / å �̸�: " + p_name + " / ���ҿ���:" + str + " / ���ų�¥:"
				+ purchasedDate + "]";
	}

	public String subString() {

		return "å ��ȣ: " + p_num + " / å �̸�: " + p_name + " / ���ų�¥:" + purchasedDate;
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
			str = "����";
		} else {
			str = "������";
			purchasedDate = "������";
		}

		return " å ��ȣ: " + p_num + " / å �̸�: " + p_name + " / ���ҿ���:" + str + " / ���ų�¥:" + purchasedDate;
	}

	public String toString3() {
		String str = "";
		if (p_paid == true) {
			str = "����";
		} else {
			str = "������";
			purchasedDate = "������";
		}

		return "[" + p_num + " ��]" + " å ��ȣ: " + p_num + " / å �̸�: " + p_name + " / ���ҿ���:" + str + " / ���ų�¥:" + purchasedDate;
	}

	@Override
	public String toString() {
		String str = "";
		if (p_paid == true) {
			str = "����";
		} else {
			str = "������";
			purchasedDate = "������";
		}

		return "[ �ֹ���ȣ " + o_num + " ]" + " å ��ȣ: " + p_num + " / å �̸�: " + p_name + " / ���ҿ���:" + str + " / ���ų�¥:" + purchasedDate;
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
