package Sd.Sb_CarRent_MVC.dto;


import java.util.List;

public class CarListDto {
	
	private List<CarDto> carDtoList;

	
	public CarListDto(List<CarDto> dtoList) {
		this.carDtoList = dtoList;
	}

	
	public List<CarDto> getCarDtoList() {
		return carDtoList;
	}

	public void setCarDtoList(List<CarDto> carDtoList) {
		this.carDtoList = carDtoList;
	}
	

}
