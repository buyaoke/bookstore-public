package com.lz.bookstore.mapper;

import com.lz.bookstore.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author buyaoke_lz
 * @since 2023-01-27
 */
public interface CommentMapper extends BaseMapper<Comment> {

    @Select("select c.* ,u.nickname,u.avatar_url from sys_comment c left join sys_user u on c.user_id = u.id where c.book_id = #{id} ")
    List<Comment> findCommentDetail(Integer id);
}
