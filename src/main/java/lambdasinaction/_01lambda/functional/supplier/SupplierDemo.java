package lambdasinaction._01lambda.functional.supplier;

import java.util.function.Supplier;

class Vehicle {
	public void drive() {
		System.out.println("Driving vehicle ...");
	}
}

class Car extends Vehicle {
	@Override
	public void drive() {
		System.out.println("Driving car...");
	}
}

public class SupplierDemo {
	static void driveVehicle(Supplier<? extends Vehicle> supplier) {
		Vehicle vehicle = supplier.get();
		vehicle.drive();
	}

	public static void main(String[] args) {
		// Using Lambda expression
		driveVehicle(() -> new Vehicle());
		driveVehicle(() -> new Car());

		// Using Method Reference
		driveVehicle(Vehicle::new);
		driveVehicle(Car::new);
	}
}