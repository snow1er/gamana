package com.jiayu.gamana.security.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Define context for authenticated user for jwt utility.
 * @author Neo.Li
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthContext{

    //userId of the authenticated user
    private UUID userId;

    //name  of the authenticated user
    private String userName;

    //role id of the authenticated user
    private String roleId;
}