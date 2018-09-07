package DataType;

public class Member {

	// 사용자 번호 (seq_user 로 자동생성, primary key)
	private int m_num;

	// 사용자 타입 (false 이면 일반사용자, true 이면 관리자)
	private boolean m_type;

	// 아이디
	private String m_id;

	// 비밀번호
	private String m_pw;

	// 이름
	private String m_name;

	// 계좌
	private String m_email;

	// 계정 생성 년월일
	private String m_birth;

	public Member() {
	}

	public Member(int m_num, boolean m_type, String m_id, String m_pw, String m_name, String m_email, String m_birth) {
		this.m_num = m_num;
		this.m_type = m_type;
		this.m_id = m_id;
		this.m_pw = m_pw;
		this.m_name = m_name;
		this.m_email = m_email;
		this.m_birth = m_birth;
	}

	@Override
	public String toString() {
		return "[ 회원정보 ] ID:" + m_id + " / PassWord:" + m_pw + " / 이름:" + m_name + " / email:" + m_email + " / ID 생성일:"
				+ m_birth;
	}

	public int getM_num() {
		return m_num;
	}

	public boolean isM_type() {
		return m_type;
	}

	public String getM_id() {
		return m_id;
	}

	public String getM_pw() {
		return m_pw;
	}

	public String getM_name() {
		return m_name;
	}

	public String getM_email() {
		return m_email;
	}

	public String getM_birth() {
		return m_birth;
	}

	public void setM_num(int m_num) {
		this.m_num = m_num;
	}

	public void setM_type(boolean m_type) {
		this.m_type = m_type;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	public void setM_birth(String m_birth) {
		this.m_birth = m_birth;
	}
}
