package pe.edu.upeu.sistema_biblioteca.repositorio;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import pe.edu.upeu.sistema_biblioteca.modelo.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {

    public List<Cliente> clientes = new ArrayList<>();

    public  List<Cliente> findAll(){

        clientes.add(new Cliente(
            new SimpleStringProperty("612300"),

            new SimpleStringProperty("apaza"),

            new SimpleStringProperty("mario"),

            new SimpleStringProperty("urb.flores"),

            new SimpleStringProperty("9123124123")));

        return clientes;
    }


}
