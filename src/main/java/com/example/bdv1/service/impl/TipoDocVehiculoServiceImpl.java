package com.example.bdv1.service.impl;

import com.example.bdv1.controller.exceptions.ResourceNotFoundException;
import com.example.bdv1.dto.TipoDocVehiculoDTO;
import com.example.bdv1.entity.TipoDocVehiculo;
import com.example.bdv1.mappers.TipoDocVehiculoMapper;
import com.example.bdv1.repository.TipoDocVehiculoRepository;
import com.example.bdv1.service.service.TipoDocVehiculoService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoDocVehiculoServiceImpl implements TipoDocVehiculoService {

    private final TipoDocVehiculoRepository repository;
    private final TipoDocVehiculoMapper mapper;

    public TipoDocVehiculoServiceImpl(TipoDocVehiculoRepository repository, TipoDocVehiculoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TipoDocVehiculoDTO create(TipoDocVehiculoDTO dto) throws ServiceException {
        if (dto == null) {
            throw new IllegalArgumentException("El tipo de documento vehicular no puede ser nulo");
        }
        TipoDocVehiculo entity = mapper.toEntity(dto);
        TipoDocVehiculo saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public TipoDocVehiculoDTO update(Long id, TipoDocVehiculoDTO dto) throws ServiceException {
        if (id == null || dto == null) {
            throw new IllegalArgumentException("ID y datos no pueden ser nulos");
        }
        TipoDocVehiculo existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de documento vehicular no encontrado con ID: " + id));
        existing.setDescripcion(dto.getDescripcion());
        TipoDocVehiculo updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    @Override
    @Transactional
    public TipoDocVehiculoDTO findById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        TipoDocVehiculo entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de documento vehicular no encontrado con ID: " + id));
        return mapper.toDTO(entity);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Tipo de documento vehicular no encontrado con ID: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar: existen documentos asociados", ex);
        }
    }

    @Override
    @Transactional
    public List<TipoDocVehiculoDTO> findAll() throws ServiceException {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }
}