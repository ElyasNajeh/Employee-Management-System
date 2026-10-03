package application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UpdateButton {
	public Employee employee;
	AlertTypes alert = new AlertTypes();
	EmployeeComboBox ecB = new EmployeeComboBox();
	private int currentIndex = -1;

	public UpdateButton(Employee employee) {
		this.employee = employee;
	}

	public void Display() {

		Stage stage = new Stage();
		MenuBar menuBar = Main.creatmenuBar(stage);
		stage.setTitle("Update Employee Details");

		stage.setWidth(1000);
		stage.setHeight(800);
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(10));
		gp.setHgap(10);
		gp.setVgap(10);

		CustomLabel empNo = new CustomLabel("Employee No");
		CustomTextField empnoF = new CustomTextField();
		empnoF.setText(String.valueOf(Employee.getCount()));
		empnoF.setDisable(true);
		empNo.setMinWidth(100);
		empnoF.setMinWidth(200);
		gp.add(empNo, 0, 0);
		gp.add(empnoF, 1, 0);

		CustomLabel fName1 = new CustomLabel("First Name :");
		CustomTextField fNamef1 = new CustomTextField();
		gp.add(fName1, 0, 1);
		gp.add(fNamef1, 1, 1);

		CustomLabel fName2 = new CustomLabel("Last Name :");
		CustomTextField fNamef2 = new CustomTextField();
		gp.add(fName2, 0, 2);
		gp.add(fNamef2, 1, 2);

		CustomLabel dBirth = new CustomLabel("Date of Birth :");
		DatePicker datePicker = new DatePicker();
		gp.add(dBirth, 0, 3);
		gp.add(datePicker, 1, 3);

		CustomLabel Desg = new CustomLabel("Designation:");
		CustomTextField Desgf = new CustomTextField();
		gp.add(Desg, 0, 4);
		gp.add(Desgf, 1, 4);

		CustomLabel Email = new CustomLabel("Email :");
		CustomTextField Emailf = new CustomTextField();
		gp.add(Email, 0, 5);
		gp.add(Emailf, 1, 5);

		CustomLabel Phone = new CustomLabel("Phone No :");
		CustomTextField Phonef = new CustomTextField();
		gp.add(Phone, 0, 6);
		gp.add(Phonef, 1, 6);

		CustomLabel Natio = new CustomLabel("Nationality :");
		CustomTextField Natiof = new CustomTextField();
		gp.add(Natio, 0, 7);
		gp.add(Natiof, 1, 7);

		CustomLabel Street = new CustomLabel("Street :");
		CustomTextField Streetf = new CustomTextField();
		gp.add(Street, 0, 8);
		gp.add(Streetf, 1, 8);

		CustomLabel City = new CustomLabel("City :");
		CustomTextField Cityf = new CustomTextField();
		gp.add(City, 0, 9);
		gp.add(Cityf, 1, 9);

		CustomLabel Country = new CustomLabel("Country :");
		CustomTextField Countryf = new CustomTextField();
		gp.add(Country, 0, 10);
		gp.add(Countryf, 1, 10);

		CustomLabel educaLabel = new CustomLabel("Education :");
		RadioButtonChoices rbC = new RadioButtonChoices();
		VBox vbC = new VBox(10, rbC.b1, rbC.b2, rbC.b3, rbC.b4, rbC.b5);
		gp.add(educaLabel, 0, 16);
		gp.add(vbC, 1, 16);

		CustomLabel typeEmp = new CustomLabel("Employee Type :");

		typeEmp.setMinWidth(108);
		ecB.setMinWidth(130);
		gp.add(typeEmp, 2, 16);
		gp.add(ecB, 3, 16);

		ImageView employeePhoto = new ImageView();
		employeePhoto.setFitWidth(200);
		employeePhoto.setFitHeight(200);
		employeePhoto.setPreserveRatio(true);

		Image defaultImage = Main.loadImage("/application/images/app-icon.png");
		employeePhoto.setImage(defaultImage);
		employeePhoto.setUserData(null);

		empnoF.setText(employee.getEmpNo());
		fNamef1.setText(employee.getFirstName());
		fNamef2.setText(employee.getLastName());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		datePicker.setValue(LocalDate.parse(employee.getDateOfBirth(), formatter));
		Desgf.setText(employee.getDesignation());
		Emailf.setText(employee.getEmail());
		Phonef.setText(employee.getPhoneNum());
		Natiof.setText(employee.getNationality());
		Streetf.setText(employee.getAdress().getStreet());
		Cityf.setText(employee.getAdress().getCity());
		Countryf.setText(employee.getAdress().getCountry());

		switch (employee.getEducation().toLowerCase()) {
		case "phd":
			rbC.b1.setSelected(true);
			break;

		case "master":
			rbC.b2.setSelected(true);
			break;

		case "b.a":
			rbC.b3.setSelected(true);
			break;

		case "secondary school":
			rbC.b4.setSelected(true);
			break;

		case "primary school":
			rbC.b5.setSelected(true);
			break;

		}
		ecB.setValue(employeeTypeLabel(employee));

		String photoPath = employee.getPhotoPath();
		Path resolvedPhoto = EmployeeData.resolvePhoto(photoPath);
		if (resolvedPhoto != null && Files.isRegularFile(resolvedPhoto)) {
			employeePhoto.setImage(new Image(resolvedPhoto.toUri().toString()));
			employeePhoto.setUserData(photoPath);
		} else {
			employeePhoto.setImage(defaultImage);
			employeePhoto.setUserData(null);
		}
		
		currentIndex = EmployeeData.employeeList.indexOf(employee);
		Path[] selectedPhotoFile = { null };
		
		Runnable updateForm = () -> {
			empnoF.setText(employee.getEmpNo());
			fNamef1.setText(employee.getFirstName());
			fNamef2.setText(employee.getLastName());
			datePicker.setValue(LocalDate.parse(employee.getDateOfBirth(), formatter));
			Desgf.setText(employee.getDesignation());
			Emailf.setText(employee.getEmail());
			Phonef.setText(employee.getPhoneNum());
			Natiof.setText(employee.getNationality());
			Streetf.setText(employee.getAdress().getStreet());
			Cityf.setText(employee.getAdress().getCity());
			Countryf.setText(employee.getAdress().getCountry());
			rbC.b1.setSelected(false);
			rbC.b2.setSelected(false);
			rbC.b3.setSelected(false);
			rbC.b4.setSelected(false);
			rbC.b5.setSelected(false);
			switch (employee.getEducation().toLowerCase()) {
			case "phd":
				rbC.b1.setSelected(true);
				break;
	
			case "master":
				rbC.b2.setSelected(true);
				break;
	
			case "b.a":
				rbC.b3.setSelected(true);
				break;
	
			case "secondary school":
				rbC.b4.setSelected(true);
				break;
	
			case "primary school":
				rbC.b5.setSelected(true);
				break;
	
			}
			ecB.setValue(employeeTypeLabel(employee));
			String photoPathInner = employee.getPhotoPath();
			Path resolvedPhotoInner = EmployeeData.resolvePhoto(photoPathInner);
			if (resolvedPhotoInner != null && Files.isRegularFile(resolvedPhotoInner)) {
				employeePhoto.setImage(new Image(resolvedPhotoInner.toUri().toString()));
				employeePhoto.setUserData(photoPathInner);
			} else {
				employeePhoto.setImage(defaultImage);
				employeePhoto.setUserData(null);
			}
			selectedPhotoFile[0] = null;
		};
		
		CustomButton previousB = new CustomButton("Previous");
		CustomButton nextB = new CustomButton("Next");
		
		Runnable updateNavButtons = () -> {
			previousB.setDisable(currentIndex <= 0);
			nextB.setDisable(currentIndex >= EmployeeData.employeeList.size() - 1);
		};
		
		previousB.setOnAction(x -> {
			if (currentIndex > 0) {
				currentIndex--;
				employee = EmployeeData.employeeList.get(currentIndex);
				updateForm.run();
				updateNavButtons.run();
			}
		});
		
		nextB.setOnAction(x -> {
			if (currentIndex < EmployeeData.employeeList.size() - 1) {
				currentIndex++;
				employee = EmployeeData.employeeList.get(currentIndex);
				updateForm.run();
				updateNavButtons.run();
			}
		});
		
		updateNavButtons.run();

		CustomButton changePhoto = new CustomButton("Change Photo");
		changePhoto.setOnAction(x -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Choose New Employee Photo");
			fileChooser.getExtensionFilters()
					.add(new FileChooser.ExtensionFilter("Image files", "*.png", "*.jpg", "*.jpeg"));
			java.io.File selectedFile = fileChooser.showOpenDialog(stage);
			if (selectedFile != null) {
				selectedPhotoFile[0] = selectedFile.toPath();
				employeePhoto.setImage(new Image(selectedFile.toURI().toString()));
			}
		});

		CustomButton updateB = new CustomButton("Update Employee");
		updateB.setOnAction(x -> {
			AddEmployee validator = new AddEmployee();
			boolean hasRequiredInput = validator.validateInput(fNamef1.getText().trim(), fNamef2.getText().trim(),
					datePicker.getValue(), Desgf.getText().trim(), Emailf.getText().trim(), Phonef.getText().trim(),
					Natiof.getText().trim(), Streetf.getText().trim(), Cityf.getText().trim(),
					Countryf.getText().trim(), (RadioButton) rbC.educationGroup.getSelectedToggle(),
					ecB.getSelectionModel().getSelectedItem(), employeePhoto.getImage());
			if (!hasRequiredInput) {
				return;
			}
			boolean hasValidInput = validator.validateSetEmployee(fNamef1.getText().trim(), fNamef2.getText().trim(),
					datePicker.getValue(), Phonef.getText().trim(), Emailf.getText().trim());
			if (!hasValidInput) {
				return;
			}
			boolean confirmed = alert.ConfirmationAlert("Confirmation",
					"Are you sure you want to update this employee?");
			if (!confirmed) {
				return;
			}

			try {
				Employee updatedEmployee = employeeForType(ecB.getValue(), employee);
				updatedEmployee.setEmpNo(employee.getEmpNo());
				updatedEmployee.setFirstName(fNamef1.getText().trim());
				updatedEmployee.setLastName(fNamef2.getText().trim());
				updatedEmployee.setDateOfBirth(datePicker.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
				updatedEmployee.setDesignation(Desgf.getText().trim());
				updatedEmployee.setEmail(Emailf.getText().trim());
				updatedEmployee.setPhoneNum(Phonef.getText().trim());
				updatedEmployee.setNationality(Natiof.getText().trim());
				updatedEmployee.setAddress(new Address(Streetf.getText().trim(), Cityf.getText().trim(),
						Countryf.getText().trim()));
				updatedEmployee.setEducation(((RadioButton) rbC.educationGroup.getSelectedToggle()).getText());

				String updatedPhotoPath = employee.getPhotoPath();
				if (selectedPhotoFile[0] != null) {
					updatedPhotoPath = EmployeeData.copyPhoto(selectedPhotoFile[0], employee.getEmpNo());
				}
				updatedEmployee.setPhotoPath(updatedPhotoPath);
				Path storedPhoto = EmployeeData.resolvePhoto(updatedPhotoPath);
				if (storedPhoto != null && Files.isRegularFile(storedPhoto)) {
					updatedEmployee.setEmpPhoto(new Image(storedPhoto.toUri().toString()));
				}

				EmployeeData.employeeList.set(currentIndex, updatedEmployee);
				employee = updatedEmployee;
				updateForm.run();
				updateNavButtons.run();
				alert.InfoAlert("Success", "Employee information updated successfully.");
			} catch (IOException exception) {
				alert.ErrorAlert("Error", "Failed to update the photo: " + exception.getMessage());
			}
		});

		CustomButton backB = new CustomButton("Back");
		backB.setOnAction(x -> {
			stage.close();
		});

		HBox hb = new HBox(50, previousB, updateB, nextB, backB);
		hb.setAlignment(Pos.CENTER);
		hb.setPadding(new Insets(20));
		VBox photoBox = new VBox(15);
		photoBox.setAlignment(Pos.CENTER);
		CustomLabel photoLabel = new CustomLabel("Employee Photo");
		photoLabel.setAlignment(Pos.CENTER);
		photoBox.getChildren().addAll(employeePhoto, photoLabel, changePhoto);
		BorderPane bp = new BorderPane();
		bp.setTop(menuBar);
		bp.setCenter(gp);
		bp.setBottom(hb);
		bp.setRight(photoBox);
		Scene scene = new Scene(bp);
		Main.applyStyles(scene);
		stage.getIcons().add(Main.loadImage("/application/images/app-icon.png"));
		stage.setMaximized(true);
		stage.setScene(scene);
		stage.showAndWait();
	}

	private String employeeTypeLabel(Employee value) {
		if (value instanceof EmployeeBasedComession) {
			return "Commession Based Employee";
		}
		if (value instanceof HourlyEmployee) {
			return "Hourly Employee";
		}
		if (value instanceof SalariedEmployee) {
			return "Salaried Employee";
		}
		return "Commession Employee";
	}

	private Employee employeeForType(String type, Employee currentEmployee) {
		switch (type) {
		case "Hourly Employee":
			if (currentEmployee.getClass() == HourlyEmployee.class) {
				return currentEmployee;
			}
			HourlyEmployee hourly = new HourlyEmployee();
			hourly.setHours((short) 1);
			hourly.setRate(2.5f);
			return hourly;
		case "Salaried Employee":
			if (currentEmployee.getClass() == SalariedEmployee.class) {
				return currentEmployee;
			}
			SalariedEmployee salaried = new SalariedEmployee();
			salaried.setAnnualSalary(4075);
			return salaried;
		case "Commession Employee":
			if (currentEmployee.getClass() == CommessionEmployee.class) {
				return currentEmployee;
			}
			return new CommessionEmployee();
		case "Commession Based Employee":
			if (currentEmployee.getClass() == EmployeeBasedComession.class) {
				return currentEmployee;
			}
			return new EmployeeBasedComession();
		default:
			throw new IllegalArgumentException("Unsupported employee type: " + type);
		}
	}

}
