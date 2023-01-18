package com.lz.bookstore.service.impl;

import com.lz.bookstore.entity.Book;
import com.lz.bookstore.mapper.BookMapper;
import com.lz.bookstore.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author buyaoke_lz
 * @since 2023-01-18
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

}
