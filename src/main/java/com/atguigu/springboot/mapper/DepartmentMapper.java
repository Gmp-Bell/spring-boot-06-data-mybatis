package com.atguigu.springboot.mapper;

import com.atguigu.springboot.bean.Department;

/**
 * @author GMP
 * @create 2020-04-30 15:41
 */


public interface DepartmentMapper {
    public Department getDeptById(Integer id);

    public int deleteDeptById(Integer id);

    public void insertDept(Department department);

    public int updateDept(Department department);

}
