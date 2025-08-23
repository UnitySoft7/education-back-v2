package com.system.education.infirmary.product.query.api.repository.impl;

import com.system.education.infirmary.product.core.model.InfirmaryProduct;
import com.system.education.infirmary.product.query.api.repository.ProductRepositories;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class ProductRepositoriesImpl implements ProductRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public ProductRepositoriesImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<InfirmaryProduct> getLastProduct() {
        Query query = new Query();
//        query.addCriteria(Criteria.where("id").exists(true));
        query.with(Sort.by(Sort.Direction.DESC, "productCode"));
        query.limit(1);

        return reactiveMongoTemplate.find(query, InfirmaryProduct.class)
                .next();
    }
}
