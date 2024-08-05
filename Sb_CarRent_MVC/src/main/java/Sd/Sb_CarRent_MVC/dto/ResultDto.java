package Sd.Sb_CarRent_MVC.dto;

public class ResultDto {
	
	private boolean success;
	private Boolean saveNewCar;
	private Boolean changeCar;

	public ResultDto() {
		super();
		this.success = true;
		this.saveNewCar = null;
		this.changeCar = null;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Boolean getSaveNewCar() {
		return saveNewCar;
	}

	public void setSaveNewCar(Boolean saveNewCar) {
		this.saveNewCar = saveNewCar;
	}

	public Boolean getChangeCar() {
		return changeCar;
	}

	public void setChangeCar(Boolean changeCar) {
		this.changeCar = changeCar;
	}

	

}
