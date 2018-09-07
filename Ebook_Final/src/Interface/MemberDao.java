package Interface;

import java.util.ArrayList;

import DataType.Member;

public interface MemberDao {
	public ArrayList<Member> selectAll();

	public Member selectByMNum(Member m);

	public Member selectByMId(Member m);

	public boolean insert(Member m);

	public boolean delete(Member m);

	public boolean delete(String m_id);

	public boolean update(Member m);

	public ArrayList<Member> searchByMType(boolean m_type);

	public Member selectMId(String m_id);

	public ArrayList<Member> searchAll();

	public ArrayList<Member> searchByMId(String m_id);

	public ArrayList<Member> searchByMNum(int m_num);

	public Member selectByMNum(int num);

	public Member selectByMID(String id);

	public ArrayList<Member> searchByMDate(String date);

	public ArrayList<Member> searchByMName(String name);

	boolean delete(int m_num);
}