package com.lz.bookstore.controller;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lz.bookstore.common.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.lz.bookstore.service.IBookService;
import com.lz.bookstore.entity.Book;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author buyaoke_lz
 * @since 2023-01-18
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private IBookService bookService;

    @PostMapping
    public Result save(@RequestBody Book book) {
        boolean flag = bookService.saveOrUpdate(book);
        return Result.success(flag);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        boolean flag = bookService.removeById(id);
        return Result.success(flag);
    }

    @GetMapping
    public Result findAll() {
        List<Book> Books = bookService.list();
        return Result.success(Books);
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        Book book = bookService.getById(id);
        return Result.success(book);
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String bookName,
                           @RequestParam(defaultValue = "") String author) {
        QueryWrapper<Book> query = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(bookName)){
            query.like("book_name",bookName);
        }
        if (StrUtil.isNotEmpty(author)){
            query.like("book_author",author);
        }
        IPage<Book> page = bookService.page(new Page<>(pageNum, pageSize),query);
        return Result.success(page);
    }
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {

        boolean flag = bookService.removeBatchByIds(ids);
        return Result.success(flag);
    }

}

