package com.example.bdv1.service.impl;

import com.example.bdv1.controller.exceptions.ResourceNotFoundException;
import com.example.bdv1.dto.MantenimientoDTO;
import com.example.bdv1.entity.Mantenimiento;
import com.example.bdv1.entity.Vehiculo;
import com.example.bdv1.entity.Taller;
import com.example.bdv1.mappers.MantenimientoMapper;
import com.example.bdv1.repository.MantenimientoRepository;
import com.example.bdv1.repository.VehiculoRepository;
import com.example.bdv1.repository.TallerRepository;
import com.example.bdv1.service.service.MantenimientoService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MantenimientoServiceImpl implements MantenimientoService {

    private final MantenimientoRepository repository;
    private final MantenimientoMapper mapper;
    private final VehiculoRepository vehiculoRepository;
    private final TallerRepository tallerRepository;

    public MantenimientoServiceImpl(
            MantenimientoRepository repository,
            MantenimientoMapper mapper,
            VehiculoRepository vehiculoRepository,
            TallerRepository tallerRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.vehiculoRepository = vehiculoRepository;
        this.tallerRepository = tallerRepository;
    }

    @Override
    public MantenimientoDTO create(MantenimientoDTO dto) throws ServiceException {
        if (dto == null) {
            throw new IllegalArgumentException("El mantenimiento no puede ser nulo");
        }

        Vehiculo vehiculo = vehiculoRepository.findById(dto.getIdVehiculo())
                .orElseThrow(() -> new ResourceNotFoundException("Vehículo no encontrado con ID: " + dto.getIdVehiculo()));
        Taller taller = tallerRepository.findById(dto.getIdTaller())
                .orElseThrow(() -> new ResourceNotFoundException("Taller no encontrado con ID: " + dto.getIdTaller()));

        Mantenimiento entity = new Mantenimiento();
        entity.setTipoMantenimiento(dto.getTipoMantenimiento());
        entity.setCodigo(dto.getCodigo());
        entity.setFechaInicio(dto.getFechaInicio());
        entity.setFechaFin(dto.getFechaFin());
        entity.setVehiculo(vehiculo);
        entity.setTaller(taller);

        Mantenimiento saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public MantenimientoDTO update(Long id, MantenimientoDTO dto) throws ServiceException {
        if (id == null || dto == null) {
            throw new IllegalArgumentException("ID y datos no pueden ser nulos");
        }

        Mantenimiento existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mantenimiento no encontrado con ID: " + id));

        Vehiculo vehiculo = vehiculoRepository.findById(dto.getIdVehiculo())
                .orElseThrow(() -> new ResourceNotFoundException("Vehículo no encontrado con ID: " + dto.getIdVehiculo()));
        Taller taller = tallerRepository.findById(dto.getIdTaller())
                .orElseThrow(() -> new ResourceNotFoundException("Taller no encontrado con ID: " + dto.getIdTaller()));

        existing.setTipoMantenimiento(dto.getTipoMantenimiento());
        existing.setCodigo(dto.getCodigo());
        existing.setFechaInicio(dto.getFechaInicio());
        existing.setFechaFin(dto.getFechaFin());
        existing.setVehiculo(vehiculo);
        existing.setTaller(taller);

        Mantenimiento updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    @Override
    @Transactional
    public MantenimientoDTO findById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        Mantenimiento entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mantenimiento no encontrado con ID: " + id));
        return mapper.toDTO(entity);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Mantenimiento no encontrado con ID: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar: error de integridad", ex);
        }
    }

    @Override
    @Transactional
    public List<MantenimientoDTO> findAll() throws ServiceException {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }
}