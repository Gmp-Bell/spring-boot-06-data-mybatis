package com.atguigu.springboot.controller;

import com.atguigu.springboot.bean.Department;
import com.atguigu.springboot.mapper.DepartmentMapper;
import com.atguigu.springboot.service.DeptService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GMP
 * @create 2020-04-30 18:57
 */

@MapperScan(value = "com.atguigu.springboot.mapper")
@RestController
public class DeptController {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    private DeptService deptService;

    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable("id") Integer id){
        Department department =  deptService.getDeptById(id);

        Map<String, Object> result = new HashMap<>();
        result.put("id", department.getId());
        result.put("name", department.getDepartmentName());

        return department;
    }

    @GetMapping("/dept")
    public Department insertDept(Department department){

        departmentMapper.insertDept(department);
        return department;
    }

    @GetMapping("/delete/{id}")
    public String deleteDeptById(@PathVariable("id") Integer id){
        deptService.deleteDeptById(id);
        int result = deptService.deleteDeptById(id);
        if(result == 0){
            return "delete fail";
        }
        return "delete success";
    }

    @GetMapping("/update")
    public String updateDept(Department department){

        departmentMapper.updateDept(department);
        return "success";
    }



}
