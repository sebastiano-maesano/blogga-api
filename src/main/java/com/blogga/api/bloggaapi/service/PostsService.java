package com.blogga.api.bloggaapi.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import com.blogga.api.bloggaapi.model.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostsService {

    @Autowired
    private EntityManager em;

    public Selection<Comment> getAllPostComments(Long postId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Comment> q = cb.createQuery(Comment.class);
        Root<Comment> commentTable = q.from(Comment.class);

        ParameterExpression<Long> pId = cb.parameter(Long.class);

        q.select(commentTable);
        q.where(cb.equal(commentTable.get("post"), pId));

        return q.getSelection();

    }

}