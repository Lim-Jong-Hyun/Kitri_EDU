package car;

public class Car {
	private String owner;
	private String license;
	private String numberPlate;
	private int mileage;
	private String age;
	private String carName;
	private int carSize;
	
	public Car() {
		
	}

	public Car(String owner, String license, String numberPlate, int mileage, String age, String carName, int carSize) {
		super();
		this.owner = owner;
		this.license = license;
		this.numberPlate = numberPlate;
		this.mileage = mileage;
		this.age = age;
		this.carName = carName;
		this.carSize = carSize;
	}

	public String getOwner() {
		return owner;
	}

	public String getLicense() {
		return license;
	}

	public String getNumberPlate() {
		return numberPlate;
	}

	public int getMileage() {
		return mileage;
	}

	public String getAge() {
		return age;
	}

	public String getCarName() {
		return carName;
	}

	public int getCarSize() {
		return carSize;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public void setCarSize(int carSize) {
		this.carSize = carSize;
	}

	@Override
	public String toString() {
		return "Car [owner=" + owner + ", license=" + license + ", numberPlate=" + numberPlate + ", mileage=" + mileage
				+ ", age=" + age + ", carName=" + carName + ", carSize=" + carSize + "]";
	}

	
}
