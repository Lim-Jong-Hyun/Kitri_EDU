package member;

public class Member {
	private String id;
	private String pw;
	private String name;
	private String email;
	private String tel;
	private String profile;
	private int type;
	private int sex;
	private int isSmoke;
	
	public Member() {
		
	}

	public Member(String id, String pw, String name, String email, String tel, String profile, int type, int sex,
			int isSmoke) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.tel = tel;
		this.profile = profile;
		this.type = type;
		this.sex = sex;
		this.isSmoke = isSmoke;
	}

	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getTel() {
		return tel;
	}

	public String getProfile() {
		return profile;
	}

	public int getType() {
		return type;
	}

	public int getSex() {
		return sex;
	}

	public int getisSmoke() {
		return isSmoke;
	}
	
	public int getIsSmoke() {
		return isSmoke;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public void setIsSmoke(int isSmoke) {
		this.isSmoke = isSmoke;
	}
	
}