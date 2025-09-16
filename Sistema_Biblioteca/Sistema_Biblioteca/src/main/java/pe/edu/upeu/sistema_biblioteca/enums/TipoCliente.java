package pe.edu.upeu.sistema_biblioteca.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum TipoCliente {

    ESTUDIANTE("Estudiante"),
    DOCENTE("Docente"),
    EXTERNO("Externo");

    private String descripcion;
}
