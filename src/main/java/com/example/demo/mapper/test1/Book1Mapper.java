package com.example.demo.mapper.test1;

import com.example.demo.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Book1Mapper {
    List<Book> getAll();
}