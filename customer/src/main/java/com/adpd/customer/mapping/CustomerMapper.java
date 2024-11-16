package com.adpd.customer.mapping;

import com.adpd.customer.entity.Customer;
import com.adpd.customer.resource.form.RegisterCustomerForm;
import com.adpd.feignclients.resource.dto.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO toDTO(Customer customer);

    Customer requestToEntity(RegisterCustomerForm form);

}
