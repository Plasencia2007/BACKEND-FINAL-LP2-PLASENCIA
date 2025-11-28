package com.example.bdv1.service.impl;

import com.example.bdv1.controller.exceptions.ResourceNotFoundException;
import com.example.bdv1.dto.DocServicioDTO;
import com.example.bdv1.entity.DocServicio;
import com.example.bdv1.entity.Destino;
import com.example.bdv1.entity.TipoDoc;
import com.example.bdv1.mappers.DocServicioMapper;
import com.example.bdv1.repository.DocServicioRepository;
import com.example.bdv1.repository.DestinoRepository;
import com.example.bdv1.repository.TipoDocRepository;
import com.example.bdv1.service.service.DocServicioService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocServicioServiceImpl implements DocServicioService {

    private final DocServicioRepository repository;
    private final DocServicioMapper mapper;
    private final DestinoRepository destinoRepository;
    private final TipoDocRepository tipoDocRepository;

    public DocServicioServiceImpl(
            DocServicioRepository repository,
            DocServicioMapper mapper,
            DestinoRepository destinoRepository,
            TipoDocRepository tipoDocRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.destinoRepository = destinoRepository;
        this.tipoDocRepository = tipoDocRepository;
    }

    @Override
    public DocServicioDTO create(DocServicioDTO dto) throws ServiceException {
        if (dto == null) {
            throw new IllegalArgumentException("El documento de servicio no puede ser nulo");
        }

        Destino destino = destinoRepository.findById(dto.getIdDestino())
                .orElseThrow(() -> new ResourceNotFoundException("Destino no encontrado con ID: " + dto.getIdDestino()));
        TipoDoc tipoDoc = tipoDocRepository.findById(dto.getIdTipoDoc())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de documento no encontrado con ID: " + dto.getIdTipoDoc()));

        DocServicio entity = new DocServicio();
        entity.setCodigo(dto.getCodigo());
        entity.setDestino(destino);
        entity.setTipoDoc(tipoDoc);

        DocServicio saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public DocServicioDTO update(Long id, DocServicioDTO dto) throws ServiceException {
        if (id == null || dto == null) {
            throw new IllegalArgumentException("ID y datos no pueden ser nulos");
        }

        DocServicio existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Documento de servicio no encontrado con ID: " + id));

        Destino destino = destinoRepository.findById(dto.getIdDestino())
                .orElseThrow(() -> new ResourceNotFoundException("Destino no encontrado con ID: " + dto.getIdDestino()));
        TipoDoc tipoDoc = tipoDocRepository.findById(dto.getIdTipoDoc())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de documento no encontrado con ID: " + dto.getIdTipoDoc()));

        existing.setCodigo(dto.getCodigo());
        existing.setDestino(destino);
        existing.setTipoDoc(tipoDoc);

        DocServicio updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    // findById, deleteById, findAll → estándar
    @Override
    @Transactional
    public DocServicioDTO findById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        DocServicio entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Documento de servicio no encontrado con ID: " + id));
        return mapper.toDTO(entity);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Documento de servicio no encontrado con ID: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("No se puede eliminar: error de integridad", ex);
        }
    }

    @Override
    @Transactional
    public List<DocServicioDTO> findAll() throws ServiceException {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }
}