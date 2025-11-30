package com.example.bdv1.controller.general;

import com.example.bdv1.dto.DocVehiculoDTO;
import com.example.bdv1.service.service.DocVehiculoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docvehiculos")
public class DocVehiculoController {
    private final DocVehiculoService docVehiculoService;

    public DocVehiculoController(DocVehiculoService docVehiculoService) {
        this.docVehiculoService = docVehiculoService;
    }

    @GetMapping
    public ResponseEntity<List<DocVehiculoDTO>> listarDocVehiculos() {
        List<DocVehiculoDTO> docs = docVehiculoService.findAll();
        return ResponseEntity.ok(docs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocVehiculoDTO> obtenerDocVehiculo(@PathVariable Long id) {
        DocVehiculoDTO doc = docVehiculoService.findById(id);
        return ResponseEntity.ok(doc);
    }

    @PostMapping
    public ResponseEntity<DocVehiculoDTO> crearDocVehiculo(@RequestBody DocVehiculoDTO docVehiculoDTO) {
        DocVehiculoDTO creada = docVehiculoService.create(docVehiculoDTO);
        return ResponseEntity.ok(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocVehiculoDTO> actualizarDocVehiculo(@PathVariable Long id,
                                                                @RequestBody DocVehiculoDTO docVehiculoDTO) {
        DocVehiculoDTO actualizada = docVehiculoService.update(id, docVehiculoDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDocVehiculo(@PathVariable Long id) {
        docVehiculoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}