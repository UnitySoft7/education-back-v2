package com.system.education.cafeteria.product.query.api.handler.impl;

import com.system.education.cafeteria.product.core.model.Product;
import com.system.education.cafeteria.product.query.api.handler.ProductQueryHandler;
import com.system.education.cafeteria.product.query.api.query.ProductByCodeQuery;
import com.system.education.cafeteria.product.query.api.query.ProductByIdQuery;
import com.system.education.cafeteria.product.query.api.repository.ProductRepository;
import com.system.education.cafeteria.product.query.api.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductQueryHandlerImpl implements ProductQueryHandler {
    private final ProductRepository productRepository;

    /**
     * This method is used to get all products
     * @return a flux of product response
     */
    @Override
    public Flux<ProductResponse> getProducts() {
        return productRepository.findAll()
                .flatMap(this::getProductResponse);
    }

    /**
     * This method is used to get a product by ID
     * @param query the ID of the product
     * @return a mono of product response
     */
    @Override
    public Mono<ProductResponse> getProductById(ProductByIdQuery query) {
        return productRepository.findById(query.id())
                .flatMap(this::getProductResponse);
    }

    /**
     * This method is used to get a product by product code
     * @param query the product code of the product
     * @return a mono of product response
     */
    @Override
    public Mono<ProductResponse> getProductByCode(ProductByCodeQuery query) {
        return productRepository.findByProductCode(query.code())
                .flatMap(this::getProductResponse);
    }

    /**
     * This method is used to get a product by establishment code
     * @param query the establishment code of the establishment
     * @return a mono of product response
     */
    @Override
    public Flux<ProductResponse> getProductByEstablishment(ProductByCodeQuery query) {
        return productRepository.findByEstablishmentCode(query.code())
                .flatMap(this::getProductResponse);
    }

    /**
     * This method is used to convert a product to a product response
     * @param product the product to convert
     * @return the driver response
     */
    private Mono<ProductResponse> getProductResponse(Product product) {
        return Mono.just(
                new ProductResponse(product.getProductId(),
                        product.getProductCode(), product.getProductName(),
                        product.getAmount(), product.getEstablishmentCode(),
                        product.getEstablishmentName())
        );
    }
}
