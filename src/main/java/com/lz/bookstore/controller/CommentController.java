package com.lz.bookstore.controller;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lz.bookstore.common.Result;
import com.lz.bookstore.utils.TokenUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import com.lz.bookstore.service.ICommentService;
import com.lz.bookstore.entity.Comment;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author buyaoke_lz
 * @since 2023-01-27
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @PostMapping
    public Result save(@RequestBody Comment comment) {
        if (comment.getUserId() == null){
            comment.setUserId(TokenUtils.getCurrentUser().getId());
            if (comment.getPid() != null){
                Integer pid = comment.getPid();
                Comment pComment = commentService.getById(pid);
                if(pComment.getOrginId() != null){
                    comment.setOrginId(pComment.getOrginId());
                }else {
                    comment.setOrginId(comment.getPid());
                }
            }


        }

        boolean flag = commentService.saveOrUpdate(comment);
        return Result.success(flag);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        boolean flag = commentService.removeById(id);
        return Result.success(flag);
    }

    @GetMapping("/tree/{bookID}")
    public Result findAll(@PathVariable Integer bookID) {
        List<Comment> articleComments = commentService.findCommentDetail(bookID);
        List<Comment> orginList = articleComments.stream().filter(comment -> comment.getOrginId() == null).collect(Collectors.toList());
        for (Comment origin : orginList) {
            List<Comment> comments = articleComments.stream().filter(comment -> origin.getId().equals(comment.getOrginId())).collect(Collectors.toList());
            comments.forEach(comment -> {
                Optional<Comment> pComment = articleComments.stream().filter(c1 -> c1.getId().equals(comment.getPid())).findFirst();
                pComment.ifPresent((v -> {
                    comment.setPUserId(v.getUserId());
                    comment.setPNickname(v.getNickname());
                }));
            });
            origin.setChildren(comments);
        }
        return Result.success(orginList);
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        Comment comment = commentService.getById(id);
        return Result.success(comment);
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        IPage<Comment> page = commentService.page(new Page<>(pageNum, pageSize));
        return Result.success(page);
    }

}

