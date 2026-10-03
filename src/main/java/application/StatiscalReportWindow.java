package application;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StatiscalReportWindow {

	private VBox layout;
	private TableView<Employee> reportTable;
	Stage stage = new Stage();

	public StatiscalReportWindow(ObservableList<Employee> employeesList) {
		reportTable = new TableView<>();

		TableColumn<Employee, String> firstNameColumn = new TableColumn<>("First Name");
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

		TableColumn<Employee, String> lastNameColumn = new TableColumn<>("Last Name");
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

		TableColumn<Employee, String> educationColumn = new TableColumn<>("Education");
		educationColumn.setCellValueFactory(new PropertyValueFactory<>("education"));

		TableColumn<Employee, Double> salaryColumn = new TableColumn<>("Salary");
		salaryColumn.setCellValueFactory(
				cell -> new SimpleDoubleProperty(cell.getValue().Payment()).asObject());

		reportTable.getColumns().add(firstNameColumn);
		reportTable.getColumns().add(lastNameColumn);
		reportTable.getColumns().add(educationColumn);
		reportTable.getColumns().add(salaryColumn);
		reportTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
		reportTable.setPrefHeight(500);
		reportTable.setItems(employeesList);

		MenuBar reportMenuBar = new MenuBar();
		Menu optionsMenu = new Menu("Options");

		MenuItem maxSalaryReport = new MenuItem("Generate Max Salary Report");
		maxSalaryReport.setOnAction(ev -> {
			StatiscalReport report = new StatiscalReport(employeesList);
			List<Employee> highestPaidEmployees = report.getHighestPaidEmployeesType();
			reportTable.getItems().setAll(highestPaidEmployees);
			report.displayAlert("Report Generated: Highest Paid Employees Per Type");
		});

		MenuItem totalSalaryReport = new MenuItem("Calculate Total Salary");
		totalSalaryReport.setOnAction(ev -> {
			StatiscalReport report = new StatiscalReport(employeesList);
			double totalSalary = report.calculateTotalSalary();
			report.displayAlert(String.format("Total salary for all employees: %.2f", totalSalary));
		});

		Menu sortMenu = new Menu("Sort Employees by...");
		MenuItem sortByFirstName = new MenuItem("First Name");
		sortByFirstName.setOnAction(ev -> {
			StatiscalReport report = new StatiscalReport(employeesList);
			List<Employee> sortedEmployees = report.sortEmployees("firstName");
			reportTable.getItems().setAll(sortedEmployees);
			report.displayAlert("Employees Sorted by First Name");
		});

		MenuItem sortByLastName = new MenuItem("Last Name");
		sortByLastName.setOnAction(ev -> {
			StatiscalReport report = new StatiscalReport(employeesList);
			List<Employee> sortedEmployees = report.sortEmployees("lastName");
			reportTable.getItems().setAll(sortedEmployees);
			report.displayAlert("Employees Sorted by Last Name");
		});

		MenuItem sortByEducation = new MenuItem("Education");
		sortByEducation.setOnAction(ev -> {
			StatiscalReport report = new StatiscalReport(employeesList);
			List<Employee> sortedEmployees = report.sortEmployees("education");
			reportTable.getItems().setAll(sortedEmployees);
			report.displayAlert("Employees Sorted by Education");
		});

		MenuItem sortBySalary = new MenuItem("Salary");
		sortBySalary.setOnAction(ev -> {
			StatiscalReport report = new StatiscalReport(employeesList);
			List<Employee> sortedEmployees = report.sortEmployees("salary");
			reportTable.getItems().setAll(sortedEmployees);
			report.displayAlert("Employees Sorted by Salary");
		});

		sortMenu.getItems().addAll(sortByFirstName, sortByLastName, sortByEducation, sortBySalary);
		optionsMenu.getItems().addAll(maxSalaryReport, totalSalaryReport, sortMenu);

		reportMenuBar.getMenus().add(optionsMenu);

		layout = new VBox();
		layout.setSpacing(10);
		layout.setPadding(new Insets(10));

		HBox buttonContainer = new HBox();
		buttonContainer.setSpacing(10);
		buttonContainer.setAlignment(Pos.CENTER);

		CustomButton backButton = new CustomButton("Back");
		backButton.setOnAction(e -> stage.close());

		buttonContainer.getChildren().addAll(backButton);

		layout.getChildren().addAll(reportMenuBar, reportTable, buttonContainer);
	}

	public void show() {
		Scene scene = new Scene(layout, 800, 600);
		Main.applyStyles(scene);
		stage.setTitle("Statistical Report");
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.getIcons().add(Main.loadImage("/application/images/app-icon.png"));
		stage.show();
	}

	public VBox getReportLayout() {
		return layout;
	}
}
