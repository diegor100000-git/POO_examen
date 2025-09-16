package pe.edu.upeu.sistema_biblioteca.repositorio;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import pe.edu.upeu.sistema_biblioteca.dto.PersonaDto;
import pe.edu.upeu.sistema_biblioteca.enums.TipoCliente;
import pe.edu.upeu.sistema_biblioteca.modelo.Cliente;
import pe.edu.upeu.sistema_biblioteca.utils.ConsultaDNI;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepository extends ConsultaDNI{

    public List<Cliente> clientes = new ArrayList<>();

    public  List<Cliente> findAll(){

        clientes.add(new Cliente(
            new SimpleStringProperty("612300"),

            new SimpleStringProperty("apaza"),

            new SimpleStringProperty("mario"),

            new SimpleStringProperty("urb.flores"),

            new SimpleStringProperty("9123124123"),

            TipoCliente.EXTERNO,

            new SimpleBooleanProperty(true)));

        return clientes;
    }
    public PersonaDto findByDni(String dni){
        return consultarDNI (dni);
    }

}
