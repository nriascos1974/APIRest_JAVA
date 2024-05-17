package com.apirest.apirest.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.apirest.apirest.Repositories.ProductoRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.apirest.apirest.Entities.Producto;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con el Id: " + id));
    }

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @PutMapping()
    public Producto updateProducto(@RequestBody Producto producto) {
        Producto product = productoRepository.findById(producto.getId())
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con el Id: " + producto.getId()));

        product.setNombre(producto.getNombre());
        product.setPrecio(producto.getPrecio());

        return productoRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id) {
        Producto product = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con el Id: " + id));

        productoRepository.delete(product);    
        
        return "El producto con Id :"+ id + " ha sido borrado correctamente.";
    }

}
