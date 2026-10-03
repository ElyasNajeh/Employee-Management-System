package application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class EmployeeData {
	public static final ArrayList<Employee> employeeList = new ArrayList<>();

	private static final Path DATA_DIRECTORY = Paths
			.get(System.getProperty("employee.management.dataDir", "data"))
			.toAbsolutePath().normalize();
	private static final Path PHOTOS_DIRECTORY = DATA_DIRECTORY.resolve("photos");
	private static final Path EMPLOYEES_FILE = DATA_DIRECTORY.resolve("employees.csv");

	private EmployeeData() {
	}

	public static Path getDataDirectory() {
		return DATA_DIRECTORY;
	}

	public static Path getPhotosDirectory() {
		return PHOTOS_DIRECTORY;
	}

	public static Path getEmployeesFile() {
		return EMPLOYEES_FILE;
	}

	public static void ensureDataDirectories() throws IOException {
		Files.createDirectories(PHOTOS_DIRECTORY);
	}

	public static Path resolvePhoto(String photoName) {
		if (photoName == null || photoName.isBlank()) {
			return null;
		}
		String safeName = Paths.get(photoName).getFileName().toString();
		return PHOTOS_DIRECTORY.resolve(safeName).normalize();
	}

	public static String copyPhoto(Path source, String employeeNumber) throws IOException {
		ensureDataDirectories();
		String originalName = source.getFileName().toString();
		String safeName = employeeNumber + "_" + originalName.replaceAll("[^a-zA-Z0-9._-]", "_");
		Path destination = resolvePhoto(safeName);
		if (source.toAbsolutePath().normalize().equals(destination)) {
			return safeName;
		}
		Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
		return safeName;
	}
}
