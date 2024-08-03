package Sd.Sb_CarRent_MVC.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Sd.Sb_CarRent_MVC.db.Database;
import Sd.Sb_CarRent_MVC.dto.CarDto;
import Sd.Sb_CarRent_MVC.dto.CarListDto;
import Sd.Sb_CarRent_MVC.model.Car;
import Sd.Sb_CarRent_MVC.model.Lease;
import Sd.Sb_CarRent_MVC.model.User;

@Service
public class AppService {
	
	private Database db;
	
	
	@Autowired
	public AppService(Database db) {
		super();
		this.db = db;
	}



	public CarListDto getCarListByDate(LocalDate from, LocalDate to) {

		CarListDto carListDto = null;
		
		List<Car> cars = db.getCarsBetweenDates(from, to);
		
		if(cars.size() > 0) {
			carListDto = convertCarListToCarListDto(cars);
		}
		
		return carListDto;
	}



	private CarListDto convertCarListToCarListDto(List<Car> cars) {

		List<CarDto> dtoList = new ArrayList<>();
		
		for(int index = 0; index < cars.size(); index++) {
			
			Car car = cars.get(index);
			CarDto carDto = new CarDto(
						car.getId(),
						car.getName(),
						car.getFeePerDay(),
						car.isActive(),
						car.getPicture()
					);
			dtoList.add(carDto);
		}
		
		CarListDto carListDto = new CarListDto(dtoList);
		
	
		
		return carListDto;
	}



	public boolean isDataCorrect(LocalDate from, LocalDate to, int carId) {

		boolean result = false;
		
		List<Car> cars = db.getCarsBetweenDates(from, to);
		
		for(int index = 0; index < cars.size(); index++) {
			
			int currCarId = cars.get(index).getId();
			if(currCarId == carId) {
				result = true;
				break;
			}
		}
		
		return result;
	}



	public void saveLeaseAndUser(
				String name, String address, 
				String email, String phoneNumber,
				int carId, LocalDate from,
				LocalDate to
			) {

		User user = new User();
		user.setId(0);
		user.setAddress(address);
		user.setEmail(email);
		user.setPhoneNumber(phoneNumber);
		user.setName(name);
		
		int userId = db.persistUser(user);
		
		Lease lease = new Lease();
		lease.setId(0);
		lease.setCarId(carId);
		lease.setUserId(userId);
		lease.setFrom(from);
		lease.setTo(to);
		
		db.persistLease(lease);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
