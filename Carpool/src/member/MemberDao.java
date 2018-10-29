package member;

import java.util.ArrayList;

public interface MemberDao {
	public boolean insert(Member m);
	public boolean delete(String id);
	public boolean update(Member m);
	public Member selectById(String id);
	public ArrayList<Member> selectByName(String name);
	public ArrayList<Member> selectByIsSmoke(int isSmoke);
	public ArrayList<Member> selectByType(int type);
	public ArrayList<Member> selectBySex(int sex);
	public ArrayList<Member> selectAll();
}
