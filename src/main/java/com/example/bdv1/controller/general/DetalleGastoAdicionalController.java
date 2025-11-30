package com.example.bdv1.controller.general;

import com.example.bdv1.dto.DetalleGastoAdicionalDTO;
import com.example.bdv1.service.service.DetalleGastoAdicionalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detallegastosadicionales")
public class DetalleGastoAdicionalController {
    private final DetalleGastoAdicionalService detalleGastoAdicionalService;

    public DetalleGastoAdicionalController(DetalleGastoAdicionalService detalleGastoAdicionalService) {
        this.detalleGastoAdicionalService = detalleGastoAdicionalService;
    }

    @GetMapping
    public ResponseEntity<List<DetalleGastoAdicionalDTO>> listarDetalleGastosAdicionales() {
        List<DetalleGastoAdicionalDTO> detalles = detalleGastoAdicionalService.findAll();
        return ResponseEntity.ok(detalles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleGastoAdicionalDTO> obtenerDetalleGastoAdicional(@PathVariable Long id) {
        DetalleGastoAdicionalDTO detalle = detalleGastoAdicionalService.findById(id);
        return ResponseEntity.ok(detalle);
    }

    @PostMapping
    public ResponseEntity<DetalleGastoAdicionalDTO> crearDetalleGastoAdicional(@RequestBody DetalleGastoAdicionalDTO detalleGastoAdicionalDTO) {
        DetalleGastoAdicionalDTO creada = detalleGastoAdicionalService.create(detalleGastoAdicionalDTO);
        return ResponseEntity.ok(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleGastoAdicionalDTO> actualizarDetalleGastoAdicional(@PathVariable Long id,
                                                                                    @RequestBody DetalleGastoAdicionalDTO detalleGastoAdicionalDTO) {
        DetalleGastoAdicionalDTO actualizada = detalleGastoAdicionalService.update(id, detalleGastoAdicionalDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalleGastoAdicional(@PathVariable Long id) {
        detalleGastoAdicionalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}