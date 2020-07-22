package com.baoge.service;

import com.baoge.entity.Comment;

import java.util.List;


public interface CommentService {
    List<Comment> listCommentByBlogId(Long blogId);


    int saveComment(Comment comment);

    void deleteComment(Comment comment, Long id);
}
