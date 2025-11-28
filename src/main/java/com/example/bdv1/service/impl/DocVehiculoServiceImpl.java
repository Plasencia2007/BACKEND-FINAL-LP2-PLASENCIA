package com.example.bdv1.service.impl;

import com.example.bdv1.controller.exceptions.ResourceNotFoundException;
import com.example.bdv1.dto.DocVehiculoDTO;
import com.example.bdv1.entity.DocVehiculo;
import com.example.bdv1.entity.Vehiculo;
import com.example.bdv1.entity.TipoDocVehiculo;
import com.example.bdv1.mappers.DocVehiculoMapper;
import com.example.bdv1.repository.DocVehiculoRepository;
import com.example.bdv1.repository.VehiculoRepository;
import com.example.bdv1.repository.TipoDocVehiculoRepository;
import com.example.bdv1.service.service.DocVehiculoService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocVehiculoServiceImpl implements DocVehiculoService {

    private final DocVehiculoRepository repository;
    private final DocVehiculoMapper mapper;
    private final VehiculoRepository vehiculoRepository;
    private final TipoDocVehiculoRepository tipoDocVehiculoRepository;

    public DocVehiculoServiceImpl(
            DocVehiculoRepository repository,
            DocVehiculoMapper mapper,
            VehiculoRepository vehiculoRepository,
            TipoDocVehiculoRepository tipoDocVehiculoRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.vehiculoRepository = vehiculoRepository;
        this.tipoDocVehiculoRepository = tipoDocVehiculoRepository;
    }

    @Override
    public DocVehiculoDTO create(DocVehiculoDTO dto) throws ServiceException {
        if (dto == null) {
            throw new IllegalArgumentException("El documento vehicular no puede ser nulo");
        }

        Vehiculo vehiculo = vehiculoRepository.findById(dto.getIdVehiculo())
                .orElseThrow(() -> new ResourceNotFoundException("Vehículo no encontrado con ID: " + dto.getIdVehiculo()));
        TipoDocVehiculo tipoDoc = tipoDocVehiculoRepository.findById(dto.getIdTipoDocVehiculo())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de documento vehicular no encontrado con ID: " + dto.getIdTipoDocVehiculo()));

        DocVehiculo entity = new DocVehiculo();
        entity.setCodigo(dto.getCodigo());
        entity.setFechaEmision(dto.getFechaEmision());
        entity.setFechaVencimiento(dto.getFechaVencimiento());
        entity.setVehiculo(vehiculo);
        entity.setTipoDocVehiculo(tipoDoc);

        DocVehiculo saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public DocVehiculoDTO update(Long id, DocVehiculoDTO dto) throws ServiceException {
        if (id == null || dto == null) {
            throw new IllegalArgumentException("ID y datos no pueden ser nulos");
        }

        DocVehiculo existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Documento vehicular no encontrado con ID: " + id));

        Vehiculo vehiculo = vehiculoRepository.findById(dto.getIdVehiculo())
                .orElseThrow(() -> new ResourceNotFoundException("Vehículo no encontrado con ID: " + dto.getIdVehiculo()));
        TipoDocVehiculo tipoDoc = tipoDocVehiculoRepository.findById(dto.getIdTipoDocVehiculo())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de documento vehicular no encontrado con ID: " + dto.getIdTipoDocVehiculo()));

        existing.setCodigo(dto.getCodigo());
        existing.setFechaEmision(dto.getFechaEmision());
        existing.setFechaVencimiento(dto.getFechaVencimiento());
        existing.setVehiculo(vehiculo);
        existing.setTipoDocVehiculo(tipoDoc);

        DocVehiculo updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    @Override
    @Transactional
    public DocVehiculoDTO findById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        DocVehiculo entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Documento vehicular no encontrado con ID: " + id));
        return mapper.toDTO(entity);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Documento vehicular no encontrado con ID: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar: error de integridad", ex);
        }
    }

    @Override
    @Transactional
    public List<DocVehiculoDTO> findAll() throws ServiceException {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }
}