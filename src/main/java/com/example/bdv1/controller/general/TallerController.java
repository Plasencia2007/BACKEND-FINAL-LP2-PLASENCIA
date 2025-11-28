package com.example.bdv1.controller.general;

import com.example.bdv1.dto.TallerDTO;
import com.example.bdv1.service.service.TallerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/talleres")
public class TallerController {
    private final TallerService tallerService;

    public TallerController(TallerService tallerService) {
        this.tallerService = tallerService;
    }

    @GetMapping
    public ResponseEntity<List<TallerDTO>> listarTalleres() {
        List<TallerDTO> talleres = tallerService.findAll();
        return ResponseEntity.ok(talleres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TallerDTO> obtenerTaller(@PathVariable Long id) {
        TallerDTO taller = tallerService.findById(id);
        return ResponseEntity.ok(taller);
    }

    @PostMapping
    public ResponseEntity<TallerDTO> crearTaller(@RequestBody TallerDTO tallerDTO) {
        TallerDTO creada = tallerService.create(tallerDTO);
        return ResponseEntity.ok(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TallerDTO> actualizarTaller(@PathVariable Long id,
                                                      @RequestBody TallerDTO tallerDTO) {
        TallerDTO actualizada = tallerService.update(id, tallerDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTaller(@PathVariable Long id) {
        tallerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}