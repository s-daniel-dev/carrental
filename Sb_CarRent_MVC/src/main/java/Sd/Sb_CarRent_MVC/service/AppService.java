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

}
