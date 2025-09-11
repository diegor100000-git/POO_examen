package pe.edu.upeu.sistema_biblioteca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SistemaBibliotecaApplication extends Application{
	private ConfigurableApplicationContext configurableApplicationContext;
	private Parent parent;

	public static void main(String[] args) {
		//SpringApplication.run(SistemaBibliotecaApplication.class, args);
        launch(args);
	}
	@Override
	public void init() throws Exception {
		//configurableApplicationContext=SpringApplication.run(SysPooApplication.class);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(SistemaBibliotecaApplication.class);
		builder.application().setWebApplicationType(WebApplicationType.NONE);
		configurableApplicationContext = builder.run(getParameters().getRaw().toArray(new String[0]));

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main_cliente.fxml"));
		fxmlLoader.setControllerFactory(configurableApplicationContext::getBean);
		parent= fxmlLoader.load();
	}
	@Override
	public void start(Stage stage) throws Exception {
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getBounds();
		stage.setScene(new Scene(parent,bounds.getWidth(), bounds.getHeight()- 80));
		stage.show();
		stage.setTitle("Spring Java-FX");
		stage.show();
	}





}
