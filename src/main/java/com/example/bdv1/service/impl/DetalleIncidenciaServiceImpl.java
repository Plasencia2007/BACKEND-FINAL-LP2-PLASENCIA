package com.example.bdv1.service.impl;

import com.example.bdv1.controller.exceptions.ResourceNotFoundException;
import com.example.bdv1.dto.DetalleIncidenciaDTO;
import com.example.bdv1.entity.DetalleIncidencia;
import com.example.bdv1.entity.Entrega;
import com.example.bdv1.entity.Incidencia;
import com.example.bdv1.mappers.DetalleIncidenciaMapper;
import com.example.bdv1.repository.DetalleIncidenciaRepository;
import com.example.bdv1.repository.EntregaRepository;
import com.example.bdv1.repository.IncidenciaRepository;
import com.example.bdv1.service.service.DetalleIncidenciaService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleIncidenciaServiceImpl implements DetalleIncidenciaService {

    private final DetalleIncidenciaRepository repository;
    private final DetalleIncidenciaMapper mapper;
    private final IncidenciaRepository incidenciaRepository;
    private final EntregaRepository entregaRepository;

    public DetalleIncidenciaServiceImpl(
            DetalleIncidenciaRepository repository,
            DetalleIncidenciaMapper mapper,
            IncidenciaRepository incidenciaRepository,
            EntregaRepository entregaRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.incidenciaRepository = incidenciaRepository;
        this.entregaRepository = entregaRepository;
    }

    @Override
    public DetalleIncidenciaDTO create(DetalleIncidenciaDTO dto) throws ServiceException {
        if (dto == null) {
            throw new IllegalArgumentException("El detalle de incidencia no puede ser nulo");
        }

        Incidencia incidencia = incidenciaRepository.findById(dto.getIdIncidencia())
                .orElseThrow(() -> new ResourceNotFoundException("Incidencia no encontrada con ID: " + dto.getIdIncidencia()));
        Entrega entrega = entregaRepository.findById(dto.getIdEntrega())
                .orElseThrow(() -> new ResourceNotFoundException("Entrega no encontrada con ID: " + dto.getIdEntrega()));

        DetalleIncidencia entity = new DetalleIncidencia();
        entity.setFechaRegistro(dto.getFechaRegistro());
        entity.setIncidencia(incidencia);
        entity.setEntrega(entrega);

        DetalleIncidencia saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public DetalleIncidenciaDTO update(Long id, DetalleIncidenciaDTO dto) throws ServiceException {
        if (id == null || dto == null) {
            throw new IllegalArgumentException("ID y datos no pueden ser nulos");
        }

        DetalleIncidencia existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle de incidencia no encontrado con ID: " + id));

        Incidencia incidencia = incidenciaRepository.findById(dto.getIdIncidencia())
                .orElseThrow(() -> new ResourceNotFoundException("Incidencia no encontrada con ID: " + dto.getIdIncidencia()));
        Entrega entrega = entregaRepository.findById(dto.getIdEntrega())
                .orElseThrow(() -> new ResourceNotFoundException("Entrega no encontrada con ID: " + dto.getIdEntrega()));

        existing.setFechaRegistro(dto.getFechaRegistro());
        existing.setIncidencia(incidencia);
        existing.setEntrega(entrega);

        DetalleIncidencia updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    @Override
    public DetalleIncidenciaDTO findById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        DetalleIncidencia entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Detalle de incidencia no encontrado con ID: " + id));
        return mapper.toDTO(entity);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Detalle de incidencia no encontrado con ID: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar: error de integridad", ex);
        }
    }

    @Override
    public List<DetalleIncidenciaDTO> findAll() throws ServiceException {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }
}