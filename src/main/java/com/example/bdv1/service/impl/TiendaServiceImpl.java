package com.example.bdv1.service.impl;

import com.example.bdv1.controller.exceptions.ResourceNotFoundException;
import com.example.bdv1.dto.TiendaDTO;
import com.example.bdv1.entity.Tienda;
import com.example.bdv1.mappers.TiendaMapper;
import com.example.bdv1.repository.TiendaRepository;
import com.example.bdv1.service.service.TiendaService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TiendaServiceImpl implements TiendaService {

    private final TiendaRepository repository;
    private final TiendaMapper mapper;

    public TiendaServiceImpl(TiendaRepository repository, TiendaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TiendaDTO create(TiendaDTO dto) throws ServiceException {
        if (dto == null) {
            throw new IllegalArgumentException("La tienda no puede ser nula");
        }
        Tienda entity = mapper.toEntity(dto);
        Tienda saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public TiendaDTO update(Long id, TiendaDTO dto) throws ServiceException {
        if (id == null || dto == null) {
            throw new IllegalArgumentException("ID y datos no pueden ser nulos");
        }
        Tienda existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tienda no encontrada con ID: " + id));
        existing.setCodigo(dto.getCodigo());
        existing.setNombre(dto.getNombre());
        existing.setDireccion(dto.getDireccion());
        existing.setDistrito(dto.getDistrito());
        Tienda updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    @Override
    @Transactional
    public TiendaDTO findById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        Tienda entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tienda no encontrada con ID: " + id));
        return mapper.toDTO(entity);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Tienda no encontrada con ID: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar: existen destinos asociados", ex);
        }
    }

    @Override
    @Transactional
    public List<TiendaDTO> findAll() throws ServiceException {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }
}