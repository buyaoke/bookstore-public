package com.lz.bookstore.controller;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lz.bookstore.common.Result;
import com.lz.bookstore.entity.User;
import com.lz.bookstore.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.websocket.server.PathParam;
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

    @Autowired
    private IUserService userService;

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
        List<Book> books = bookService.list();
        for (Book book : books) {
            User user = userService.getById(book.getBussionId());
            book.setBussionName(user.getNickname());

        }
        return Result.success(books);
    }

    @GetMapping("/searchBook")
    public Result findBook(@RequestParam String bookName) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("book_name",bookName);
        List<Book> book = bookService.list(queryWrapper);
        return Result.success(book);
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        Book book = bookService.getById(id);
        return Result.success(book);
    }
    @GetMapping("/type")
    public Result findType(@RequestParam String type) {
        System.out.println(type);
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("book_type",type);

        List<Book> books = bookService.list(queryWrapper);

        return Result.success(books);
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
        List<Book> records = page.getRecords();
        for (Book book : records) {
            User user = userService.getById(book.getBussionId());
            if(user != null)
                book.setBussionName(user.getNickname());
            else {
                book.setBussionName("");
            }

        }
        return Result.success(page);
    }
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {

        boolean flag = bookService.removeBatchByIds(ids);
        return Result.success(flag);
    }

}

