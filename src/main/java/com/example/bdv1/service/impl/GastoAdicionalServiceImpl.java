package com.example.bdv1.service.impl;

import com.example.bdv1.controller.exceptions.ResourceNotFoundException;
import com.example.bdv1.dto.GastoAdicionalDTO;
import com.example.bdv1.entity.GastoAdicional;
import com.example.bdv1.mappers.GastoAdicionalMapper;
import com.example.bdv1.repository.GastoAdicionalRepository;
import com.example.bdv1.service.service.GastoAdicionalService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class GastoAdicionalServiceImpl implements GastoAdicionalService {

    private final GastoAdicionalRepository repository;
    private final GastoAdicionalMapper mapper;

    public GastoAdicionalServiceImpl(GastoAdicionalRepository repository, GastoAdicionalMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public GastoAdicionalDTO create(GastoAdicionalDTO dto) throws ServiceException {
        if (dto == null) {
            throw new IllegalArgumentException("El gasto adicional no puede ser nulo");
        }
        GastoAdicional entity = mapper.toEntity(dto);
        GastoAdicional saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public GastoAdicionalDTO update(Long id, GastoAdicionalDTO dto) throws ServiceException {
        if (id == null || dto == null) {
            throw new IllegalArgumentException("ID y datos no pueden ser nulos");
        }
        GastoAdicional existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gasto adicional no encontrado con ID: " + id));
        existing.setTipoGastosAdicionales(dto.getTipoGastosAdicionales());
        existing.setMonto(dto.getMonto());
        existing.setDetalles(dto.getDetalles());
        existing.setEvidencia(dto.getEvidencia());
        GastoAdicional updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    @Override
    @Transactional
    public GastoAdicionalDTO findById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        GastoAdicional entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gasto adicional no encontrado con ID: " + id));
        return mapper.toDTO(entity);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Gasto adicional no encontrado con ID: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar: existen detalles asociados", ex);
        }
    }

    @Override
    @Transactional
    public List<GastoAdicionalDTO> findAll() throws ServiceException {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }
}