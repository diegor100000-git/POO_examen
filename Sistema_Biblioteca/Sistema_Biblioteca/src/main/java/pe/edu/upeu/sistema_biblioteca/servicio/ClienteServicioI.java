package pe.edu.upeu.sistema_biblioteca.servicio;

import  pe.edu.upeu.sistema_biblioteca.modelo.Cliente;

import java.util.List;
public interface ClienteServicioI {

    void save(Cliente cliente);

    List<Cliente> findAll();

    Cliente update (Cliente cliente,int index);

    void delete(int index);

    Cliente findById(int index);



}
