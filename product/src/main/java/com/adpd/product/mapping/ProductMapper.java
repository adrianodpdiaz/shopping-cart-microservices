package com.adpd.product.mapping;

import com.adpd.product.entity.Product;
import com.adpd.product.resource.ProductDTO;
import com.adpd.product.resource.RegisterProductRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDTO(Product customer);

    Product requestToEntity(RegisterProductRequest request);

}
