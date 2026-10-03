package application;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeleteButton {
	public int currentIndex = 0;
	public List<Employee> employeeList = EmployeeData.employeeList;
	public Employee employee;
	AlertTypes alert = new AlertTypes();
	EmployeeComboBox ecB = new EmployeeComboBox();

	public DeleteButton() {
	}

	public DeleteButton(Employee employee) {
		this.employee = employee;
		this.currentIndex = employeeList.indexOf(employee);
	}

	public void Display() {
		Stage stage = new Stage();
		MenuBar menuBar = Main.creatmenuBar(stage);
		stage.setTitle("Delete Employee");

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
		fNamef1.setDisable(true);
		gp.add(fName1, 0, 1);
		gp.add(fNamef1, 1, 1);

		CustomLabel fName2 = new CustomLabel("Last Name :");
		CustomTextField fNamef2 = new CustomTextField();
		fNamef2.setDisable(true);
		gp.add(fName2, 0, 2);
		gp.add(fNamef2, 1, 2);

		CustomLabel dBirth = new CustomLabel("Date of Birth :");
		CustomTextField dobF = new CustomTextField();
		dobF.setDisable(true);
		gp.add(dBirth, 0, 3);
		gp.add(dobF, 1, 3);

		CustomLabel Desg = new CustomLabel("Designation:");
		CustomTextField Desgf = new CustomTextField();
		Desgf.setDisable(true);
		gp.add(Desg, 0, 4);
		gp.add(Desgf, 1, 4);

		CustomLabel Email = new CustomLabel("Email :");
		CustomTextField Emailf = new CustomTextField();
		Emailf.setDisable(true);
		gp.add(Email, 0, 5);
		gp.add(Emailf, 1, 5);

		CustomLabel Phone = new CustomLabel("Phone No :");
		CustomTextField Phonef = new CustomTextField();
		Phonef.setDisable(true);
		gp.add(Phone, 0, 6);
		gp.add(Phonef, 1, 6);

		CustomLabel Natio = new CustomLabel("Nationality :");
		CustomTextField Natiof = new CustomTextField();
		Natiof.setDisable(true);
		gp.add(Natio, 0, 7);
		gp.add(Natiof, 1, 7);

		CustomLabel Street = new CustomLabel("Street :");
		CustomTextField Streetf = new CustomTextField();
		Streetf.setDisable(true);
		gp.add(Street, 0, 8);
		gp.add(Streetf, 1, 8);

		CustomLabel City = new CustomLabel("City :");
		CustomTextField Cityf = new CustomTextField();
		Cityf.setDisable(true);
		gp.add(City, 0, 9);
		gp.add(Cityf, 1, 9);

		CustomLabel Country = new CustomLabel("Country :");
		CustomTextField Countryf = new CustomTextField();
		Countryf.setDisable(true);
		gp.add(Country, 0, 10);
		gp.add(Countryf, 1, 10);

		CustomLabel educaLabel = new CustomLabel("Education :");
		RadioButtonChoices rbC = new RadioButtonChoices();
		VBox vbC = new VBox(10, rbC.b1, rbC.b2, rbC.b3, rbC.b4, rbC.b5);
		rbC.b1.setDisable(true);
		rbC.b2.setDisable(true);
		rbC.b3.setDisable(true);
		rbC.b4.setDisable(true);
		rbC.b5.setDisable(true);
		gp.add(educaLabel, 0, 16);
		gp.add(vbC, 1, 16);

		CustomLabel typeEmp = new CustomLabel("Employee Type :");
		typeEmp.setMinWidth(108);
		ecB.setMinWidth(130);
		gp.add(typeEmp, 2, 16);
		gp.add(ecB, 3, 16);
		ecB.setDisable(true);

		ImageView employeePhoto = new ImageView();
		employeePhoto.setFitWidth(200);
		employeePhoto.setFitHeight(200);
		employeePhoto.setPreserveRatio(true);

		CustomLabel photo = new CustomLabel("Employee Photo");
		VBox vb = new VBox(15, employeePhoto, photo);
		vb.setAlignment(Pos.CENTER);
		vb.setPadding(new Insets(20));

		loadEmployeeDetails(currentIndex, empnoF, fNamef1, fNamef2, dobF, Desgf, Emailf, Phonef, Natiof, Streetf, Cityf,
				Countryf, rbC, ecB, employeePhoto);

		CustomButton deleteE = new CustomButton("Delete Employee");
		deleteE.setOnAction(x -> {
			int previousSize = employeeList.size();
			new DeleteButtonHandler(employeeList, currentIndex, alert).handle(x);
			if (employeeList.size() < previousSize) {
				stage.close();
			}
		});

		CustomButton back = new CustomButton("Back");
		back.setOnAction(x -> {
			stage.close();
		});
		HBox hb = new HBox(100, deleteE, back);
		hb.setAlignment(Pos.CENTER);
		hb.setPadding(new Insets(20));
		BorderPane bp = new BorderPane();
		bp.setTop(menuBar);
		bp.setCenter(gp);
		bp.setBottom(hb);
		bp.setRight(vb);
		Scene scene = new Scene(bp);
		Main.applyStyles(scene);
		stage.getIcons().add(Main.loadImage("/application/images/app-icon.png"));
		stage.setMaximized(true);
		stage.setScene(scene);
		stage.showAndWait();
	}

	public void loadEmployeeDetails(int index, CustomTextField empnoF, CustomTextField fNamef1, CustomTextField fNamef2,
			CustomTextField dobF, CustomTextField Desgf, CustomTextField Emailf, CustomTextField Phonef,
			CustomTextField Natiof, CustomTextField Streetf, CustomTextField Cityf, CustomTextField Countryf,
			RadioButtonChoices rbC, EmployeeComboBox ecB, ImageView employeePhoto) {
		if (index < 0 || index >= employeeList.size()) {
			return;
		}
		Employee employee = employeeList.get(index);
		empnoF.setText(employee.getEmpNo());
		fNamef1.setText(employee.getFirstName());
		fNamef2.setText(employee.getLastName());
		dobF.setText(employee.getDateOfBirth());
		Desgf.setText(employee.getDesignation());
		Emailf.setText(employee.getEmail());
		Phonef.setText(employee.getPhoneNum());
		Natiof.setText(employee.getNationality());
		Streetf.setText(employee.getAdress().getStreet());
		Cityf.setText(employee.getAdress().getCity());
		Countryf.setText(employee.getAdress().getCountry());
		switch (employee.getEducation().trim().toLowerCase()) {
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
		if (employee instanceof EmployeeBasedComession) {
			ecB.setValue("Commession Based Employee");
		} else if (employee instanceof HourlyEmployee) {
			ecB.setValue("Hourly Employee");
		} else if (employee instanceof SalariedEmployee) {
			ecB.setValue("Salaried Employee");
		} else if (employee instanceof CommessionEmployee) {
			ecB.setValue("Commession Employee");
		}
		String photoPath = employee.getPhotoPath();
		if (photoPath != null && !photoPath.trim().isEmpty()) {
			Path file = EmployeeData.resolvePhoto(photoPath);
			if (file != null && Files.isRegularFile(file)) {
				employeePhoto.setImage(new Image(file.toUri().toString()));
			}

		}

	}
}
