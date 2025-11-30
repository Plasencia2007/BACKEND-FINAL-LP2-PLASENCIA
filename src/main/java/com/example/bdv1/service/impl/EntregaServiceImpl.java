package com.example.bdv1.service.impl;

import com.example.bdv1.controller.exceptions.ResourceNotFoundException;
import com.example.bdv1.dto.EntregaDTO;
import com.example.bdv1.entity.Entrega;
import com.example.bdv1.entity.Vehiculo;
import com.example.bdv1.entity.Chofer;
import com.example.bdv1.entity.Cliente;
import com.example.bdv1.entity.Estado;
import com.example.bdv1.mappers.EntregaMapper;
import com.example.bdv1.repository.EntregaRepository;
import com.example.bdv1.repository.VehiculoRepository;
import com.example.bdv1.repository.ChoferRepository;
import com.example.bdv1.repository.ClienteRepository;
import com.example.bdv1.repository.EstadoRepository;
import com.example.bdv1.service.service.EntregaService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EntregaServiceImpl implements EntregaService {

    private final EntregaRepository repository;
    private final EntregaMapper mapper;
    private final VehiculoRepository vehiculoRepository;
    private final ChoferRepository choferRepository;
    private final ClienteRepository clienteRepository;
    private final EstadoRepository estadoRepository;

    public EntregaServiceImpl(
            EntregaRepository repository,
            EntregaMapper mapper,
            VehiculoRepository vehiculoRepository,
            ChoferRepository choferRepository,
            ClienteRepository clienteRepository,
            EstadoRepository estadoRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.vehiculoRepository = vehiculoRepository;
        this.choferRepository = choferRepository;
        this.clienteRepository = clienteRepository;
        this.estadoRepository = estadoRepository;
    }

    @Override
    public EntregaDTO create(EntregaDTO dto) throws ServiceException {
        if (dto == null) {
            throw new IllegalArgumentException("La entrega no puede ser nula");
        }

        Vehiculo vehiculo = vehiculoRepository.findById(dto.getIdVehiculo())
                .orElseThrow(() -> new ResourceNotFoundException("Vehículo no encontrado con ID: " + dto.getIdVehiculo()));
        Chofer chofer = choferRepository.findById(dto.getIdChofer())
                .orElseThrow(() -> new ResourceNotFoundException("Chofer no encontrado con ID: " + dto.getIdChofer()));
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + dto.getIdCliente()));
        Estado estado = estadoRepository.findById(dto.getIdEstado())
                .orElseThrow(() -> new ResourceNotFoundException("Estado no encontrado con ID: " + dto.getIdEstado()));

        Entrega entity = new Entrega();
        entity.setTipoRuta(dto.getTipoRuta());
        entity.setFechaProgramacion(dto.getFechaProgramacion());
        entity.setFechaFin(dto.getFechaFin());
        entity.setMontoBase(dto.getMontoBase());
        entity.setMontoGastos(dto.getMontoGastos());
        entity.setMontoTotal(dto.getMontoTotal());
        entity.setVehiculo(vehiculo);
        entity.setChofer(chofer);
        entity.setCliente(cliente);
        entity.setEstado(estado);

        Entrega saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public EntregaDTO update(Long id, EntregaDTO dto) throws ServiceException {
        if (id == null || dto == null) {
            throw new IllegalArgumentException("ID y datos no pueden ser nulos");
        }

        Entrega existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entrega no encontrada con ID: " + id));

        Vehiculo vehiculo = vehiculoRepository.findById(dto.getIdVehiculo())
                .orElseThrow(() -> new ResourceNotFoundException("Vehículo no encontrado con ID: " + dto.getIdVehiculo()));
        Chofer chofer = choferRepository.findById(dto.getIdChofer())
                .orElseThrow(() -> new ResourceNotFoundException("Chofer no encontrado con ID: " + dto.getIdChofer()));
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + dto.getIdCliente()));
        Estado estado = estadoRepository.findById(dto.getIdEstado())
                .orElseThrow(() -> new ResourceNotFoundException("Estado no encontrado con ID: " + dto.getIdEstado()));

        existing.setTipoRuta(dto.getTipoRuta());
        existing.setFechaProgramacion(dto.getFechaProgramacion());
        existing.setFechaFin(dto.getFechaFin());
        existing.setMontoBase(dto.getMontoBase());
        existing.setMontoGastos(dto.getMontoGastos());
        existing.setMontoTotal(dto.getMontoTotal());
        existing.setVehiculo(vehiculo);
        existing.setChofer(chofer);
        existing.setCliente(cliente);
        existing.setEstado(estado);

        Entrega updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    @Override
    @Transactional
    public EntregaDTO findById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        Entrega entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entrega no encontrada con ID: " + id));
        return mapper.toDTO(entity);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Entrega no encontrada con ID: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar: existen destinos asociados", ex);
        }
    }

    @Override
    @Transactional
    public List<EntregaDTO> findAll() throws ServiceException {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }
}