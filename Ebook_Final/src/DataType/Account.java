package DataType;

public class Account {
	// User 의 m_num 으로부터 fk, pk
	private int m_num;

	// 은행이름
	private String a_name;

	// 계좌번호
	private String a_accountNum;

	// 계좌잔액
	private int a_balance;

	public Account() {
	}

	public Account(int m_num, String a_name, String a_accountNum, int a_balance) {
		this.m_num = m_num;
		this.a_name = a_name;
		this.a_accountNum = a_accountNum;
		this.a_balance = a_balance;
	}

	@Override
	public String toString() {
		return "[계좌정보] 은행명:" + a_name + " / 계좌번호:" + a_accountNum + " / 잔액 :" + a_balance + "원";

	}

	public int getm_num() {
		return m_num;
	}

	public String getA_name() {
		return a_name;
	}

	public String getA_accountNum() {
		return a_accountNum;
	}

	public int getA_balance() {
		return a_balance;
	}

	public void setm_num(int m_num) {
		this.m_num = m_num;
	}

	public void setA_name(String a_name) {
		this.a_name = a_name;
	}

	public void setA_accountNum(String a_accountNum) {
		this.a_accountNum = a_accountNum;
	}

	public void setA_balance(int a_balance) {
		this.a_balance = a_balance;
	}
}
