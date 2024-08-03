package Sd.Sb_CarRent_MVC.dto;

import java.util.Arrays;

public class CarDto {
	
	private int id;
	private String name;
	private int feePerDay;
	private boolean active;
	private byte[] picture;
	
	
	public CarDto(int id, String name, int feePerDay, boolean active, byte[] picture) {
		super();
		this.id = id;
		this.name = name;
		this.feePerDay = feePerDay;
		this.active = active;
		this.picture = picture;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFeePerDay() {
		return feePerDay;
	}

	public void setFeePerDay(int feePerDay) {
		this.feePerDay = feePerDay;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}


	@Override
	public String toString() {
		return "CarDto [id=" + id + ", name=" + name + ", feePerDay=" + feePerDay + ", active=" + active + ", picture="
				+ Arrays.toString(picture) + "]";
	}
	
	
	
	
	

}
