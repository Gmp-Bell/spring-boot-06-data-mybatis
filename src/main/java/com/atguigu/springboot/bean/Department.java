package com.atguigu.springboot.bean;

import java.io.Serializable;

/**
 * @author GMP
 * @create 2020-04-30 15:37
 */
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String departmentName;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }
}
