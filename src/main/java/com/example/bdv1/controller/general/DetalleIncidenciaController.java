package com.example.bdv1.controller.general;

import com.example.bdv1.dto.DetalleIncidenciaDTO;
import com.example.bdv1.service.service.DetalleIncidenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalleincidencias")
public class DetalleIncidenciaController {
    private final DetalleIncidenciaService detalleIncidenciaService;

    public DetalleIncidenciaController(DetalleIncidenciaService detalleIncidenciaService) {
        this.detalleIncidenciaService = detalleIncidenciaService;
    }

    @GetMapping
    public ResponseEntity<List<DetalleIncidenciaDTO>> listarDetalleIncidencias() {
        List<DetalleIncidenciaDTO> detalles = detalleIncidenciaService.findAll();
        return ResponseEntity.ok(detalles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleIncidenciaDTO> obtenerDetalleIncidencia(@PathVariable Long id) {
        DetalleIncidenciaDTO detalle = detalleIncidenciaService.findById(id);
        return ResponseEntity.ok(detalle);
    }

    @PostMapping
    public ResponseEntity<DetalleIncidenciaDTO> crearDetalleIncidencia(@RequestBody DetalleIncidenciaDTO detalleIncidenciaDTO) {
        DetalleIncidenciaDTO creada = detalleIncidenciaService.create(detalleIncidenciaDTO);
        return ResponseEntity.ok(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleIncidenciaDTO> actualizarDetalleIncidencia(@PathVariable Long id,
                                                                            @RequestBody DetalleIncidenciaDTO detalleIncidenciaDTO) {
        DetalleIncidenciaDTO actualizada = detalleIncidenciaService.update(id, detalleIncidenciaDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalleIncidencia(@PathVariable Long id) {
        detalleIncidenciaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}