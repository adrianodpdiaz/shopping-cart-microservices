package com.adpd.product.mapping;

import com.adpd.product.entity.Product;
import com.adpd.feignclients.resource.dto.ProductDTO;
import com.adpd.product.resource.form.RegisterProductForm;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDTO(Product product);

    Product requestToEntity(RegisterProductForm form);

    List<ProductDTO> toDTOList(List<Product> products);

}
