package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;

public class ViewEmployee {

	AlertTypes alert = new AlertTypes();
	TableView<Employee> tableView = new TableView<>();
	CustomButton updateButton;
	CustomButton deleteButton;

	public void Display() {
		Stage stage = new Stage();
		MenuBar menuBar = Main.creatmenuBar(stage);
		stage.setTitle("View Employees");

		CustomLabel empNoL = new CustomLabel("Employee No :");
		empNoL.setMinWidth(150);
		CustomTextField empnoFi = new CustomTextField();
		empnoFi.setMinWidth(150);

		CustomButton searchButton = new CustomButton("Search");
		searchButton.setOnAction(x -> {
			searchEmployee(empnoFi.getText().trim());
		});
		updateButton = new CustomButton("Update");
		updateButton.setDisable(true);
		deleteButton = new CustomButton("Delete Employee");
		deleteButton.setDisable(true);
		CustomButton backButton = new CustomButton("Back");
		backButton.setOnAction(x -> {
			stage.close();
		});

		HBox hbS = new HBox(15, empNoL, empnoFi, searchButton, updateButton, deleteButton, backButton);
		hbS.setAlignment(Pos.CENTER);
		hbS.setPadding(new Insets(10));
		TableColumn<Employee, String> firstNameCol = new TableColumn<>("First Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

		TableColumn<Employee, String> lastNameCol = new TableColumn<>("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

		TableColumn<Employee, String> emailCol = new TableColumn<>("Email");
		emailCol.setMinWidth(100);
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

		TableColumn<Employee, String> desgiCol = new TableColumn<>("Designation");
		desgiCol.setCellValueFactory(new PropertyValueFactory<>("designation"));

		TableColumn<Employee, String> dateCol = new TableColumn<>("Date of Birth");
		dateCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

		TableColumn<Employee, String> eduCol = new TableColumn<>("Education");
		eduCol.setCellValueFactory(new PropertyValueFactory<>("education"));

		TableColumn<Employee, String> phoneCol = new TableColumn<>("Phone No");
		phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));

		TableColumn<Employee, String> addressCol = new TableColumn<>("Address (Street/City/Country)");
		addressCol.setMinWidth(150);
		addressCol.setCellValueFactory(x -> {
			Address address = x.getValue().getAdress();
			if (address != null) {
				return new SimpleStringProperty(
						address.getStreet() + ", " + address.getCity() + ", " + address.getCountry());
			} else {

				return new SimpleStringProperty("N/A");
			}
		});
		TableColumn<Employee, String> empTCol = new TableColumn<>("Employee Type");
		empTCol.setCellValueFactory(new PropertyValueFactory<>("employeeType"));

		TableColumn<Employee, String> salaCol = new TableColumn<>("Salary");
		salaCol.setCellValueFactory(y -> {
			double salary = y.getValue().Payment();
			return new SimpleStringProperty(String.format("%.2f", salary));
		});

		tableView.getColumns().add(firstNameCol);
		tableView.getColumns().add(lastNameCol);
		tableView.getColumns().add(emailCol);
		tableView.getColumns().add(desgiCol);
		tableView.getColumns().add(dateCol);
		tableView.getColumns().add(eduCol);
		tableView.getColumns().add(phoneCol);
		tableView.getColumns().add(addressCol);
		tableView.getColumns().add(empTCol);
		tableView.getColumns().add(salaCol);
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
		tableView.setItems(FXCollections.observableArrayList(EmployeeData.employeeList));

		BorderPane bp = new BorderPane();
		bp.setTop(menuBar);
		bp.setCenter(tableView);
		bp.setBottom(hbS);
		Scene scene = new Scene(bp, 1000, 800);
		Main.applyStyles(scene);
		stage.getIcons().add(Main.loadImage("/application/images/app-icon.png"));
		stage.setMaximized(true);
		stage.setScene(scene);
		stage.showAndWait();

	}

	public void searchEmployee(String empNo) {
		if (empNo == null || empNo.isEmpty()) {
			alert.ErrorAlert("Error", "Please enter an Employee Number to Search!");
			return;
		}
		ObservableList<Employee> employeeList = FXCollections.observableArrayList();
		Employee foundEmployee = null;
		for (Employee employee : EmployeeData.employeeList) {
			if (employee.getEmpNo().equals(empNo.trim())) {
				employeeList.add(employee);
				foundEmployee = employee;
				break;
			}
		}
		if (employeeList.isEmpty()) {
			alert.ErrorAlert("Error", "No Employee found with this Employee Number. ");
		} else {
			tableView.setItems(employeeList);
			tableView.getSelectionModel().select(foundEmployee);
			enableButtons();
		}
	}

	public void enableButtons() {
		updateButton.setDisable(false);
		updateButton.setOnAction(x -> {
			Employee selectedEmployee = tableView.getSelectionModel().getSelectedItem();
			if (selectedEmployee == null) {
				alert.ErrorAlert("Error", "Please Select an Employee to Update");
				return;
			}
			UpdateButton update = new UpdateButton(selectedEmployee);
			update.Display();

			tableView.setItems(FXCollections.observableArrayList(EmployeeData.employeeList));
		});

		deleteButton.setDisable(false);
		deleteButton.setOnAction(x -> {
			Employee selectedEmployee = tableView.getSelectionModel().getSelectedItem();
			if (selectedEmployee == null) {
				alert.ErrorAlert("Error", "Please Select an Employee to Delete");
				return;
			}
			DeleteButton delete = new DeleteButton(selectedEmployee);
			delete.Display();

			tableView.setItems(FXCollections.observableArrayList(EmployeeData.employeeList));
		});
	}
}
