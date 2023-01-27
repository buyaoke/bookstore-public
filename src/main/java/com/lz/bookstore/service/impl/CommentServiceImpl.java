package com.lz.bookstore.service.impl;

import com.lz.bookstore.entity.Comment;
import com.lz.bookstore.mapper.CommentMapper;
import com.lz.bookstore.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author buyaoke_lz
 * @since 2023-01-27
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> findCommentDetail(Integer bookID) {
        return commentMapper.findCommentDetail(bookID);
    }
}
