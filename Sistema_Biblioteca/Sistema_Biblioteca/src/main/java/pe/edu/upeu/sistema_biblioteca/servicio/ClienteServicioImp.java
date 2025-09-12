package pe.edu.upeu.sistema_biblioteca.servicio;


import org.springframework.stereotype.Service;
import pe.edu.upeu.sistema_biblioteca.modelo.Cliente;
import pe.edu.upeu.sistema_biblioteca.repositorio.ClienteRepository;

import  java.util.List;

@Service
public class ClienteServicioImp extends ClienteRepository implements ClienteServicioI {

    @Override
    public void save(Cliente cliente) {
        clientes.add(cliente);
    }

    @Override
    public List<Cliente> findAll() {
        if(clientes.isEmpty()) {
            return List.of();
        }
        return  clientes;
    }

    @Override
    public Cliente update(Cliente cliente, int index) {
        return clientes.set(index, cliente);
    }

    @Override
    public void delete(int index) {
        clientes.remove(index);
    }

    @Override
    public Cliente findById(int index) {
        return clientes.get(index);
    }
}
