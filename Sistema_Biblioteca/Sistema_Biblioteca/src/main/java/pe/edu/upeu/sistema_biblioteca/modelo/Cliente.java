package pe.edu.upeu.sistema_biblioteca.modelo;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cliente {

    private StringProperty dni;
    private StringProperty apellido;
    private StringProperty nombre;
    private StringProperty direccion;
    private StringProperty telefono;
    private BooleanProperty estado;



}
