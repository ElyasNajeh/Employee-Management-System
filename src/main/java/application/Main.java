package application;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;

import javafx.event.ActionEvent;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) {
		try {
			EmployeeData.ensureDataDirectories();
		} catch (IOException exception) {
			new AlertTypes().ErrorAlert("Data directory error",
					"The application data directory could not be prepared: " + exception.getMessage());
		}
		BorderPane bp1 = new BorderPane();

		MenuBar menuBar = creatmenuBar(stage);
		bp1.setTop(menuBar);

		VBox vb = createTabs();
		bp1.setCenter(vb);

		HBox hbBu = buttonWithIcon();
		bp1.setBottom(hbBu);

		Scene scene = new Scene(bp1, 800, 600);
		applyStyles(scene);
		stage.getIcons().add(loadImage("/application/images/app-icon.png"));
		stage.setTitle("Employee Management System");
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.show();

	}

	public static MenuBar creatmenuBar(Stage stage) {
		MenuBar menuBar = new MenuBar();
		Menu mEmployee = new Menu("Employee");
		mEmployee.getStyleClass().add("main-menu");
		MenuItem addEmployee = new MenuItem("Add Employee");
		addEmployee.setAccelerator(KeyCombination.keyCombination("Ctrl+A"));
		addEmployee.setOnAction(e -> {
			AddEmployee addEmployeeW = new AddEmployee();
			addEmployeeW.Display();
		});
		MenuItem viewEmployee = new MenuItem("View Employee");
		viewEmployee.setAccelerator(KeyCombination.keyCombination("Ctrl+V"));
		viewEmployee.setOnAction(e -> {
			ViewEmployee viewEmployeeW = new ViewEmployee();
			viewEmployeeW.Display();
		});
		MenuItem readFile = new MenuItem("Read File");
		readFile.setAccelerator(KeyCombination.keyCombination("Ctrl+R"));
		readFile.setOnAction(e -> {
			ReadFileHandler readWindow = new ReadFileHandler();
			readWindow.handle(new ActionEvent());
		});
		MenuItem saveFile = new MenuItem("Save File");
		saveFile.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
		saveFile.setOnAction(e -> {
			SaveFileHandler saveHandler = new SaveFileHandler(EmployeeData.employeeList);
			saveHandler.handle(new ActionEvent());
		});
		mEmployee.getItems().addAll(addEmployee, viewEmployee, readFile, saveFile);

		Menu mReport = new Menu("Statistical Report");
		mReport.getStyleClass().add("main-menu");

		MenuItem openReportWindow = new MenuItem("Open Statistical Report");
		openReportWindow.setOnAction(e -> {
			ObservableList<Employee> employeesList = FXCollections.observableArrayList(EmployeeData.employeeList);
			StatiscalReportWindow reportWindow = new StatiscalReportWindow(employeesList);
			reportWindow.show();
		});

		mReport.getItems().add(openReportWindow);

		Menu mClose = new Menu("Close");
		mClose.getStyleClass().add("main-menu");
		MenuItem exitApp = new MenuItem("Exit");
		exitApp.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
		exitApp.setOnAction(e -> {
			stage.close();
		});
		mClose.getItems().add(exitApp);

		Menu empty1 = new Menu("|");
		Menu empty2 = new Menu("|");
		empty1.setStyle("-fx-font-size: 18px; -fx-text-fill: black;");
		empty2.setStyle("-fx-font-size: 18px; -fx-text-fill: black;");

		menuBar.getMenus().addAll(mEmployee, empty1, mReport, empty2, mClose);
		menuBar.getStyleClass().add("main-menu-bar");
		return menuBar;
	}

	public VBox createTabs() {
		TabPane tabPane = new TabPane();
		Tab tRecord = new Tab("Employee Record");
		tRecord.setStyle(
				"-fx-background-color: #C6E2FF; -fx-text-fill: #101000 ; -fx-font-size: 15px; -fx-padding: 10px;");

		boolean[] isInitialLoad = { true };

		tRecord.setOnSelectionChanged(event -> {
			if (tRecord.isSelected() && !isInitialLoad[0]) {
				AddEmployee addEmployeeWindow = new AddEmployee();
				addEmployeeWindow.Display();
			}
			isInitialLoad[0] = false;
		});

		Tab tReport = new Tab("Statistical Report");
		tReport.setStyle(
				"-fx-background-color: #C6E2FF; -fx-text-fill: #101010 ; -fx-font-size: 15px; -fx-padding: 10px;");

		tReport.setOnSelectionChanged(e -> {
			if (tReport.isSelected()) {
				ObservableList<Employee> employeesList = FXCollections.observableArrayList(EmployeeData.employeeList);
				StatiscalReportWindow reportWindow = new StatiscalReportWindow(employeesList);
				reportWindow.show();
			}
		});

		tabPane.getTabs().addAll(tRecord, tReport);
		Image image = loadImage("/application/images/employee-system.png");
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(800);
		imageView.setFitHeight(400);
		imageView.setPreserveRatio(true);

		VBox imageContainer = new VBox(imageView);
		imageContainer.setAlignment(Pos.TOP_CENTER);
		imageContainer.setPadding(new Insets(100, 0, 0, 0));
		VBox vb = new VBox();
		vb.getChildren().addAll(tabPane, imageContainer);
		vb.setAlignment(Pos.CENTER);
		return vb;
	}

	public HBox buttonWithIcon() {
		IconButton icAdd = new IconButton("Add", "/application/images/add-employee.png");
		icAdd.setOnAction(x -> {
			AddEmployee addEmployee = new AddEmployee();
			addEmployee.Display();

		});
		IconButton icView = new IconButton("View", "/application/images/view-employee.png");
		icView.setOnAction(y -> {
			ViewEmployee viewEmployee = new ViewEmployee();
			viewEmployee.Display();
		});
		IconButton icRead = new IconButton("Read File", "/application/images/open-file.png");
		icRead.setOnAction(new ReadFileHandler());

		IconButton icSave = new IconButton("Save", "/application/images/save.png");
		icSave.setOnAction(new SaveFileHandler(EmployeeData.employeeList));

		icAdd.setPrefSize(200, 60);
		icView.setPrefSize(200, 60);
		icRead.setPrefSize(200, 60);
		icSave.setPrefSize(200, 60);

		HBox hbBu = new HBox(70);
		hbBu.getChildren().addAll(icAdd, icView, icRead, icSave);
		hbBu.setAlignment(Pos.CENTER);
		hbBu.setPadding(new Insets(30));
		return hbBu;
	}

	public static Image loadImage(String resourcePath) {
		URL resource = Main.class.getResource(resourcePath);
		if (resource == null) {
			throw new IllegalArgumentException("Missing resource: " + resourcePath);
		}
		return new Image(resource.toExternalForm());
	}

	public static void applyStyles(Scene scene) {
		URL stylesheet = Main.class.getResource("/application/styles/application.css");
		if (stylesheet != null) {
			scene.getStylesheets().add(stylesheet.toExternalForm());
		}
	}
}
