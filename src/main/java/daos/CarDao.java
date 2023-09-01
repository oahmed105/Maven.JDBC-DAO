package daos;

import models.Cars;

import java.util.Set;

public interface CarDao {
    Cars getCar(int id);
    Set<Cars> getAllCars();
    Cars getCarByMakeAndModel(String make, String model);
    boolean insertCar(Cars car);
    boolean updateCar(Cars car);
    boolean deleteCar(int id);
}
