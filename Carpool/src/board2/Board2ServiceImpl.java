package board2;

import java.util.ArrayList;

public class Board2ServiceImpl implements Board2Service {

	private Board2Dao dao;

	public Board2ServiceImpl(Board2Dao dao) {
		this.dao = dao;
	}
	
	@Override
	public boolean add(Board2 b) {
		// TODO Auto-generated method stub
		return dao.insert(b);
	}

	@Override
	public boolean edit(Board2 b) {
		// TODO Auto-generated method stub
		return dao.update(b);
	}

	@Override
	public Board2 gettByNum(int num) {
		// TODO Auto-generated method stub
		return dao.selectByNum(num);
	}

	@Override
	public ArrayList<Board2> getByType(int type) {
		// TODO Auto-generated method stub
		return dao.selectByType(type);
	}

	@Override
	public ArrayList<Board2> getByWriter(String writer) {
		// TODO Auto-generated method stub
		return dao.selectByWriter(writer);
	}

	@Override
	public ArrayList<Board2> getAll() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}

	@Override
	public boolean remove(int num) {
		// TODO Auto-generated method stub
		return dao.delete(num);
	}

}
