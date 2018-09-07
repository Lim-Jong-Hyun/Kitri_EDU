package Interface;

import java.util.ArrayList;

import DataType.Member;

public interface MemberService {
	public boolean join(Member m);
	
	public Member login(Member m);
	
	public Member getMyInfo(String m_id);

	public void editMyInfo(Member m);
	
	public ArrayList<Member> selectByMId(String m_id);
	
	public ArrayList<Member> searchByMNum(int m_num);
	
	public ArrayList<Member> searchByMId(String m_id);
	
	public ArrayList<Member> searchByMName(String m_name);
	
	public Member selectById(String id);
	
	public Member selectByNum(int num);
	
	public ArrayList<Member> selectByDate(String date);
	
	public ArrayList<Member> selectByName(String name);
	
	public ArrayList<Member> alluserInfo();

	public ArrayList<Member> searchByMType(boolean m_type);
		
	public ArrayList<Member> searchAll();

	public ArrayList<Member> searchByMDate(String m_birth);

	public boolean out(int m_num);
}
