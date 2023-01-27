package com.lz.bookstore.service;

import com.lz.bookstore.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author buyaoke_lz
 * @since 2023-01-27
 */
public interface ICommentService extends IService<Comment> {

    List<Comment> findCommentDetail(Integer bookID);
}
