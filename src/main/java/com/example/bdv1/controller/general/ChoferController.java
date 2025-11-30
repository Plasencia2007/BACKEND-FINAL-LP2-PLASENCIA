package com.example.bdv1.controller.general;

import com.example.bdv1.dto.ChoferDTO;
import com.example.bdv1.service.service.ChoferService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/choferes")
public class ChoferController {
    private final ChoferService choferService;

    public ChoferController(ChoferService choferService) {
        this.choferService = choferService;
    }

    @PreAuthorize("hasAuthority('GET_ALL_CHOFERES')")
    @GetMapping
    public ResponseEntity<List<ChoferDTO>> listarChoferes() {
        List<ChoferDTO> choferes = choferService.findAll();
        return ResponseEntity.ok(choferes);
    }

    @PreAuthorize("hasAuthority('GET_ONE_CHOFERES')")
    @GetMapping("/{id}")
    public ResponseEntity<ChoferDTO> obtenerChofer(@PathVariable Long id) {
        ChoferDTO chofer = choferService.findById(id);
        return ResponseEntity.ok(chofer);
    }

    @PreAuthorize("hasAuthority('CREATE_CHOFERES')")
    @PostMapping
    public ResponseEntity<ChoferDTO> crearChofer(@RequestBody ChoferDTO choferDTO) {
        ChoferDTO creada = choferService.create(choferDTO);
        return ResponseEntity.ok(creada);
    }

    @PreAuthorize("hasAuthority('UPDATE_CHOFERES')")
    @PutMapping("/{id}")
    public ResponseEntity<ChoferDTO> actualizarChofer(@PathVariable Long id,
                                                      @RequestBody ChoferDTO choferDTO) {
        ChoferDTO actualizada = choferService.update(id, choferDTO);
        return ResponseEntity.ok(actualizada);
    }

    @PreAuthorize("hasAuthority('DELETE_CHOFERES')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarChofer(@PathVariable Long id) {
        choferService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}