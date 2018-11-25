package member;

import java.util.ArrayList;

public class MemberServiceImpl implements MemberService {
	
	private MemberDao dao;
	
	public MemberServiceImpl(MemberDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean join(Member m) {
		// TODO Auto-generated method stub
		return dao.insert(m);
	}

	@Override
	public boolean login(String id, String pw) {
		// TODO Auto-generated method stub
		Member m = dao.selectById(id);
		if(m == null) {
			return false;
		} else if(m.getPw().equals(pw)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean out(String id, String pw) {
		// TODO Auto-generated method stub
		Member m = dao.selectById(id);
		if(m == null) {
			return false;
		} else if(m.getPw().equals(pw)) {
			dao.delete(m.getId());
			return true;
		}
		return false;
	}

	@Override
	public Member getMember(String id) {
		// TODO Auto-generated method stub
		return dao.selectById(id);
	}

	@Override
	public ArrayList<Member> getByName(String name) {
		// TODO Auto-generated method stub
		return dao.selectByName(name);
	}

	@Override
	public ArrayList<Member> getByIsSmoke(int isSmoke) {
		// TODO Auto-generated method stub
		return dao.selectByIsSmoke(isSmoke);
	}

	@Override
	public ArrayList<Member> getByType(int type) {
		// TODO Auto-generated method stub
		return dao.selectByType(type);
	}

	@Override
	public ArrayList<Member> getBySex(int sex) {
		// TODO Auto-generated method stub
		return dao.selectBySex(sex);
	}

	@Override
	public ArrayList<Member> getAll() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}

	@Override
	public boolean editInfo(Member m) {
		// TODO Auto-generated method stub
		return dao.update(m);
	}
}