package com.example.demo;

import com.example.demo.entity.Book;
import com.example.demo.mapper.test1.Book1Mapper;
import com.example.demo.mapper.test2.Book2Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    Book1Mapper book1Mapper;

    @Test
    public void test1() {
        List<Book> all = book1Mapper.getAll();
        for (int i = 0; i < all.size(); i++) {
            System.out.println(i + "======" + all.get(i).toString());
        }
    }

    @Autowired
    Book2Mapper book2Mapper;

    @Test
    public void test2() {
        List<Book> all = book2Mapper.getAll();
        for (int i = 0; i < all.size(); i++) {
            System.out.println(i + "======" + all.get(i).toString());
        }
    }
}

