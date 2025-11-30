package com.example.bdv1.controller.general;

import com.example.bdv1.dto.TiendaDTO;
import com.example.bdv1.service.service.TiendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tiendas")
public class TiendaController {
    private final TiendaService tiendaService;

    public TiendaController(TiendaService tiendaService) {
        this.tiendaService = tiendaService;
    }

    @GetMapping
    public ResponseEntity<List<TiendaDTO>> listarTiendas() {
        List<TiendaDTO> tiendas = tiendaService.findAll();
        return ResponseEntity.ok(tiendas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TiendaDTO> obtenerTienda(@PathVariable Long id) {
        TiendaDTO tienda = tiendaService.findById(id);
        return ResponseEntity.ok(tienda);
    }

    @PostMapping
    public ResponseEntity<TiendaDTO> crearTienda(@RequestBody TiendaDTO tiendaDTO) {
        TiendaDTO creada = tiendaService.create(tiendaDTO);
        return ResponseEntity.ok(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TiendaDTO> actualizarTienda(@PathVariable Long id,
                                                      @RequestBody TiendaDTO tiendaDTO) {
        TiendaDTO actualizada = tiendaService.update(id, tiendaDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTienda(@PathVariable Long id) {
        tiendaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}