package application;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StatiscalReport {

	private ObservableList<Employee> employeeData;

	public StatiscalReport(ObservableList<Employee> employeeData) {
		this.employeeData = employeeData;
	}

	public List<Employee> getHighestPaidEmployeesType() {

		HourlyEmployee highestHourly = null;
		double highestHourlyPay = 0;

		SalariedEmployee highestSalaried = null;
		double highestSalariedPay = 0;

		CommessionEmployee highestCommission = null;
		double highestCommissionPay = 0;

		EmployeeBasedComession highestCommissionBased = null;
		double highestCommissionBasedPay = 0;

		for (Employee emp : employeeData) {
			double salary = emp.Payment();

			if (emp instanceof EmployeeBasedComession) {
				if (highestCommissionBased == null || salary > highestCommissionBasedPay) {
					highestCommissionBased = (EmployeeBasedComession) emp;
					highestCommissionBasedPay = salary;
				}
			} else if (emp instanceof HourlyEmployee) {
				if (highestHourly == null || salary > highestHourlyPay) {
					highestHourly = (HourlyEmployee) emp;
					highestHourlyPay = salary;
				}
			} else if (emp instanceof SalariedEmployee) {
				if (highestSalaried == null || salary > highestSalariedPay) {
					highestSalaried = (SalariedEmployee) emp;
					highestSalariedPay = salary;
				}
			} else if (emp instanceof CommessionEmployee) {
				if (highestCommission == null || salary > highestCommissionPay) {
					highestCommission = (CommessionEmployee) emp;
					highestCommissionPay = salary;
				}
			}
		}

		List<Employee> result = new ArrayList<>();
		if (highestHourly != null) {
			result.add(highestHourly);
		}
		if (highestSalaried != null) {
			result.add(highestSalaried);
		}
		if (highestCommission != null) {
			result.add(highestCommission);
		}
		if (highestCommissionBased != null) {
			result.add(highestCommissionBased);
		}

		return result;
	}

	public double calculateTotalSalary() {
		double total = 0.0;
		for (Employee emp : employeeData) {
			total += emp.Payment();
		}
		return total;
	}

	public List<Employee> sortEmployees(String sortBy) {
		List<Employee> sortedList = new ArrayList<>(employeeData);

		switch (sortBy) {
		case "firstName":
			sortedList.sort(Comparator.comparing(Employee::getFirstName, String.CASE_INSENSITIVE_ORDER));
			break;

		case "lastName":
			sortedList.sort(Comparator.comparing(Employee::getLastName, String.CASE_INSENSITIVE_ORDER));
			break;

		case "education":
			sortedList.sort(Comparator.comparing(Employee::getEducation, String.CASE_INSENSITIVE_ORDER));
			break;

		case "salary":
			sortedList.sort(Comparator.comparingDouble(Employee::Payment));
			break;

		default:
			break;
		}
		return sortedList;
	}

	public void displayAlert(String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Statistical Report");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

}
