package com.springboot.shiro.springbootshiro.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;


}
