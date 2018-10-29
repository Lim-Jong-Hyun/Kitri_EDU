package car;

import java.util.ArrayList;

public interface CarDao {
	public boolean insert(Car c);

	public boolean delete(String owner);

	public boolean update(Car c);

	public Car select(String owner);

	public ArrayList<Car> selectAll();
}