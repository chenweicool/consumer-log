package com.mylog.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理员
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    /**
     *   用户的id
     */
    private Integer id;

    /**
     *   用户名
     */
    private String userName;

    /**
     *   密码
     */
    private String password;


}