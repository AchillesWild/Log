package com.achilles.server.entity;

import com.achilles.model.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends BaseEntity {

    private String userName;

    private String nickName;

    private String password;

    private String email;

    private String mobile;

    private Integer sex;

    private String img;

    private Integer loginTimes;

    private Integer proved;
}