package com.example.demo.mapper.test2;

import com.example.demo.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Book2Mapper {
    List<Book> getAll();
}
