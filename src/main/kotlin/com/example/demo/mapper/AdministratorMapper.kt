package com.example.demo.mapper

import com.example.demo.entity.Administrator
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface AdministratorMapper{
    fun findAll(): List<Administrator>

    fun findById(id: Int): Administrator

    fun insert(administrator: Administrator): Int

    fun delete(id: Int): Int
}
