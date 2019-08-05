package com.segware.postings.repository.impl;

import com.segware.postings.controller.PostingsController;
import com.segware.postings.model.Post;
import com.segware.postings.repository.PostingsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class PostingRepositoryImpl implements PostingsRepository {

    public static final Logger logger = LoggerFactory.getLogger(PostingsController.class);

    final String SELECT_QUERY = "SELECT id, title, comments, likes, unlikes FROM TBL_POST;";
    final String SELECT_BY_ID_QUERY = "SELECT id, title, comments, likes, unlikes FROM TBL_POST WHERE id = ? ;";
    final String INSERT_QUERY = "INSERT INTO TBL_POST (title, comments, likes, unlikes) VALUES (?, ?, ?, ?)";
    final String UPDATE_QUERY = " UPDATE TBL_POST\n" +
            "        SET  title = ?,\n" +
            "                 comments = ?, \n" +
            "                 likes = ?, \n" +
            "                 unlikes = ?\n" +
            "WHERE id = ?;";
    final String DELETE_QUERY = "DELETE FROM TBL_POST WHERE ID = ?;";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PostingRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Post> getAll() {
        List<Post> postings = null;
        try {
            postings = jdbcTemplate.query(SELECT_QUERY, new BeanPropertyRowMapper<>(Post.class));
        } catch (Exception exp) {
            logger.error("Error finding all postings : " + exp.getMessage());
        }
        return postings;
    }

    @Override
    public Post getById(Long postId) {
        Post post = null;
        try {
            post = jdbcTemplate.queryForObject(SELECT_BY_ID_QUERY, new PostMapper(), postId.intValue());
        } catch (Exception exp) {
            logger.error("Error finding all postings : " + exp.getMessage());
        }
        return post;
    }

    @Override
    public int create(Post post) {

        int postInsert = 0;
        try {
            String insertSql = INSERT_QUERY;
            // define query arguments
            Object[] params = {post.getTitle(), post.getComments(), post.getLikes(), post.getUnlikes()};

            postInsert = jdbcTemplate.update(insertSql, params);

            System.out.println(postInsert + " row(s) inserted.");

        } catch (Exception exp) {
            logger.error("Error updated post : " + post.getId() + " error: " + exp.getMessage());
        }

        return postInsert;
    }

    @Override
    public int update(Post post) {
        int postUpdate = 0;
        try {
            String updateSql = UPDATE_QUERY;
            // define query arguments
            Object[] params = {post.getTitle(), post.getComments(), post.getLikes(), post.getUnlikes(), post.getId()};

            // define SQL types of the arguments
            int[] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BIGINT};
            postUpdate = jdbcTemplate.update(updateSql, params, types);
            System.out.println(postUpdate + " row(s) updated.");

        } catch (Exception exp) {
            logger.error("Error updated post : " + post.getId() + " error: " + exp.getMessage());
        }

        return postUpdate;
    }

    @Override
    public int delete(Long id) {
        int status = 0;
        try {
            status = jdbcTemplate.update(DELETE_QUERY, id);
            if (status != 0) {
                System.out.println("Post data deleted for ID " + id);
            } else {
                System.out.println("No Post found with ID " + id);
            }
        } catch (Exception exp) {
            logger.error("Error removing post id= " + id);
        }
        return status;
    }

    private static final class PostMapper implements RowMapper<Post> {
        public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
            Post post = new Post();
            post.setId(rs.getInt("id"));
            post.setTitle(rs.getString("title"));
            post.setComments(rs.getString("comments"));
            post.setLikes(rs.getInt("likes"));
            post.setUnlikes(rs.getInt("unlikes"));
            return post;
        }
    }
}
