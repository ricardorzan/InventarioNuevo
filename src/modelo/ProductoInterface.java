package modelo;

import java.util.List;

public interface ProductoInterface {
    public boolean create(ProductoPojo producto);
    public List<ProductoPojo> readAll();
    //public EstudiantePojo read(int id);
    //public EstudiantePojo update(EstudiantePojo estudiante);
    //public boolean delete(EstudiantePojo estudiante);
}