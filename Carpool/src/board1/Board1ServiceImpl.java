package board1;

import java.util.ArrayList;

public class Board1ServiceImpl implements Board1Service {

	private Board1Dao dao;

	public Board1ServiceImpl(Board1Dao dao) {
		this.dao = dao;
	}
	
	@Override
	public boolean add(Board1 b) {
		// TODO Auto-generated method stub
		return dao.insert(b);
	}

	@Override
	public boolean edit(Board1 b) {
		// TODO Auto-generated method stub
		return dao.update(b);
	}

	@Override
	public Board1 getByNum(int num) {
		// TODO Auto-generated method stub
		return dao.selectByNum(num);
	}

	@Override
	public ArrayList<Board1> getByCate(String cate) {
		// TODO Auto-generated method stub
		return dao.selectByCate(cate);
	}

	@Override
	public ArrayList<Board1> getByTime(String startTime) {
		// TODO Auto-generated method stub
		return dao.selectByTime(startTime);
	}

	@Override
	public ArrayList<Board1> getByStartPosi(String startPosi) {
		// TODO Auto-generated method stub
		return dao.selectByStartPosi(startPosi);
	}

	@Override
	public ArrayList<Board1> getByEndPosi(String endPosi) {
		// TODO Auto-generated method stub
		return dao.selectByEndPosi(endPosi);
	}

	@Override
	public ArrayList<Board1> getByPrice(int price) {
		// TODO Auto-generated method stub
		return dao.selectByPrice(price);
	}

	@Override
	public ArrayList<Board1> getByWriter(String writer) {
		// TODO Auto-generated method stub
		return dao.selectByWriter(writer);
	}

	@Override
	public ArrayList<Board1> getAll() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}

	@Override
	public boolean remove(int num) {
		// TODO Auto-generated method stub
		return dao.delete(num);
	}

	@Override
	public ArrayList<Board1> getByType(int type) {
		// TODO Auto-generated method stub
		return dao.selectByType(type);
	}
	
	@Override
	public ArrayList<Board1> getByStartEnd(String start_posi, String end_posi) {
		return dao.selectByStartEnd(start_posi, end_posi);
	}

	@Override
	public boolean editPassenger(Board1 b) {
		// TODO Auto-generated method stub
		return dao.updatePassenger(b);
	}

	@Override
	public ArrayList<Board1> getByDriver(String driver) {
		// TODO Auto-generated method stub
		return dao.selectByDriver(driver);
	}

	@Override
	public ArrayList<Board1> getByPassenger(String passenger) {
		// TODO Auto-generated method stub
		return dao.selectByPassenger(passenger);
	}

	@Override
	public boolean editDriver(Board1 b) {
		// TODO Auto-generated method stub
		return dao.updateDriver(b);
	}
}