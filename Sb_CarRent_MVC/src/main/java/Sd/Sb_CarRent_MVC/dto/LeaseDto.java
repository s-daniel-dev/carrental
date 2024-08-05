package Sd.Sb_CarRent_MVC.dto;

import java.time.LocalDate;

public class LeaseDto {
	
	private int id;
	private String userName;
	private String carName;
	private LocalDate from;
	private LocalDate to;
	
	
	public LeaseDto(int id, String userName, String carName, LocalDate from, LocalDate to) {
		super();
		this.id = id;
		this.userName = userName;
		this.carName = carName;
		this.from = from;
		this.to = to;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
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
	
	
}
