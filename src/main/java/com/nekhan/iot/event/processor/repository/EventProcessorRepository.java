package com.nekhan.iot.event.processor.repository;

import com.nekhan.iot.event.processor.entity.EventDataEntity;
import com.nekhan.iot.event.processor.entity.EventEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The {@code EventProcessorRepository} interface provides CRUD operations for {@link EventEntity} objects.
 * This repository is reactive and extends the {@link ReactiveCrudRepository} interface, allowing for non-blocking
 * database operations.
 *
 * <p>This repository is annotated with {@link Repository}, indicating that it's a Spring Data repository.</p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * @Autowired
 * private EventProcessorRepository repository;
 *
 * public Mono<EventEntity> saveEventData(EventEntity event) {
 *     return repository.save(event);
 * }
 * }</pre>
 *
 * @author Neha Khan
 * @version 1.0
 */
@Repository
public interface EventProcessorRepository extends ReactiveCrudRepository<EventEntity, Long> {

}
