package com.adpd.notification.mapping;

import com.adpd.notification.entity.Notification;
import com.adpd.notification.resource.dto.NotificationDTO;
import com.adpd.notification.resource.form.SendNotificationForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationDTO toDTO(Notification notification);

    Notification requestToEntity(SendNotificationForm inbound);

}
