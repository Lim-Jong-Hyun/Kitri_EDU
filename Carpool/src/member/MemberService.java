package member;

import java.util.ArrayList;

public interface MemberService {
	public boolean join(Member m);
	public boolean login(String id, String pw);
	public boolean out(String id, String pw);
	public boolean editInfo(Member m);
	public Member getMember(String id);
	public ArrayList<Member> getByName(String name);
	public ArrayList<Member> getByIsSmoke(int isSmoke);
	public ArrayList<Member> getByType(int type);
	public ArrayList<Member> getBySex(int sex);
	public ArrayList<Member> getAll();
}
