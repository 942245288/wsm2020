package com.bigdata.sys.utils;

import com.bigdata.sys.domain.User;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ActiverUser implements Serializable {

    private User user;
    private List<String> roles;
    private List<String> permissions;


}
