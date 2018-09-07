package DataType;

public class Review {
	// �����ȣ (pk, ������ �ڵ�����)
	private int r_num;

	// ���� ������ ����ѹ� fk
	private int m_num;

	// Item p_num fk
	private int p_num;

	// ����
	private int r_score;

	// ���䳻��
	private String r_contents;

	public Review() {
	}

	public Review(int r_num, int m_num, int p_num, int r_score, String r_contents) {
		this.r_num = r_num;
		this.m_num = m_num;
		this.p_num = p_num;
		this.r_score = r_score;
		this.r_contents = r_contents;
	}

	public String toSubString() {
		String str = String.valueOf(r_score);
		if (r_score == 1) {
			str = "��";
		} else if (r_score == 2) {
			str = "�ڡ�";
		} else if (r_score == 3) {
			str = "�ڡڡ�";
		} else if (r_score == 4) {
			str = "�ڡڡڡ�";
		} else if (r_score == 5) {
			str = "�ڡڡڡڡ�";
		}

		return "[" + r_num + " ��] " + " å��ȣ:" + p_num + " / ����:" + str;

	}

	public String toSubString2() {
		String str = String.valueOf(r_score);
		if (r_score == 1) {
			str = "��";
		} else if (r_score == 2) {
			str = "�ڡ�";
		} else if (r_score == 3) {
			str = "�ڡڡ�";
		} else if (r_score == 4) {
			str = "�ڡڡڡ�";
		} else if (r_score == 5) {
			str = "�ڡڡڡڡ�";
		}

		return "å��ȣ:" + p_num + " / ����:" + str + " / ����:" + r_contents;

	}

	public String toSubString3() {
		return "\n- ���� -\n" + r_contents;
	}

	@Override
	public String toString() {
		return "Review [r_num=" + r_num + ", m_num=" + m_num + ", p_num=" + p_num + ", r_score=" + r_score
				+ ", r_contents=" + r_contents + "]";
	}

	public int getR_num() {
		return r_num;
	}

	public int getM_num() {
		return m_num;
	}

	public int getP_num() {
		return p_num;
	}

	public int getR_score() {
		return r_score;
	}

	public String getR_contents() {
		return r_contents;
	}

	public void setR_num(int r_num) {
		this.r_num = r_num;
	}

	public void setM_num(int m_num) {
		this.m_num = m_num;
	}

	public void setP_num(int p_num) {
		this.p_num = p_num;
	}

	public void setR_score(int r_score) {
		this.r_score = r_score;
	}

	public void setR_contents(String r_contents) {
		this.r_contents = r_contents;
	}
}
