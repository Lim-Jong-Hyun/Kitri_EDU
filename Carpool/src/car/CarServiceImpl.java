package car;

import java.util.ArrayList;

public class CarServiceImpl implements CarService {
	private CarDao dao;

	public CarServiceImpl(CarDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean insertCar(Car c) {
		// TODO Auto-generated method stub
		return dao.insert(c);
	}

	@Override
	public boolean delCar(String owner) {
		// TODO Auto-generated method stub
		return dao.delete(owner);
	}

	@Override
	public boolean updateCar(Car c) {
		// TODO Auto-generated method stub
		return dao.update(c);
	}

	@Override
	public Car getCar(String owner) {
		// TODO Auto-generated method stub
		return dao.select(owner);
	}

	@Override
	public ArrayList<Car> getAll() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}
}