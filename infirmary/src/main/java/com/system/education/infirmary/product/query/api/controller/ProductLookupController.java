package com.system.education.infirmary.product.query.api.controller;

import com.system.education.infirmary.consumed.query.api.dto.AllLookupGenderResponse;
import com.system.education.infirmary.product.query.api.dto.AllLookupProductResponse;
import com.system.education.infirmary.product.query.api.dto.LookupProductResponse;
import com.system.education.infirmary.product.query.api.handler.ProductQueryHandler;
import com.system.education.infirmary.product.query.api.query.ProductByCodeQuery;
import com.system.education.infirmary.product.query.api.query.ProductByIdQuery;
import com.system.education.infirmary.consumed.query.api.response.GenderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/infirmary/product-lookup/")
@Tag(name = "Product", description = "Data REST API for product resource")
public class ProductLookupController {
    private final ProductQueryHandler productQueryHandler;

    /**
     * This method is used to retrieve all products
     * @return the list of products
     */
    @Operation(summary = "Retrieve data products")
    @GetMapping(path = "get-products", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupProductResponse>> show() {
        return productQueryHandler.getProducts()
                .collectList()
                .map(products ->
                        new AllLookupProductResponse(true, products))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a product by ID
     * @param query the ID of the product
     * @return the product with the specified ID
     */
    @Operation(summary = "Retrieve data product by ID")
    @PutMapping(path = "get-product-by-product-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getById(@Valid @RequestBody ProductByIdQuery query) {
        return productQueryHandler.getProductById(query)
                .map(product ->
                        new LookupProductResponse(true, product))
                .map(ResponseEntity::ok);
    }

    /**
     * This method is used to retrieve a product by product code
     * @param query the code of the product
     * @return the product with the specified code
     */
    @Operation(summary = "Retrieve data product by code")
    @PutMapping(path = "get-product-by-product-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<LookupProductResponse>> getByProductCode(@Valid @RequestBody ProductByCodeQuery query) {
        return productQueryHandler.getProductByCode(query)
                .map(product ->
                        new LookupProductResponse(true, product))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest()
                        .body(new LookupProductResponse(false, null))));
    }

    /**
     * This method is used to retrieve a product by establishment code
     * @param query the code of the establishment
     * @return the product with the specified code
     */
    @Operation(summary = "Retrieve data product by establishment")
    @PutMapping(path = "get-product-by-establishment", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> getByEstablishment(@Valid @RequestBody ProductByCodeQuery query) {
        return productQueryHandler.getProductByEstablishment(query)
                .collectList()
                .map(products ->
                        new AllLookupProductResponse(true, products))
                .map(ResponseEntity::ok);
    }


    /**
     * This method is used to retrieve genders for products
     */
    @Operation(summary = "Retrieve data teachers")
    @GetMapping(path = "get-genders", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AllLookupGenderResponse>> genders() {
        List<GenderResponse> genders = new ArrayList<>();
        genders.add(new GenderResponse("HOMME"));
        genders.add(new GenderResponse("FEMME"));
        return Mono.just(ResponseEntity.ok()
                .body(new AllLookupGenderResponse(true, genders)));
    }
}
