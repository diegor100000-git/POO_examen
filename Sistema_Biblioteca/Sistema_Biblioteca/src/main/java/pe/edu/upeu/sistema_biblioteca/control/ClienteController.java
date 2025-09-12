package pe.edu.upeu.sistema_biblioteca.control;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pe.edu.upeu.sistema_biblioteca.modelo.Cliente;
import pe.edu.upeu.sistema_biblioteca.servicio.ClienteServicioI;

@Controller
public class ClienteController {

    @FXML
    TextField txtDni,txtApellidos,txtNombres,txtDireccion,txtTelefono;
    int indexE=-1;

    @FXML
    TableView<Cliente> tableView;
    ObservableList<Cliente> clientes;
    TableColumn<Cliente, String> dniCol,apellidoCol,nombreCol,direccionCol,telefonoCol;
    TableColumn<Cliente, Void> opcionCol;

    @Autowired
    ClienteServicioI ps;

    @FXML
    public void initialize(){
        definirNombresColumnas();
        listarClientes();
    }
    public void definirNombresColumnas(){
        dniCol = new TableColumn("DNI");
        apellidoCol = new TableColumn("Apellidos");
        nombreCol = new TableColumn("Nombres");
        direccionCol = new TableColumn("Direccion");
        telefonoCol = new TableColumn("Telefono");
        opcionCol = new TableColumn<>("opciones");
        tableView.getColumns().addAll(dniCol, nombreCol, apellidoCol,direccionCol,telefonoCol,opcionCol);
    }
    public void agregarAccionBotones(){
        Callback<TableColumn<Cliente, Void>, TableCell<Cliente,Void >> cellFactory = param-> new TableCell<>() {
            Button btnEditar = new Button("Editar");
            Button btnEliminar = new Button("Eliminar");
            {
                btnEditar.setOnAction((event) -> {
                    Cliente cliente = getTableView().getItems().get(getIndex());
                    editarCliente(cliente, getIndex());
                });
                btnEliminar.setOnAction((event) -> {
                    eliminarCliente(getIndex());
                });
            }
            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                }else{
                    HBox hBox = new HBox(btnEditar,btnEliminar);
                    hBox.setSpacing(10);
                    setGraphic(hBox);
                }
            }
        };
        opcionCol.setCellFactory(cellFactory);
    }
    public void listarClientes(){
        dniCol.setCellValueFactory(cellData -> cellData.getValue().getDni());
        apellidoCol.setCellValueFactory(cellData -> cellData.getValue().getApellido());
        nombreCol.setCellValueFactory(cellData -> cellData.getValue().getNombre());
        direccionCol.setCellValueFactory(cellData -> cellData.getValue().getDireccion());
        telefonoCol.setCellValueFactory(cellData -> cellData.getValue().getTelefono());


        agregarAccionBotones();
        clientes=FXCollections.observableArrayList(ps.findAll());
        tableView.setItems(clientes);
    }
    @FXML
    public void crearCliente(){
        Cliente cliente = new Cliente();
        cliente.setDni(new SimpleStringProperty(txtDni.getText()));
        cliente.setApellido(new SimpleStringProperty(txtApellidos.getText()));
        cliente.setNombre(new SimpleStringProperty(txtNombres.getText()));
        cliente.setDireccion(new SimpleStringProperty(txtDireccion.getText()));
        cliente.setTelefono(new SimpleStringProperty(txtTelefono.getText()));
        if(indexE==-1){
            ps.save(cliente);
        }else{
            ps.update(cliente, indexE);
            indexE=-1;
        }
        limpiarFormulario();
        listarClientes();
    }
    public void limpiarFormulario(){
        txtDni.setText("");
        txtApellidos.setText("");
        txtNombres.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
    }
    public void editarCliente(Cliente c, int index){
        txtDni.setText(c.getDni().getValue());
        txtApellidos.setText(c.getApellido().getValue());
        txtNombres.setText(c.getNombre().getValue());
        txtDireccion.setText(c.getDireccion().getValue());
        txtTelefono.setText(c.getTelefono().getValue());

        indexE=index;
    }
    public void eliminarCliente(int index){
        ps.delete(index);
        listarClientes();
    }

}
