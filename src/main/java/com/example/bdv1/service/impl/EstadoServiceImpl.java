package com.example.bdv1.service.impl;

import com.example.bdv1.controller.exceptions.ResourceNotFoundException;
import com.example.bdv1.dto.EstadoDTO;
import com.example.bdv1.entity.Estado;
import com.example.bdv1.mappers.EstadoMapper;
import com.example.bdv1.repository.EstadoRepository;
import com.example.bdv1.service.service.EstadoService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstadoServiceImpl implements EstadoService {

    private final EstadoRepository repository;
    private final EstadoMapper mapper;

    public EstadoServiceImpl(EstadoRepository repository, EstadoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public EstadoDTO create(EstadoDTO dto) throws ServiceException {
        if (dto == null) {
            throw new IllegalArgumentException("El estado no puede ser nulo");
        }
        Estado entity = mapper.toEntity(dto);
        Estado saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public EstadoDTO update(Long id, EstadoDTO dto) throws ServiceException {
        if (id == null || dto == null) {
            throw new IllegalArgumentException("ID y datos no pueden ser nulos");
        }
        Estado existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado no encontrado con ID: " + id));
        existing.setDescripcion(dto.getDescripcion());
        existing.setTipo(dto.getTipo());
        Estado updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    @Override
    @Transactional
    public EstadoDTO findById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        Estado entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado no encontrado con ID: " + id));
        return mapper.toDTO(entity);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Estado no encontrado con ID: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar: existen entregas o destinos asociados", ex);
        }
    }

    @Override
    @Transactional
    public List<EstadoDTO> findAll() throws ServiceException {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }
}