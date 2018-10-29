package car;

import java.util.ArrayList;

public interface CarService {
	public boolean insertCar(Car c);

	public boolean delCar(String owner);

	public boolean updateCar(Car c);

	public Car getCar(String owner);

	public ArrayList<Car> getAll();
}
