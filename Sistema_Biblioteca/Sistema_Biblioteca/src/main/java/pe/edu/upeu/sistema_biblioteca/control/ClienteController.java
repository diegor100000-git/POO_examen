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
import pe.edu.upeu.sistema_biblioteca.dto.PersonaDto;
import pe.edu.upeu.sistema_biblioteca.enums.TipoCliente;
import pe.edu.upeu.sistema_biblioteca.modelo.Cliente;
import pe.edu.upeu.sistema_biblioteca.servicio.ClienteServicioI;

@Controller
public class ClienteController {

    @FXML
    TextField txtDni,txtApellidos,txtNombres,txtDireccion,txtTelefono;
    int indexE=-1;

    @FXML
    private ComboBox<TipoCliente>cbxTipoCliente;

    @FXML
    TableView<Cliente> tableView;
    ObservableList<Cliente> clientes;
    TableColumn<Cliente, String> dniCol,apellidoCol,nombreCol,direccionCol,telefonoCol,tipoClienteCol;
    TableColumn<Cliente, Void> opcionCol;

    @Autowired
    ClienteServicioI ps;

    @FXML
    public void initialize(){
        cbxTipoCliente.getItems().setAll(TipoCliente.values());
        definirNombresColumnas();
        listarClientes();
    }
    public void definirNombresColumnas(){
        dniCol = new TableColumn("DNI");
        apellidoCol = new TableColumn("Apellidos");
        apellidoCol.setMinWidth(180);
        nombreCol = new TableColumn("Nombres");
        nombreCol.setMinWidth(180);
        direccionCol = new TableColumn("Direccion");
        direccionCol.setMinWidth(180);
        telefonoCol = new TableColumn("Telefono");
        tipoClienteCol=new TableColumn<>("Tipo Cliente");
        opcionCol = new TableColumn<>("opciones");
        opcionCol.setMinWidth(160);
        tableView.getColumns().addAll(dniCol, nombreCol, apellidoCol,direccionCol,telefonoCol,tipoClienteCol,opcionCol);
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
        tipoClienteCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipoCliente().toString()));


        agregarAccionBotones();
        clientes=FXCollections.observableArrayList(ps.findAll());
        tableView.setItems(clientes);
    }
    @FXML
    public void buscarPorDni(){
        PersonaDto personaDto = ps.findByDni(txtDni.getText());
        if(personaDto!=null){
            txtNombres.setText(personaDto.getNombre());
            txtApellidos.setText(personaDto.getApellidoPaterno()+" "+personaDto.getApellidoMaterno());
        }else{
            txtApellidos.setText("");
            txtNombres.setText("");
        }
    }

    @FXML
    public void crearCliente(){
        Cliente cliente = new Cliente();
        cliente.setDni(new SimpleStringProperty(txtDni.getText()));
        cliente.setApellido(new SimpleStringProperty(txtApellidos.getText()));
        cliente.setNombre(new SimpleStringProperty(txtNombres.getText()));
        cliente.setDireccion(new SimpleStringProperty(txtDireccion.getText()));
        cliente.setTelefono(new SimpleStringProperty(txtTelefono.getText()));
        cliente.setTipoCliente(cbxTipoCliente.getValue());
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
        cbxTipoCliente.getSelectionModel().clearSelection();
    }
    public void editarCliente(Cliente c, int index){
        txtDni.setText(c.getDni().getValue());
        txtApellidos.setText(c.getApellido().getValue());
        txtNombres.setText(c.getNombre().getValue());
        txtDireccion.setText(c.getDireccion().getValue());
        txtTelefono.setText(c.getTelefono().getValue());
        cbxTipoCliente.getSelectionModel().select(c.getTipoCliente());

        indexE=index;
    }
    public void eliminarCliente(int index){
        ps.delete(index);
        listarClientes();
    }

    public void cancelar(){
        txtDni.setText("");
        txtApellidos.setText("");
        txtNombres.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        cbxTipoCliente.getSelectionModel().clearSelection();
    }

}
