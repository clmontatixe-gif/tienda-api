package com.tienda.api.service;

import com.tienda.api.exception.ProductoInvalidoException;
import com.tienda.api.exception.ProductoNoEncontradoException;
import com.tienda.api.model.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {

    // Lista para almacenar los productos en memoria
    private final List<Producto> productos = new ArrayList<>();

    // Contador para generar identificadores automáticamente
    private Long siguienteId = 1L;

    // Constructor con productos iniciales de ejemplo
    public ProductoService() {
        productos.add(new Producto(siguienteId++, "Laptop", 850.00, 10));
        productos.add(new Producto(siguienteId++, "Mouse", 15.50, 25));
        productos.add(new Producto(siguienteId++, "Teclado", 30.00, 15));
    }

    // Consultar todos los productos
    public List<Producto> listarProductos() {
        return productos;
    }

    // Buscar un producto por su identificador
    public Producto buscarPorId(Long id) {

        return productos.stream()
                .filter(producto -> producto.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new ProductoNoEncontradoException(
                                "Producto con ID " + id + " no encontrado"
                        )
                );
    }

    // Registrar un nuevo producto
    public Producto crearProducto(Producto producto) {

        // Validar que el precio sea mayor que cero
        if (producto.getPrecio() <= 0) {
            throw new ProductoInvalidoException(
                    "El precio debe ser mayor que cero"
            );
        }

        // Validar que el stock no sea negativo
        if (producto.getStock() < 0) {
            throw new ProductoInvalidoException(
                    "El stock no puede ser negativo"
            );
        }

        // Asignar identificador automáticamente
        producto.setId(siguienteId++);

        // Agregar el producto a la lista
        productos.add(producto);

        return producto;
    }

    // Eliminar un producto por su identificador
    public void eliminarProducto(Long id) {

        // Primero comprobamos que el producto exista
        Producto producto = buscarPorId(id);

        // Eliminar el producto de la lista
        productos.remove(producto);
    }
}
