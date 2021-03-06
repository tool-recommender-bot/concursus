package com.opencredo.concursus.domain.events.sourcing;

import com.opencredo.concursus.domain.common.AggregateId;
import com.opencredo.concursus.domain.events.Event;
import com.opencredo.concursus.domain.events.matching.EventTypeMatcher;
import com.opencredo.concursus.domain.time.TimeRange;

import java.util.Collection;
import java.util.List;

/**
 * A source of {@link Event}s, which can be retrieved, replayed or pre-loaded.
 */
public interface EventSource {

    /**
     * Given an {@link EventRetriever}, return an {@link EventSource} that preloads events into an in-memory cache.
     * @param eventRetriever The EventRetriever to use to retrieve events.
     * @return The caching EventSource
     */
    static EventSource retrievingWith(EventRetriever eventRetriever) {
        return CachingEventSource.retrievingWith(eventRetriever);
    }

    List<Event> getEvents(EventTypeMatcher matcher, AggregateId aggregateId, TimeRange timeRange);
    CachedEventSource preload(EventTypeMatcher matcher, String aggregateType, Collection<String> aggregateIds, TimeRange timeRange);

    default List<Event> getEvents(EventTypeMatcher matcher, AggregateId aggregateId) {
        return getEvents(matcher, aggregateId, TimeRange.unbounded());
    }

    default EventReplayer replaying(EventTypeMatcher matcher, AggregateId aggregateId, TimeRange timeRange) {
        return EventReplayer.of(getEvents(matcher, aggregateId, timeRange));
    }

    default EventReplayer replaying(EventTypeMatcher matcher, AggregateId aggregateId) {
        return replaying(matcher, aggregateId, TimeRange.unbounded());
    }

    default CachedEventSource preload(EventTypeMatcher matcher, String aggregateType, Collection<String> aggregateIds) {
        return preload(matcher, aggregateType, aggregateIds, TimeRange.unbounded());
    }

}
