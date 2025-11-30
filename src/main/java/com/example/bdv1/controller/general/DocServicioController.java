package com.example.bdv1.controller.general;

import com.example.bdv1.dto.DocServicioDTO;
import com.example.bdv1.service.service.DocServicioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docservicios")
public class DocServicioController {
    private final DocServicioService docServicioService;

    public DocServicioController(DocServicioService docServicioService) {
        this.docServicioService = docServicioService;
    }

    @GetMapping
    public ResponseEntity<List<DocServicioDTO>> listarDocServicios() {
        List<DocServicioDTO> docs = docServicioService.findAll();
        return ResponseEntity.ok(docs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocServicioDTO> obtenerDocServicio(@PathVariable Long id) {
        DocServicioDTO doc = docServicioService.findById(id);
        return ResponseEntity.ok(doc);
    }

    @PostMapping
    public ResponseEntity<DocServicioDTO> crearDocServicio(@RequestBody DocServicioDTO docServicioDTO) {
        DocServicioDTO creada = docServicioService.create(docServicioDTO);
        return ResponseEntity.ok(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocServicioDTO> actualizarDocServicio(@PathVariable Long id,
                                                                @RequestBody DocServicioDTO docServicioDTO) {
        DocServicioDTO actualizada = docServicioService.update(id, docServicioDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDocServicio(@PathVariable Long id) {
        docServicioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}