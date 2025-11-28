package com.example.bdv1.controller.general;

import com.example.bdv1.dto.TipoDocVehiculoDTO;
import com.example.bdv1.service.service.TipoDocVehiculoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipodocvehiculos")
public class TipoDocVehiculoController {
    private final TipoDocVehiculoService tipoDocVehiculoService;

    public TipoDocVehiculoController(TipoDocVehiculoService tipoDocVehiculoService) {
        this.tipoDocVehiculoService = tipoDocVehiculoService;
    }

    @GetMapping
    public ResponseEntity<List<TipoDocVehiculoDTO>> listarTipoDocVehiculos() {
        List<TipoDocVehiculoDTO> tipos = tipoDocVehiculoService.findAll();
        return ResponseEntity.ok(tipos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDocVehiculoDTO> obtenerTipoDocVehiculo(@PathVariable Long id) {
        TipoDocVehiculoDTO tipo = tipoDocVehiculoService.findById(id);
        return ResponseEntity.ok(tipo);
    }

    @PostMapping
    public ResponseEntity<TipoDocVehiculoDTO> crearTipoDocVehiculo(@RequestBody TipoDocVehiculoDTO tipoDocVehiculoDTO) {
        TipoDocVehiculoDTO creada = tipoDocVehiculoService.create(tipoDocVehiculoDTO);
        return ResponseEntity.ok(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoDocVehiculoDTO> actualizarTipoDocVehiculo(@PathVariable Long id,
                                                                        @RequestBody TipoDocVehiculoDTO tipoDocVehiculoDTO) {
        TipoDocVehiculoDTO actualizada = tipoDocVehiculoService.update(id, tipoDocVehiculoDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipoDocVehiculo(@PathVariable Long id) {
        tipoDocVehiculoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}