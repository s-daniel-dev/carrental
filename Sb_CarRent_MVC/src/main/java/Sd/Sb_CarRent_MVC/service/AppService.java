package Sd.Sb_CarRent_MVC.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Sd.Sb_CarRent_MVC.db.Database;
import Sd.Sb_CarRent_MVC.dto.AdminDto;
import Sd.Sb_CarRent_MVC.dto.CarDto;
import Sd.Sb_CarRent_MVC.dto.CarListDto;
import Sd.Sb_CarRent_MVC.dto.LeaseDto;
import Sd.Sb_CarRent_MVC.dto.MyBoolean;
import Sd.Sb_CarRent_MVC.dto.ResultDto;
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
			List<CarDto> dtoList = convertCarListToDtoList(cars);
			carListDto = new CarListDto(dtoList);
		}
		
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
		
		db.persistUser(user);
		
		Lease lease = new Lease();
		lease.setId(0);
		lease.setCarId(carId);
		lease.setUserId(user.getId());
		lease.setFrom(from);
		lease.setTo(to);
		
		db.persistLease(lease);
		
		
	}



	public AdminDto getAdminDto() {

		List<Car> cars = db.getCarList();
		
		List<CarDto> carDtoList = convertCarListToDtoList(cars);
		
		List<Lease> leases = db.getLeaseList();
		
		List<LeaseDto> leaseDtoList = convertLeaseListToDtoList(leases, cars);
		
		AdminDto adminDto = new AdminDto(carDtoList, leaseDtoList);
		
		return adminDto;
	}



	private List<LeaseDto> convertLeaseListToDtoList(List<Lease> leases, List<Car> cars) {

		List<LeaseDto> dtoList = new ArrayList<>();
		
		
		for(int index = 0; index < leases.size(); index++) {
			
			Lease lease = leases.get(index);
			String user = db.getUserNameById(lease.getUserId());
			String carName = getCarNameById(cars, lease.getCarId());
		
			
			LeaseDto dto = new LeaseDto(
						lease.getId(),
						user,
						carName,
						lease.getFrom(),
						lease.getTo()
					);
			dtoList.add(dto);
			
		}
		
		
		return dtoList;
	}



	private String getCarNameById(List<Car> cars, int carId) {

		String result = "";
		
		for(int index = 0; index < cars.size(); index++) {
			
			Car currCar = cars.get(index);
			
			if(currCar.getId() == carId) {
				result = currCar.getName();
			}
			
		}
		
		
		return result;
	}



	private List<CarDto> convertCarListToDtoList(List<Car> cars) {

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
		
		return dtoList;
	}



	public ResultDto changeCar(int carId, String name, int feePerDay, String active) {
		
		ResultDto resultDto = new ResultDto();
		boolean isActive = false;
		
		if(active.equals("true")) {
			isActive = true;
		}
		if(isActive == false) {
			boolean deactivatable = isActiveLease(carId);
			if(deactivatable == true) {
				Car car = new Car();
				car.setId(carId);
				car.setName(name);
				car.setActive(isActive);
				car.setFeePerDay(feePerDay);
				car.setPicture(null);
				
				db.mergeCar(car);
				resultDto.setChangeCar(true);
			}
			else {
				resultDto.setChangeCar(false);
			}
		}
		else {
			Car car = new Car();
			car.setId(carId);
			car.setName(name);
			car.setActive(isActive);
			car.setFeePerDay(feePerDay);
			car.setPicture(null);
			
			db.mergeCar(car);
			resultDto.setChangeCar(true);
		}
		
		
		return resultDto;
	}



	private boolean isActiveLease(int carId) {

		boolean result = true;
		List<Lease> leases = db.getLeaseListByCarId(carId);
		
		if(leases.size() > 0) {
			
			for(int index = 0; index < leases.size(); index++) {
				
				Lease lease = leases.get(index);
				LocalDate leaseFrom = lease.getFrom();
				LocalDate now = LocalDate.now();
				if(now.isAfter(leaseFrom)) {
					result = false;
					break;
				}
				
			}
			
		}
		
		return result;
	}



	public ResultDto saveNewCar(String name, int feePerDay, String active) {

		ResultDto resultDto = new ResultDto();
		resultDto.setSaveNewCar(true);
		boolean isActive = false;
		
		if(active.equals("true")) {
			isActive = true;
		}
		
		Car car = new Car();
		car.setId(0);
		car.setFeePerDay(feePerDay);
		car.setName(name);
		car.setPicture(null);
		car.setActive(isActive);
		
		db.persistCar(car);
		
		
		return resultDto;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
