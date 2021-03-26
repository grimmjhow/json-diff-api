package br.com.waes.json.diff.repositories;

import br.com.waes.json.diff.model.DiffSide;
import br.com.waes.json.diff.model.JsonComparison;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring data @Repository to fetch, save, update and delete data from
 * a configured RDBMS.
 */
@Repository
public interface JsonComparisonRepository extends CrudRepository<JsonComparison,String> {

    /**
     * Find a {@link JsonComparison} by id and side.
     * @param id Comparison ID
     * @param side RIGHT or LEFT side of the comparison
     * @return a valid {@link JsonComparison} entity or an empty {@link Optional} if no Entity exists with id and side.
     */
    Optional<JsonComparison> findByIdAndSide(long id, DiffSide side);

    /**
     * Find all JsonComparison
     * @return List with all {@link JsonComparison} or empty list if there are no records on the database
     */
    List<JsonComparison> findAll();

    /**
     * Find all {@link JsonComparison} by id and side, ordering by side.
     * @param id Comparison ID
     * @return List ordered by side attribute, or empty list if no record is found
     */
    List<JsonComparison> findByIdOrderBySide(long id);
}
