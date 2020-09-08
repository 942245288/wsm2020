package com.bigdata.sys.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultObj {

    private Integer code=200;
    private String msg="";
    public static final ResultObj LOGIN_SUCESS = new ResultObj(200,"登陆成功");
    public static final ResultObj LOGIN_ERROR = new ResultObj(-1,"登陆失败");
    public static final ResultObj DELETE_SUCESS=new ResultObj(200,"删除成功");
    public static final ResultObj DELETE_ERROR=new ResultObj(-1,"删除失败");

    public static final ResultObj ADD_SUCESS=new ResultObj(200,"添加成功");
    public static final ResultObj ADD_ERROR=new ResultObj(-1,"添加失败");
    public ResultObj(String msg) {
        this.msg = msg;
    }
}
