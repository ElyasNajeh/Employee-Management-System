package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ReadFileHandler implements EventHandler<ActionEvent> {
	private final AlertTypes alert = new AlertTypes();

	@Override
	public void handle(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select Employees File");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV and text files", "*.csv", "*.txt"));

		Path dataDirectory = EmployeeData.getDataDirectory();
		if (Files.isDirectory(dataDirectory)) {
			fileChooser.setInitialDirectory(dataDirectory.toFile());
		}
		Path defaultFile = EmployeeData.getEmployeesFile();
		if (Files.isRegularFile(defaultFile)) {
			fileChooser.setInitialFileName(defaultFile.getFileName().toString());
		}

		File selectedFile = fileChooser.showOpenDialog(new Stage());
		if (selectedFile == null) {
			return;
		}

		try {
			int loaded = readEmployees(selectedFile.toPath());
			alert.InfoAlert("Success", loaded + " employee record(s) loaded successfully.");
		} catch (IOException | IllegalArgumentException exception) {
			alert.ErrorAlert("Error", "Could not read the employee file: " + exception.getMessage());
		}
	}

	public int readEmployees(Path file) throws IOException {
		List<Employee> loadedEmployees = new ArrayList<>();
		int maximumEmployeeNumber = 999;

		try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
			String line;
			int lineNumber = 0;
			while ((line = reader.readLine()) != null) {
				lineNumber++;
				if (line.isBlank()) {
					continue;
				}
				try {
					Employee employee = parseEmployee(line);
					loadedEmployees.add(employee);
					try {
						maximumEmployeeNumber = Math.max(maximumEmployeeNumber,
								Integer.parseInt(employee.getEmpNo()));
					} catch (NumberFormatException ignored) {
						// Legacy non-numeric IDs remain valid but do not affect the next generated ID.
					}
				} catch (RuntimeException exception) {
					throw new IllegalArgumentException("invalid data on line " + lineNumber, exception);
				}
			}
		}

		EmployeeData.employeeList.clear();
		EmployeeData.employeeList.addAll(loadedEmployees);
		Employee.setCount(maximumEmployeeNumber + 1);
		return loadedEmployees.size();
	}

	private Employee parseEmployee(String line) {
		String[] data = line.split(",", -1);
		if (data.length < 14) {
			throw new IllegalArgumentException("expected at least 14 comma-separated fields");
		}

		Employee employee;
		switch (data[12].trim().toLowerCase()) {
		case "hourlyemployee":
		case "hourly employee":
			HourlyEmployee hourly = new HourlyEmployee();
			hourly.setHours(data.length > 14 && !data[14].isBlank() ? Short.parseShort(data[14].trim()) : (short) 1);
			hourly.setRate(data.length > 15 && !data[15].isBlank() ? Float.parseFloat(data[15].trim()) : 2.5f);
			employee = hourly;
			break;
		case "salariedemployee":
		case "salaried employee":
			SalariedEmployee salaried = new SalariedEmployee();
			salaried.setAnnualSalary(
					data.length > 14 && !data[14].isBlank() ? Double.parseDouble(data[14].trim()) : 4075);
			employee = salaried;
			break;
		case "commessionemployee":
		case "commession employee":
			CommessionEmployee commission = new CommessionEmployee();
			commission.setSoldItems(
					data.length > 14 && !data[14].isBlank() ? Double.parseDouble(data[14].trim()) : 0);
			employee = commission;
			break;
		case "employeebasedcomession":
		case "commession based employee":
			EmployeeBasedComession commissionBased = new EmployeeBasedComession();
			commissionBased.setSoldItems(
					data.length > 14 && !data[14].isBlank() ? Double.parseDouble(data[14].trim()) : 0);
			commissionBased.setBasicSalary(
					data.length > 15 && !data[15].isBlank() ? Double.parseDouble(data[15].trim()) : 0);
			employee = commissionBased;
			break;
		default:
			throw new IllegalArgumentException("unknown employee type: " + data[12]);
		}

		employee.setEmpNo(data[0].trim());
		employee.setFirstName(data[1].trim());
		employee.setLastName(data[2].trim());
		employee.setDateOfBirth(data[3].trim());
		employee.setDesignation(data[4].trim());
		employee.setPhoneNum(data[5].trim());
		employee.setEmail(data[6].trim());
		employee.setNationality(data[7].trim());
		employee.setAddress(new Address(data[8].trim(), data[9].trim(), data[10].trim()));
		employee.setEducation(normalizeEducation(data[11]));

		String rawPhotoName = data[13].trim();
		String photoName = rawPhotoName.isEmpty() ? "" : Path.of(rawPhotoName).getFileName().toString();
		employee.setPhotoPath(photoName);
		return employee;
	}

	private String normalizeEducation(String value) {
		switch (value.trim().toLowerCase()) {
		case "phd":
			return "PhD";
		case "master":
			return "Master";
		case "b.a":
			return "B.A";
		case "secondary school":
			return "Secondary School";
		case "primary school":
			return "Primary School";
		default:
			throw new IllegalArgumentException("unknown education type: " + value);
		}
	}
}
