package com.example.bdv1.service.impl;

import com.example.bdv1.controller.exceptions.ResourceNotFoundException;
import com.example.bdv1.dto.IncidenciaDTO;
import com.example.bdv1.entity.Incidencia;
import com.example.bdv1.mappers.IncidenciaMapper;
import com.example.bdv1.repository.IncidenciaRepository;
import com.example.bdv1.service.service.IncidenciaService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IncidenciaServiceImpl implements IncidenciaService {

    private final IncidenciaRepository repository;
    private final IncidenciaMapper mapper;

    public IncidenciaServiceImpl(IncidenciaRepository repository, IncidenciaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public IncidenciaDTO create(IncidenciaDTO dto) throws ServiceException {
        if (dto == null) {
            throw new IllegalArgumentException("La incidencia no puede ser nula");
        }
        Incidencia entity = mapper.toEntity(dto);
        Incidencia saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public IncidenciaDTO update(Long id, IncidenciaDTO dto) throws ServiceException {
        if (id == null || dto == null) {
            throw new IllegalArgumentException("ID y datos no pueden ser nulos");
        }
        Incidencia existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incidencia no encontrada con ID: " + id));
        existing.setTipoIncidencia(dto.getTipoIncidencia());
        existing.setPrioridad(dto.getPrioridad());
        existing.setDetalles(dto.getDetalles());
        existing.setEvidencia(dto.getEvidencia());
        Incidencia updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    @Override
    @Transactional
    public IncidenciaDTO findById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        Incidencia entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incidencia no encontrada con ID: " + id));
        return mapper.toDTO(entity);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Incidencia no encontrada con ID: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar: existen detalles asociados", ex);
        }
    }

    @Override
    @Transactional
    public List<IncidenciaDTO> findAll() throws ServiceException {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }
}