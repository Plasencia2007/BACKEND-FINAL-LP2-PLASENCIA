package com.example.bdv1.controller.general;

import com.example.bdv1.dto.GastoAdicionalDTO;
import com.example.bdv1.service.service.GastoAdicionalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gastosadicionales")
public class GastoAdicionalController {
    private final GastoAdicionalService gastoAdicionalService;

    public GastoAdicionalController(GastoAdicionalService gastoAdicionalService) {
        this.gastoAdicionalService = gastoAdicionalService;
    }

    @GetMapping
    public ResponseEntity<List<GastoAdicionalDTO>> listarGastosAdicionales() {
        List<GastoAdicionalDTO> gastos = gastoAdicionalService.findAll();
        return ResponseEntity.ok(gastos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GastoAdicionalDTO> obtenerGastoAdicional(@PathVariable Long id) {
        GastoAdicionalDTO gasto = gastoAdicionalService.findById(id);
        return ResponseEntity.ok(gasto);
    }

    @PostMapping
    public ResponseEntity<GastoAdicionalDTO> crearGastoAdicional(@RequestBody GastoAdicionalDTO gastoAdicionalDTO) {
        GastoAdicionalDTO creada = gastoAdicionalService.create(gastoAdicionalDTO);
        return ResponseEntity.ok(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GastoAdicionalDTO> actualizarGastoAdicional(@PathVariable Long id,
                                                                      @RequestBody GastoAdicionalDTO gastoAdicionalDTO) {
        GastoAdicionalDTO actualizada = gastoAdicionalService.update(id, gastoAdicionalDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarGastoAdicional(@PathVariable Long id) {
        gastoAdicionalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}