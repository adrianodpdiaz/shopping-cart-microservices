package com.adpd.feignclients.resource.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "name", "description", "SKU", "category", "price"})
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    @JsonProperty("SKU")
    private String sku;
    private String category;
    private BigDecimal price;

}
