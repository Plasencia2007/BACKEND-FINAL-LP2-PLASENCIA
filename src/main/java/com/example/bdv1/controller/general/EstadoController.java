package com.example.bdv1.controller.general;

import com.example.bdv1.dto.EstadoDTO;
import com.example.bdv1.service.service.EstadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/estados")
public class EstadoController {
    private final EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @PreAuthorize("hasAuthority('GET_ALL_ESTADOS')")
    @GetMapping
    public ResponseEntity<List<EstadoDTO>> listarEstados() {
        List<EstadoDTO> estados = estadoService.findAll();
        return ResponseEntity.ok(estados);
    }

    @PreAuthorize("hasAuthority('GET_ONE_ESTADO')")
    @GetMapping("/{id}")
    public ResponseEntity<EstadoDTO> obtenerEstado(@PathVariable Long id) {
        EstadoDTO estado = estadoService.findById(id);
        return ResponseEntity.ok(estado);
    }

    @PreAuthorize("hasAuthority('CREATE_ESTADOS')")
    @PostMapping
    public ResponseEntity<EstadoDTO> crearEstado(@RequestBody EstadoDTO estadoDTO) {
        EstadoDTO creada = estadoService.create(estadoDTO);
        return ResponseEntity.ok(creada);
    }

    @PreAuthorize("hasAuthority('UPDATE_ESTADOS')")
    @PutMapping("/{id}")
    public ResponseEntity<EstadoDTO> actualizarEstado(@PathVariable Long id,
                                                      @RequestBody EstadoDTO estadoDTO) {
        EstadoDTO actualizada = estadoService.update(id, estadoDTO);
        return ResponseEntity.ok(actualizada);
    }

    @PreAuthorize("hasAuthority('DELETE_ESTADOS')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstado(@PathVariable Long id) {
        estadoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}