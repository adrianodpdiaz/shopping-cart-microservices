package com.adpd.customer.mapping;

import com.adpd.customer.entity.Customer;
import com.adpd.customer.resource.form.RegisterCustomerForm;
import com.adpd.customer.resource.outbound.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDTO toDTO(Customer customer);

    Customer requestToEntity(RegisterCustomerForm form);

}
