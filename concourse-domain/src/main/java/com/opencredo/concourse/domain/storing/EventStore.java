package com.opencredo.concourse.domain.storing;

import com.opencredo.concourse.domain.events.Event;
import com.opencredo.concourse.domain.events.sourcing.EventRetriever;
import com.opencredo.concourse.domain.persisting.EventPersister;

/**
 * A store of {@link Event}s, into which they can be persisted and from which they can be retrieved.
 */
public interface EventStore extends EventPersister, EventRetriever {
}