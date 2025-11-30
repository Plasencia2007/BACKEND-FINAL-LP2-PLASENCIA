package com.example.bdv1.controller.general;

import com.example.bdv1.dto.IncidenciaDTO;
import com.example.bdv1.service.service.IncidenciaService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

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

    // ✅ CORREGIDO: Devuelve texto plano (no JSON)
    @PostMapping(value = "/upload", produces = MediaType.TEXT_PLAIN_VALUE)
    @PreAuthorize("hasAuthority('CREATE_INCIDENCIAS') or hasAuthority('UPDATE_INCIDENCIAS')")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Archivo vacío");
            }

            String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFilename = UUID.randomUUID().toString() + extension;

            Path uploadPath = Paths.get("uploads");
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(uniqueFilename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String fileUrl = "/api/v1/uploads/" + uniqueFilename;
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(fileUrl);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al subir archivo");
        }
    }
}