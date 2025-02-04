package Sd.Sb_CarRent_MVC.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Sd.Sb_CarRent_MVC.dto.AdminDto;
import Sd.Sb_CarRent_MVC.dto.CarListDto;
import Sd.Sb_CarRent_MVC.dto.RentDto;
import Sd.Sb_CarRent_MVC.dto.ResultDto;
import Sd.Sb_CarRent_MVC.service.AppService;

@Controller
public class AppController {
	
	private AppService service;
	
	
	@Autowired
	public AppController(AppService service) {
		super();
		this.service = service;
	}

	@GetMapping("/")
	public String index() {
		return "index.html";
	}
	
	@GetMapping("/car/rentable")
	public String showAvailableCars(
			Model model,
			@RequestParam("from") LocalDate from,
			@RequestParam("to") LocalDate to
			) {
		
		CarListDto carListDto = service.getCarListByDate(from, to);
		RentDto rentDto = new RentDto(from, to);
		
		model.addAttribute("carListDto", carListDto);
		model.addAttribute("rentDto", rentDto);
		
		return "index.html";
	}
	
	
	@GetMapping("/rentform")
	public String showRentForm(
				Model model,
				@RequestParam("carid") int carId,
				@RequestParam("from") LocalDate from,
				@RequestParam("to") LocalDate to
			) {
		
		String result = "index.html";
		RentDto rentDto = null;
		boolean dataIsGood = service.isDataCorrect(from, to, carId);
		
		
		if(dataIsGood == true) {
			result = "rentform.html";
			rentDto = new RentDto(from, to, carId);
			model.addAttribute("rentDto", rentDto);
		}
		
		
		return result;
	}
	
	@PostMapping("/rentform/newrent")
	public String saveReservation(
				Model model,
				@RequestParam("name") String name,
				@RequestParam("address") String address,
				@RequestParam("email") String email,
				@RequestParam("pnumber") String phoneNumber,
				@RequestParam("carid") int carId,
				@RequestParam("from") LocalDate from,
				@RequestParam("to") LocalDate to
			) {
		
		service.saveLeaseAndUser(name, address, email, phoneNumber, carId, from, to);
		
		ResultDto resultDto = new ResultDto();
		
		model.addAttribute("resultDto", resultDto);
		
		return "index.html";
	}
	
	
	@GetMapping("/admin")
	public String showAdminInterface(Model model) {
		
		AdminDto adminDto = service.getAdminDto();
		
		model.addAttribute("adminDto", adminDto);
		
		return "admin.html";
	}
	
	@PostMapping("/admin/car/change")
	public String showChangeCarResult(
				Model model,
				@RequestParam("carid") int carId,
				@RequestParam("name") String name,
				@RequestParam("fee") int feePerDay,
				@RequestParam("active") String active
			) {
		
		ResultDto resultDto = service.changeCar(carId, name, feePerDay, active);
		
		AdminDto adminDto = service.getAdminDto();
		
		model.addAttribute("adminDto", adminDto);
		
		model.addAttribute("resultDto", resultDto);
		
		return "admin.html";
	}
	
	@PostMapping("/admin/car/new")
	public String showSaveNewCarResult(
			Model model,
			@RequestParam("name") String name,
			@RequestParam("fee") int feePerDay,
			@RequestParam("active") String active
			) {
		
		ResultDto resultDto = service.saveNewCar(name, feePerDay, active);
		
		AdminDto adminDto = service.getAdminDto();
		
		model.addAttribute("adminDto", adminDto);
		
		model.addAttribute("resultDto", resultDto);
		
		return "admin.html";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
