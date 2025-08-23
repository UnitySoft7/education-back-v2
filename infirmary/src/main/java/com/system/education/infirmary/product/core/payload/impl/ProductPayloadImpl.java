package com.system.education.infirmary.product.core.payload.impl;

import com.system.education.infirmary.core.common.ProductCode;
import com.system.education.infirmary.product.core.payload.ProductPayload;
import com.system.education.infirmary.product.query.api.repository.ProductRepositories;
import com.system.education.infirmary.product.query.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductPayloadImpl implements ProductPayload {
    private final ProductRepository productRepository;
    private final ProductRepositories productRepositories;

    /**
     * This method generates a unique code for the product.
     * It first checks if there are any existing products in the repository.
     * If there are no products, it returns a default code "ESPC10000001".
     * If there are existing products, it retrieves the last product's code and generates a new code based on it.
     *
     * @return A Mono containing the generated product code.
     */
    @Override
    public Mono<String> getCode(){
        return productRepository.count().flatMap(aLong -> {
            if (aLong == 0) {
                return Mono.just("ESIPC10000001");
            }
            else {
                Mono<String> code = productRepositories.getLastProduct()
                        .flatMap(product -> Mono.just(product.getProductCode()));
                return ProductCode.generate(code);
            }
        });
    }

    /**
     * This method checks if a product with the given code exists in the repository.
     * It uses the productRepository to check for existence.
     *
     * @param productCode The product code of the product to check.
     * @return A Mono containing true if the product exists, false otherwise.
     */
    @Override
    public Mono<Boolean> isProductCodeExist(String productCode) {
        return productRepository.existsByProductCode(productCode);
    }
}
