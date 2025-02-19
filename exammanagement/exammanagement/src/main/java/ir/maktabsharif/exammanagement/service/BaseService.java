package ir.maktabsharif.exammanagement.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BaseService<T , DTO> {
    T register(DTO dto);
    List<T> findAll();
    Boolean update(UUID uuid, DTO dto);
    void delete(UUID uuid);
    Optional<T> findById(UUID uuid);

}
