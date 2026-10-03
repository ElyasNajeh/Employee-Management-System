package application;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SaveFileHandler implements EventHandler<ActionEvent> {
	private final ArrayList<Employee> employeeList;
	private final AlertTypes alert;

	public SaveFileHandler(ArrayList<Employee> employeeList) {
		this.employeeList = employeeList;
		this.alert = new AlertTypes();
	}

	@Override
	public void handle(ActionEvent event) {
		try {
			saveEmployees(EmployeeData.getEmployeesFile());
			alert.InfoAlert("Success", "Employee data saved successfully.");
		} catch (IOException exception) {
			alert.ErrorAlert("Error", "Failed to save employee data: " + exception.getMessage());
		}
	}

	public void saveEmployees(Path file) throws IOException {
		Path parent = file.toAbsolutePath().getParent();
		if (parent != null) {
			Files.createDirectories(parent);
		}
		Path temporaryFile = Files.createTempFile(parent, "employees-", ".tmp");
		try {
			try (BufferedWriter writer = Files.newBufferedWriter(temporaryFile, StandardCharsets.UTF_8)) {
				for (Employee employee : employeeList) {
					writer.write(buildEmployeeData(employee));
					writer.newLine();
				}
			}
			try {
				Files.move(temporaryFile, file, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
			} catch (AtomicMoveNotSupportedException exception) {
				Files.move(temporaryFile, file, StandardCopyOption.REPLACE_EXISTING);
			}
		} finally {
			Files.deleteIfExists(temporaryFile);
		}
	}

	String buildEmployeeData(Employee employee) {
		StringBuilder data = new StringBuilder();
		data.append(employee.getEmpNo()).append(',');
		data.append(employee.getFirstName()).append(',');
		data.append(employee.getLastName()).append(',');
		data.append(employee.getDateOfBirth()).append(',');
		data.append(employee.getDesignation()).append(',');
		data.append(employee.getPhoneNum()).append(',');
		data.append(employee.getEmail()).append(',');
		data.append(employee.getNationality()).append(',');
		data.append(employee.getAdress().getStreet()).append(',');
		data.append(employee.getAdress().getCity()).append(',');
		data.append(employee.getAdress().getCountry()).append(',');
		data.append(employee.getEducation()).append(',');
		data.append(employee.getEmployeeType()).append(',');
		data.append(employee.getPhotoPath() == null ? "" : employee.getPhotoPath());

		if (employee instanceof EmployeeBasedComession commissionBased) {
			data.append(',').append(commissionBased.getSoldItems());
			data.append(',').append(commissionBased.getBasicSalary());
		} else if (employee instanceof HourlyEmployee hourly) {
			data.append(',').append(hourly.getHours());
			data.append(',').append(hourly.getRate());
		} else if (employee instanceof SalariedEmployee salaried) {
			data.append(',').append(salaried.getAnnualSalary());
		} else if (employee instanceof CommessionEmployee commission) {
			data.append(',').append(commission.getSoldItems());
		}
		return data.toString();
	}
}
