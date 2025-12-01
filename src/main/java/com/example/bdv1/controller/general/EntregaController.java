package com.example.bdv1.controller.general;

import com.example.bdv1.dto.EntregaDTO;
import com.example.bdv1.service.service.EntregaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/entregas")
public class EntregaController {
    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @PreAuthorize("hasAuthority('GET_ALL_ENTREGAS')")
    @GetMapping
    public ResponseEntity<List<EntregaDTO>> listarEntregas() {
        List<EntregaDTO> entregas = entregaService.findAll();
        return ResponseEntity.ok(entregas);
    }

    @PreAuthorize("hasAuthority('GET_ONE_ENTREGA')")
    @GetMapping("/{id}")
    public ResponseEntity<EntregaDTO> obtenerEntrega(@PathVariable Long id) {
        EntregaDTO entrega = entregaService.findById(id);
        return ResponseEntity.ok(entrega);
    }

    @PreAuthorize("hasAuthority('CREATE_ENTREGAS')")
    @PostMapping
    public ResponseEntity<EntregaDTO> crearEntrega(@RequestBody EntregaDTO entregaDTO) {
        EntregaDTO creada = entregaService.create(entregaDTO);
        return ResponseEntity.ok(creada);
    }

    @PreAuthorize("hasAuthority('UPDATE_ENTREGAS')")
    @PutMapping("/{id}")
    public ResponseEntity<EntregaDTO> actualizarEntrega(@PathVariable Long id,
                                                        @RequestBody EntregaDTO entregaDTO) {
        EntregaDTO actualizada = entregaService.update(id, entregaDTO);
        return ResponseEntity.ok(actualizada);
    }

    @PreAuthorize("hasAuthority('DELETE_ENTREGAS')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEntrega(@PathVariable Long id) {
        entregaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}