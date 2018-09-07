package ServiceImpl;

import java.util.ArrayList;
import java.util.Scanner;

import DataType.Member;
import Interface.MemberDao;
import Interface.MemberService;

public class MemberServiceImpl implements MemberService {
	private MemberDao dao;
	Scanner sc = new Scanner(System.in);

	public MemberServiceImpl(MemberDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean join(Member m) {
		// TODO Auto-generated method stub
		return dao.insert(m);
	}

	@Override
	public Member login(Member m) {
		// TODO Auto-generated method stub
		Member m2 = dao.selectMId(m.getM_id());
		if (m2 == null) {
			System.out.println("없는 아이디");
		} else {
			if (m2.getM_pw().equals(m.getM_pw())) {
				return m2;
			} else {
				System.out.println("패스워드 불일치");
			}
		}
		return null;
	}

	@Override
	public Member selectById(String id) {
		// TODO Auto-generated method stub
		return dao.selectByMID(id);
	}

	@Override
	public Member selectByNum(int num) {
		// TODO Auto-generated method stub
		return dao.selectByMNum(num);
	}

	@Override
	public ArrayList<Member> alluserInfo() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}

	@Override
	public ArrayList<Member> selectByDate(String date) {
		// TODO Auto-generated method stub
		return dao.searchByMDate(date);
	}

	@Override
	public ArrayList<Member> selectByName(String name) {
		// TODO Auto-generated method stub
		return dao.searchByMName(name);
	}

	@Override
	public Member getMyInfo(String m_id) {
		// TODO Auto-generated method stub
		return dao.selectMId(m_id);
	}

	@Override
	public void editMyInfo(Member m) {
		// TODO Auto-generated method stub
		dao.update(m);
	}

	@Override
	public boolean out(int m_num) {
		// TODO Auto-generated method stub
		return dao.delete(m_num);
	}

	@Override
	public ArrayList<Member> searchByMNum(int m_num) {
		// TODO Auto-generated method stub
		return dao.searchByMNum(m_num);
	}

	@Override
	public ArrayList<Member> searchByMId(String m_id) {
		// TODO Auto-generated method stub
		return dao.searchByMId(m_id);
	}

	@Override
	public ArrayList<Member> searchByMName(String m_name) {
		// TODO Auto-generated method stub
		return dao.searchByMName(m_name);
	}

	@Override
	public ArrayList<Member> searchByMDate(String m_birth) {
		// TODO Auto-generated method stub
		return dao.searchByMDate(m_birth);
	}

	@Override
	public ArrayList<Member> searchByMType(boolean m_type) {
		// TODO Auto-generated method stub
		return dao.searchByMType(m_type);
	}

	@Override
	public ArrayList<Member> searchAll() {
		// TODO Auto-generated method stub
		return dao.searchAll();
	}

	@Override
	public ArrayList<Member> selectByMId(String m_id) {
		// TODO Auto-generated method stub
		return null;
	}
}
