package com.system.education.parent.query.api.repository;

import com.system.education.parent.core.model.Parent;
import reactor.core.publisher.Mono;

public interface ParentRepositories {
    Mono<Parent> getLastParent();
}
