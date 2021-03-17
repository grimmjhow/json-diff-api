package br.com.waes.json.diff.repositories;

import br.com.waes.json.diff.model.DiffSide;
import br.com.waes.json.diff.model.JsonComparison;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JsonComparisonRepository extends CrudRepository<JsonComparison,String> {

    Optional<JsonComparison> findByIdAndSide(long id, DiffSide side);
    List<JsonComparison> findAll();

    List<JsonComparison> findByIdOrderBySide(long id);
}
