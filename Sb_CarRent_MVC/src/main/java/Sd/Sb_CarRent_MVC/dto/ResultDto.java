package Sd.Sb_CarRent_MVC.dto;

public class ResultDto {
	
	private boolean success;
	private MyBoolean saveNewCar;
	private MyBoolean changeCar;

	public ResultDto() {
		super();
		this.success = true;
		this.saveNewCar = MyBoolean.NULL;
		this.changeCar = MyBoolean.NULL;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public MyBoolean getSaveNewCar() {
		return saveNewCar;
	}

	public void setSaveNewCar(MyBoolean saveNewCar) {
		this.saveNewCar = saveNewCar;
	}

	public MyBoolean getChangeCar() {
		return changeCar;
	}

	public void setChangeCar(MyBoolean changeCar) {
		this.changeCar = changeCar;
	}

	
	

}
