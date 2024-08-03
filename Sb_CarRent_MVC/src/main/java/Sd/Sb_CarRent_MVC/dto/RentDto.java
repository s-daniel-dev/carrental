package Sd.Sb_CarRent_MVC.dto;

import java.time.LocalDate;

public class RentDto {
	
	private LocalDate from;
	private LocalDate to;
	private Integer carId;
	
	
	public RentDto(LocalDate from, LocalDate to) {
		super();
		this.from = from;
		this.to = to;
		this.carId = null;
	}
	

	public RentDto(LocalDate from, LocalDate to, Integer carId) {
		super();
		this.from = from;
		this.to = to;
		this.carId = carId;
	}




	public LocalDate getFrom() {
		return from;
	}

	public void setFrom(LocalDate from) {
		this.from = from;
	}

	public LocalDate getTo() {
		return to;
	}

	public void setTo(LocalDate to) {
		this.to = to;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	

}
