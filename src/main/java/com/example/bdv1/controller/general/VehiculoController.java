package com.example.bdv1.controller.general;

import com.example.bdv1.dto.VehiculoDTO;
import com.example.bdv1.service.service.VehiculoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/vehiculos")
public class VehiculoController {
    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @PreAuthorize("hasAuthority('GET_ALL_VEHICULOS')")
    @GetMapping
    public ResponseEntity<List<VehiculoDTO>> listarVehiculos() {
        List<VehiculoDTO> vehiculos = vehiculoService.findAll();
        return ResponseEntity.ok(vehiculos);
    }

    @PreAuthorize("hasAuthority('GET_ONE_VEHICULO')")
    @GetMapping("/{id}")
    public ResponseEntity<VehiculoDTO> obtenerVehiculo(@PathVariable Long id) {
        VehiculoDTO vehiculo = vehiculoService.findById(id);
        return ResponseEntity.ok(vehiculo);
    }

    @PreAuthorize("hasAuthority('CREATE_VEHICULOS')")
    @PostMapping
    public ResponseEntity<VehiculoDTO> crearVehiculo(@RequestBody VehiculoDTO vehiculoDTO) {
        VehiculoDTO creada = vehiculoService.create(vehiculoDTO);
        return ResponseEntity.ok(creada);
    }

    @PreAuthorize("hasAuthority('UPDATE_VEHICULOS')")
    @PutMapping("/{id}")
    public ResponseEntity<VehiculoDTO> actualizarVehiculo(@PathVariable Long id,
                                                          @RequestBody VehiculoDTO vehiculoDTO) {
        VehiculoDTO actualizada = vehiculoService.update(id, vehiculoDTO);
        return ResponseEntity.ok(actualizada);
    }

    @PreAuthorize("hasAuthority('DELETE_VEHICULOS')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVehiculo(@PathVariable Long id) {
        vehiculoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}