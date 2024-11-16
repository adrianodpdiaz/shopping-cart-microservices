package com.adpd.customer.mapping;

import com.adpd.customer.entity.Address;
import com.adpd.customer.resource.form.RegisterAddressForm;
import com.adpd.feignclients.resource.dto.AddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address requestToEntity(RegisterAddressForm form);

    List<AddressDTO> toDTOList(List<Address> products);

}
