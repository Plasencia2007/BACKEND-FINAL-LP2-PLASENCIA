package com.example.bdv1.controller.general;

import com.example.bdv1.dto.EstadoDTO;
import com.example.bdv1.service.service.EstadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {
    private final EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping
    public ResponseEntity<List<EstadoDTO>> listarEstados() {
        List<EstadoDTO> estados = estadoService.findAll();
        return ResponseEntity.ok(estados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoDTO> obtenerEstado(@PathVariable Long id) {
        EstadoDTO estado = estadoService.findById(id);
        return ResponseEntity.ok(estado);
    }

    @PostMapping
    public ResponseEntity<EstadoDTO> crearEstado(@RequestBody EstadoDTO estadoDTO) {
        EstadoDTO creada = estadoService.create(estadoDTO);
        return ResponseEntity.ok(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoDTO> actualizarEstado(@PathVariable Long id,
                                                      @RequestBody EstadoDTO estadoDTO) {
        EstadoDTO actualizada = estadoService.update(id, estadoDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstado(@PathVariable Long id) {
        estadoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}