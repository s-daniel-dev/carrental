package Sd.Sb_CarRent_MVC.dto;

import java.util.List;

public class AdminDto {
	
	private List<CarDto> carDtoList;
	private List<LeaseDto> leaseDtoList;
	
	
	public AdminDto(List<CarDto> carDtoList, List<LeaseDto> leaseDtoList) {
		super();
		this.carDtoList = carDtoList;
		this.leaseDtoList = leaseDtoList;
	}


	public List<CarDto> getCarDtoList() {
		return carDtoList;
	}


	public void setCarDtoList(List<CarDto> carDtoList) {
		this.carDtoList = carDtoList;
	}


	public List<LeaseDto> getLeaseDtoList() {
		return leaseDtoList;
	}


	public void setLeaseDtoList(List<LeaseDto> leaseDtoList) {
		this.leaseDtoList = leaseDtoList;
	}
	
	
	
	
	

}
