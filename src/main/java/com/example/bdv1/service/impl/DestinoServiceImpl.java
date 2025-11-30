package com.example.bdv1.service.impl;

import com.example.bdv1.controller.exceptions.ResourceNotFoundException;
import com.example.bdv1.dto.DestinoDTO;
import com.example.bdv1.entity.Destino;
import com.example.bdv1.entity.Entrega;
import com.example.bdv1.entity.Tienda;
import com.example.bdv1.entity.Estado;
import com.example.bdv1.mappers.DestinoMapper;
import com.example.bdv1.repository.DestinoRepository;
import com.example.bdv1.repository.EntregaRepository;
import com.example.bdv1.repository.TiendaRepository;
import com.example.bdv1.repository.EstadoRepository;
import com.example.bdv1.service.service.DestinoService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DestinoServiceImpl implements DestinoService {

    private final DestinoRepository repository;
    private final DestinoMapper mapper;
    private final EntregaRepository entregaRepository;
    private final TiendaRepository tiendaRepository;
    private final EstadoRepository estadoRepository;

    public DestinoServiceImpl(
            DestinoRepository repository,
            DestinoMapper mapper,
            EntregaRepository entregaRepository,
            TiendaRepository tiendaRepository,
            EstadoRepository estadoRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.entregaRepository = entregaRepository;
        this.tiendaRepository = tiendaRepository;
        this.estadoRepository = estadoRepository;
    }

    @Override
    public DestinoDTO create(DestinoDTO dto) throws ServiceException {
        if (dto == null) {
            throw new IllegalArgumentException("El destino no puede ser nulo");
        }

        Entrega entrega = entregaRepository.findById(dto.getIdEntrega())
                .orElseThrow(() -> new ResourceNotFoundException("Entrega no encontrada con ID: " + dto.getIdEntrega()));
        Tienda tienda = tiendaRepository.findById(dto.getIdTienda())
                .orElseThrow(() -> new ResourceNotFoundException("Tienda no encontrada con ID: " + dto.getIdTienda()));
        Estado estado = estadoRepository.findById(dto.getIdEstado())
                .orElseThrow(() -> new ResourceNotFoundException("Estado no encontrado con ID: " + dto.getIdEstado()));

        Destino entity = new Destino();
        entity.setVolumenM3(dto.getVolumenM3());
        entity.setVhInicio(dto.getVhInicio());
        entity.setVhFinal(dto.getVhFinal());
        entity.setAlmacen(dto.getAlmacen());
        entity.setPsex(dto.getPsex());
        entity.setCanal(dto.getCanal());
        entity.setObservaciones(dto.getObservaciones());
        entity.setHoraEntrega(dto.getHoraEntrega());
        entity.setEntrega(entrega);
        entity.setTienda(tienda);
        entity.setEstado(estado);

        Destino saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public DestinoDTO update(Long id, DestinoDTO dto) throws ServiceException {
        if (id == null || dto == null) {
            throw new IllegalArgumentException("ID y datos no pueden ser nulos");
        }

        Destino existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destino no encontrado con ID: " + id));

        Entrega entrega = entregaRepository.findById(dto.getIdEntrega())
                .orElseThrow(() -> new ResourceNotFoundException("Entrega no encontrada con ID: " + dto.getIdEntrega()));
        Tienda tienda = tiendaRepository.findById(dto.getIdTienda())
                .orElseThrow(() -> new ResourceNotFoundException("Tienda no encontrada con ID: " + dto.getIdTienda()));
        Estado estado = estadoRepository.findById(dto.getIdEstado())
                .orElseThrow(() -> new ResourceNotFoundException("Estado no encontrado con ID: " + dto.getIdEstado()));

        existing.setVolumenM3(dto.getVolumenM3());
        existing.setVhInicio(dto.getVhInicio());
        existing.setVhFinal(dto.getVhFinal());
        existing.setAlmacen(dto.getAlmacen());
        existing.setPsex(dto.getPsex());
        existing.setCanal(dto.getCanal());
        existing.setObservaciones(dto.getObservaciones());
        existing.setHoraEntrega(dto.getHoraEntrega());
        existing.setEntrega(entrega);
        existing.setTienda(tienda);
        existing.setEstado(estado);

        Destino updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    @Override
    @Transactional
    public DestinoDTO findById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        Destino entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destino no encontrado con ID: " + id));
        return mapper.toDTO(entity);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Destino no encontrado con ID: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar: existen documentos asociados", ex);
        }
    }

    @Override
    @Transactional
    public List<DestinoDTO> findAll() throws ServiceException {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }
}