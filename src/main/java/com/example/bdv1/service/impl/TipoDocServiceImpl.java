package com.example.bdv1.service.impl;

import com.example.bdv1.controller.exceptions.ResourceNotFoundException;
import com.example.bdv1.dto.TipoDocDTO;
import com.example.bdv1.entity.TipoDoc;
import com.example.bdv1.mappers.TipoDocMapper;
import com.example.bdv1.repository.TipoDocRepository;
import com.example.bdv1.service.service.TipoDocService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoDocServiceImpl implements TipoDocService {

    private final TipoDocRepository repository;
    private final TipoDocMapper mapper;

    public TipoDocServiceImpl(TipoDocRepository repository, TipoDocMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TipoDocDTO create(TipoDocDTO dto) throws ServiceException {
        if (dto == null) {
            throw new IllegalArgumentException("El tipo de documento no puede ser nulo");
        }
        TipoDoc entity = mapper.toEntity(dto);
        TipoDoc saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public TipoDocDTO update(Long id, TipoDocDTO dto) throws ServiceException {
        if (id == null || dto == null) {
            throw new IllegalArgumentException("ID y datos no pueden ser nulos");
        }
        TipoDoc existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de documento no encontrado con ID: " + id));
        existing.setDescripcion(dto.getDescripcion());
        TipoDoc updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    @Override
    @Transactional
    public TipoDocDTO findById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        TipoDoc entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de documento no encontrado con ID: " + id));
        return mapper.toDTO(entity);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Tipo de documento no encontrado con ID: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar: existen documentos de servicio asociados", ex);
        }
    }

    @Override
    @Transactional
    public List<TipoDocDTO> findAll() throws ServiceException {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }
}