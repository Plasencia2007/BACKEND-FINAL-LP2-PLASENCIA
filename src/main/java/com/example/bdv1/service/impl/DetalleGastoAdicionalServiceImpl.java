package com.example.bdv1.service.impl;

import com.example.bdv1.controller.exceptions.ResourceNotFoundException;
import com.example.bdv1.dto.DetalleGastoAdicionalDTO;
import com.example.bdv1.entity.DetalleGastoAdicional;
import com.example.bdv1.entity.Entrega;
import com.example.bdv1.entity.GastoAdicional;
import com.example.bdv1.mappers.DetalleGastoAdicionalMapper;
import com.example.bdv1.repository.DetalleGastoAdicionalRepository;
import com.example.bdv1.repository.EntregaRepository;
import com.example.bdv1.repository.GastoAdicionalRepository;
import com.example.bdv1.service.service.DetalleGastoAdicionalService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleGastoAdicionalServiceImpl implements DetalleGastoAdicionalService {

    private final DetalleGastoAdicionalRepository repository;
    private final DetalleGastoAdicionalMapper mapper;
    private final GastoAdicionalRepository gastoAdicionalRepository;
    private final EntregaRepository entregaRepository;

    public DetalleGastoAdicionalServiceImpl(
            DetalleGastoAdicionalRepository repository,
            DetalleGastoAdicionalMapper mapper,
            GastoAdicionalRepository gastoAdicionalRepository,
            EntregaRepository entregaRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.gastoAdicionalRepository = gastoAdicionalRepository;
        this.entregaRepository = entregaRepository;
    }

    @Override
    public DetalleGastoAdicionalDTO create(DetalleGastoAdicionalDTO dto) throws ServiceException {
        if (dto == null) {
            throw new IllegalArgumentException("El detalle de gasto no puede ser nulo");
        }

        GastoAdicional gasto = gastoAdicionalRepository.findById(dto.getIdGastoAdicional())
                .orElseThrow(() -> new ResourceNotFoundException("Gasto no encontrado con ID: " + dto.getIdGastoAdicional()));
        Entrega entrega = entregaRepository.findById(dto.getIdEntrega())
                .orElseThrow(() -> new ResourceNotFoundException("Entrega no encontrada con ID: " + dto.getIdEntrega()));

        DetalleGastoAdicional entity = new DetalleGastoAdicional();
        entity.setFechaRegistro(dto.getFechaRegistro());
        entity.setGastoAdicional(gasto);
        entity.setEntrega(entrega);

        DetalleGastoAdicional saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public DetalleGastoAdicionalDTO update(Long id, DetalleGastoAdicionalDTO dto) throws ServiceException {
        if (id == null || dto == null) {
            throw new IllegalArgumentException("ID y datos no pueden ser nulos");
        }

        DetalleGastoAdicional existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle de gasto no encontrado con ID: " + id));

        GastoAdicional gasto = gastoAdicionalRepository.findById(dto.getIdGastoAdicional())
                .orElseThrow(() -> new ResourceNotFoundException("Gasto no encontrado con ID: " + dto.getIdGastoAdicional()));
        Entrega entrega = entregaRepository.findById(dto.getIdEntrega())
                .orElseThrow(() -> new ResourceNotFoundException("Entrega no encontrada con ID: " + dto.getIdEntrega()));

        existing.setFechaRegistro(dto.getFechaRegistro());
        existing.setGastoAdicional(gasto);
        existing.setEntrega(entrega);

        DetalleGastoAdicional updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    // findById, deleteById, findAll → igual que antes
    @Override
    public DetalleGastoAdicionalDTO findById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        DetalleGastoAdicional entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle de gasto no encontrado con ID: " + id));
        return mapper.toDTO(entity);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Detalle de gasto no encontrado con ID: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar: error de integridad", ex);
        }
    }

    @Override
    public List<DetalleGastoAdicionalDTO> findAll() throws ServiceException {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }
}