package com.example.bdv1.controller.general;

import com.example.bdv1.dto.TipoDocDTO;
import com.example.bdv1.service.service.TipoDocService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipodocs")
public class TipoDocController {
    private final TipoDocService tipoDocService;

    public TipoDocController(TipoDocService tipoDocService) {
        this.tipoDocService = tipoDocService;
    }

    @GetMapping
    public ResponseEntity<List<TipoDocDTO>> listarTipoDocs() {
        List<TipoDocDTO> tipos = tipoDocService.findAll();
        return ResponseEntity.ok(tipos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDocDTO> obtenerTipoDoc(@PathVariable Long id) {
        TipoDocDTO tipo = tipoDocService.findById(id);
        return ResponseEntity.ok(tipo);
    }

    @PostMapping
    public ResponseEntity<TipoDocDTO> crearTipoDoc(@RequestBody TipoDocDTO tipoDocDTO) {
        TipoDocDTO creada = tipoDocService.create(tipoDocDTO);
        return ResponseEntity.ok(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoDocDTO> actualizarTipoDoc(@PathVariable Long id,
                                                        @RequestBody TipoDocDTO tipoDocDTO) {
        TipoDocDTO actualizada = tipoDocService.update(id, tipoDocDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipoDoc(@PathVariable Long id) {
        tipoDocService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}