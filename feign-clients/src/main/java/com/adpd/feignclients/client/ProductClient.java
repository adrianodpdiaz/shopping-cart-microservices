package com.adpd.feignclients.client;

import com.adpd.feignclients.config.ErrorDecoderConfig;
import com.adpd.feignclients.resource.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "product", path = "/api/v1/products",
    configuration = ErrorDecoderConfig.class)
public interface ProductClient {

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Long id);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProductDTO>> listProducts();

}
