package pe.edu.upeu.sistema_biblioteca.modelo;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upeu.sistema_biblioteca.enums.TipoCliente;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cliente {

    private StringProperty dni;
    private StringProperty apellido;
    private StringProperty nombre;
    private StringProperty direccion;
    private StringProperty telefono;
    private TipoCliente tipoCliente;
    private BooleanProperty estado;



}
