package com.example.bdv1.service.impl;

import com.example.bdv1.controller.exceptions.ResourceNotFoundException;
import com.example.bdv1.dto.TallerDTO;
import com.example.bdv1.entity.Taller;
import com.example.bdv1.mappers.TallerMapper;
import com.example.bdv1.repository.TallerRepository;
import com.example.bdv1.service.service.TallerService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TallerServiceImpl implements TallerService {

    private final TallerRepository repository;
    private final TallerMapper mapper;

    public TallerServiceImpl(TallerRepository repository, TallerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TallerDTO create(TallerDTO dto) throws ServiceException {
        if (dto == null) {
            throw new IllegalArgumentException("El taller no puede ser nulo");
        }
        Taller entity = mapper.toEntity(dto);
        Taller saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public TallerDTO update(Long id, TallerDTO dto) throws ServiceException {
        if (id == null || dto == null) {
            throw new IllegalArgumentException("ID y datos no pueden ser nulos");
        }
        Taller existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Taller no encontrado con ID: " + id));
        existing.setNombreTaller(dto.getNombreTaller());
        existing.setDireccion(dto.getDireccion());
        Taller updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    @Override
    @Transactional
    public TallerDTO findById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        Taller entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Taller no encontrado con ID: " + id));
        return mapper.toDTO(entity);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Taller no encontrado con ID: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar: existen mantenimientos asociados", ex);
        }
    }

    @Override
    @Transactional
    public List<TallerDTO> findAll() throws ServiceException {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }
}