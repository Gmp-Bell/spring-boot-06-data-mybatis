package com.atguigu.springboot.service;

import com.atguigu.springboot.bean.Department;
import com.atguigu.springboot.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author GMP
 * @create 2020-05-06 15:57
 */
@Service
public class DeptService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    public Department getDeptById(Integer id){
        String key = "user_" + id;

        ValueOperations<String, Department> operations = redisTemplate.opsForValue();

        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            Department department = operations.get(key);
            System.out.println("==========从缓存中获得数据=========");
            System.out.println(department.getDepartmentName());
            System.out.println("==============================");
            return department;
        } else {
            Department department = departmentMapper.getDeptById(id);
            System.out.println("==========从数据表中获得数据=========");
            System.out.println(department.getDepartmentName());
            System.out.println("==============================");

            // 写入缓存
            operations.set(key, department, 5, TimeUnit.HOURS);
            return department;
        }

    }

    public int updateUser(Department department) {
        ValueOperations<String, Department> operations = redisTemplate.opsForValue();
        int result = departmentMapper.updateDept(department);
        if (result != 0) {
            String key = "user_" + department.getId();
            boolean haskey = redisTemplate.hasKey(key);
            if (haskey) {
                redisTemplate.delete(key);
                System.out.println("删除缓存中的key=========>" + key);
            }
            // 再将更新后的数据加入缓存
            Department department1 = departmentMapper.getDeptById(department.getId());
                if (department1 != null) {
                operations.set(key, department1, 3, TimeUnit.HOURS);
            }
        }
        return result;
    }

    public int deleteDeptById(int id) {
        int result = departmentMapper.deleteDeptById(id);
        String key = "user_" + id;
        if (result != 0) {
            boolean hasKey = redisTemplate.hasKey(key);
            if (hasKey) {
                redisTemplate.delete(key);
                System.out.println("删除了缓存中的key:" + key);
            }
        }
        return result;
    }



}
