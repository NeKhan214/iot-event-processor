package com.nekhan.iot.event.processor.repository;

import com.nekhan.iot.event.processor.entity.EventDataEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The {@code EventDataProcessorRepository} interface provides CRUD operations for {@link EventDataEntity} objects.
 * This repository is reactive and extends the {@link ReactiveCrudRepository} interface, allowing for non-blocking
 * database operations.
 *
 * <p>This repository is annotated with {@link Repository}, indicating that it's a Spring Data repository.</p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * @Autowired
 * private EventDataProcessorRepository repository;
 *
 * public Mono<EventDataEntity> saveEventData(EventDataEntity eventData) {
 *     return repository.save(eventData);
 * }
 * }</pre>
 *
 * @author Neha Khan
 * @version 1.0
 */
@Repository
public interface EventDataProcessorRepository extends ReactiveCrudRepository<EventDataEntity, Long> {

}
