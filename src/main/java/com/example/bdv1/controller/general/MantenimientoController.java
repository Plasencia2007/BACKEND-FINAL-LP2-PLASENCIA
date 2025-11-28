package com.example.bdv1.controller.general;

import com.example.bdv1.dto.MantenimientoDTO;
import com.example.bdv1.service.service.MantenimientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mantenimientos")
public class MantenimientoController {
    private final MantenimientoService mantenimientoService;

    public MantenimientoController(MantenimientoService mantenimientoService) {
        this.mantenimientoService = mantenimientoService;
    }

    @GetMapping
    public ResponseEntity<List<MantenimientoDTO>> listarMantenimientos() {
        List<MantenimientoDTO> mantenimientos = mantenimientoService.findAll();
        return ResponseEntity.ok(mantenimientos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MantenimientoDTO> obtenerMantenimiento(@PathVariable Long id) {
        MantenimientoDTO mantenimiento = mantenimientoService.findById(id);
        return ResponseEntity.ok(mantenimiento);
    }

    @PostMapping
    public ResponseEntity<MantenimientoDTO> crearMantenimiento(@RequestBody MantenimientoDTO mantenimientoDTO) {
        MantenimientoDTO creada = mantenimientoService.create(mantenimientoDTO);
        return ResponseEntity.ok(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MantenimientoDTO> actualizarMantenimiento(@PathVariable Long id,
                                                                    @RequestBody MantenimientoDTO mantenimientoDTO) {
        MantenimientoDTO actualizada = mantenimientoService.update(id, mantenimientoDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMantenimiento(@PathVariable Long id) {
        mantenimientoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}