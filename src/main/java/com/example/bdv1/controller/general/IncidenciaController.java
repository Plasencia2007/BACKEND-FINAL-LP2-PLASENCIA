package com.example.bdv1.controller.general;

import com.example.bdv1.dto.IncidenciaDTO;
import com.example.bdv1.service.service.IncidenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/incidencias")
public class IncidenciaController {
    private final IncidenciaService incidenciaService;
    public IncidenciaController(IncidenciaService incidenciaService) {
        this.incidenciaService = incidenciaService;
    }
    @PreAuthorize("hasAuthority('GET_ALL_INCIDENCIAS')")
    @GetMapping
    public ResponseEntity<List<IncidenciaDTO>> listarIncidencias() {
        List<IncidenciaDTO> incidencias = incidenciaService.findAll();
        return ResponseEntity.ok(incidencias);
    }

    @PreAuthorize("hasAuthority('GET_ONE_INCIDENCIA')")
    @GetMapping("/{id}")
    public ResponseEntity<IncidenciaDTO> obtenerIncidencia(@PathVariable Long id) {
        IncidenciaDTO incidencia = incidenciaService.findById(id);
        return ResponseEntity.ok(incidencia);
    }

    @PreAuthorize("hasAuthority('CREATE_INCIDENCIAS')")
    @PostMapping
    public ResponseEntity<IncidenciaDTO> crearIncidencia(@RequestBody IncidenciaDTO incidenciaDTO) {
        IncidenciaDTO creada = incidenciaService.create(incidenciaDTO);
        return ResponseEntity.ok(creada);
    }

    @PreAuthorize("hasAuthority('UPDATE_INCIDENCIAS')")
    @PutMapping("/{id}")
    public ResponseEntity<IncidenciaDTO> actualizarIncidencia(@PathVariable Long id,
                                                              @RequestBody IncidenciaDTO incidenciaDTO) {
        IncidenciaDTO actualizada = incidenciaService.update(id, incidenciaDTO);
        return ResponseEntity.ok(actualizada);
    }

    @PreAuthorize("hasAuthority('INCIDENCIAS')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarIncidencia(@PathVariable Long id) {
        incidenciaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}