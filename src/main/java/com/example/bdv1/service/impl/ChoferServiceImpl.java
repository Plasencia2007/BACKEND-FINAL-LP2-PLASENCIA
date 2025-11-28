package com.example.bdv1.service.impl;

import com.example.bdv1.controller.exceptions.ResourceNotFoundException;
import com.example.bdv1.dto.ChoferDTO;
import com.example.bdv1.entity.Chofer;
import com.example.bdv1.mappers.ChoferMapper;
import com.example.bdv1.repository.ChoferRepository;
import com.example.bdv1.service.service.ChoferService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChoferServiceImpl implements ChoferService {

    private final ChoferRepository repository;
    private final ChoferMapper mapper;

    public ChoferServiceImpl(ChoferRepository repository, ChoferMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ChoferDTO create(ChoferDTO dto) throws ServiceException {
        if (dto == null) {
            throw new IllegalArgumentException("El chofer no puede ser nulo");
        }
        Chofer entity = mapper.toEntity(dto);
        Chofer saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public ChoferDTO update(Long id, ChoferDTO dto) throws ServiceException {
        if (id == null || dto == null) {
            throw new IllegalArgumentException("ID y datos no pueden ser nulos");
        }
        Chofer existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Chofer no encontrado con ID: " + id));
        existing.setNombres(dto.getNombres());
        existing.setDni(dto.getDni());
        existing.setLicencia(dto.getLicencia());
        existing.setTelefono(dto.getTelefono());
        Chofer updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    @Override
    @Transactional
    public ChoferDTO findById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        Chofer entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Chofer no encontrado con ID: " + id));
        return mapper.toDTO(entity);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Chofer no encontrado con ID: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar: existen entregas asociadas", ex);
        }
    }

    @Override
    @Transactional
    public List<ChoferDTO> findAll() throws ServiceException {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }
}