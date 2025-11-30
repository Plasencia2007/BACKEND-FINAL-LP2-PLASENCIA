package com.example.bdv1.service.impl;

import com.example.bdv1.controller.exceptions.ResourceNotFoundException;
import com.example.bdv1.dto.VehiculoDTO;
import com.example.bdv1.entity.Vehiculo;
import com.example.bdv1.mappers.VehiculoMapper;
import com.example.bdv1.repository.VehiculoRepository;
import com.example.bdv1.service.service.VehiculoService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    private final VehiculoRepository repository;
    private final VehiculoMapper mapper;

    public VehiculoServiceImpl(VehiculoRepository repository, VehiculoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public VehiculoDTO create(VehiculoDTO dto) throws ServiceException {
        if (dto == null) {
            throw new IllegalArgumentException("El vehículo no puede ser nulo");
        }
        Vehiculo entity = mapper.toEntity(dto);
        Vehiculo saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public VehiculoDTO update(Long id, VehiculoDTO dto) throws ServiceException {
        if (id == null || dto == null) {
            throw new IllegalArgumentException("ID y datos no pueden ser nulos");
        }
        Vehiculo existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehículo no encontrado con ID: " + id));
        existing.setPlaca(dto.getPlaca());
        existing.setMarca(dto.getMarca());
        existing.setModelo(dto.getModelo());
        existing.setCapacidadM3(dto.getCapacidadM3());
        Vehiculo updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    @Override
    @Transactional
    public VehiculoDTO findById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        Vehiculo entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehículo no encontrado con ID: " + id));
        return mapper.toDTO(entity);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Vehículo no encontrado con ID: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar: existen entregas o documentos asociados", ex);
        }
    }

    @Override
    @Transactional
    public List<VehiculoDTO> findAll() throws ServiceException {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }
}