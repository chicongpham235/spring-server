package com.map_properties.spring_server.dto;

import com.map_properties.spring_server.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String uuid;
    private String email;
    private String avatar_url;

    public UserDTO(User user) {
        this.uuid = user.getUuid().toString();
        this.email = user.getEmail();
        this.avatar_url = user.getAvatarUrl();
    }
}
