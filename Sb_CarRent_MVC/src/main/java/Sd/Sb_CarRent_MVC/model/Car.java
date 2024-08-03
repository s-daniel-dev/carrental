package Sd.Sb_CarRent_MVC.model;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "fee_per_day")
	private int feePerDay;
	
	@Column(name = "active")
	private boolean active;
	
	@Column(name = "picture")
	@Lob
	private byte[] picture;

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
		return "Car [id=" + id + ", name=" + name + ", feePerDay=" + feePerDay + ", active=" + active + ", picture="
				+ Arrays.toString(picture) + "]";
	}
	
	
	


}
