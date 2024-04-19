package com.adpd.customer.mapping;

import com.adpd.customer.entity.Customer;
import com.adpd.customer.resource.CustomerDTO;
import com.adpd.customer.resource.RegisterCustomerRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toDTO(Customer customer);

    Customer requestToEntity(RegisterCustomerRequest request);

}
