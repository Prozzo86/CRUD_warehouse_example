package algebra.spring_boot.article;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Article> fetchAll(){
        return jdbcTemplate.query("SELECT a.id, a.name, a.description, a.price, a.categoryId, c.name AS categoryName, c.description AS categoryDescription FROM Article a LEFT JOIN Category c ON a.categoryId = c.id", new ArticleRowMapper());
    }

    public Optional<Article> findById(Integer id){
        String query = "SELECT a.id, a.name, a.description, a.price, a.categoryId, c.name AS categoryName, c.description AS categoryDescription FROM Article a LEFT JOIN Category c ON a.categoryId = c.id WHERE a.id = ?";

        return Optional.of(jdbcTemplate.queryForObject(query, new ArticleRowMapper(), id));
    }

    public Article create(Article article){
        String query = "INSERT INTO Article (name, description, price, categoryId) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, article.getName(), article.getDescription(), article.getPrice(), article.getCategory().getId());
        return article;
    }

    public Article update (Article article){
        String query = "UPDATE Article SET name = ?,  description = ?,  price = ?,  categoryId = ? WHERE id = ?";
        jdbcTemplate.update(query, article.getName(), article.getDescription(), article.getPrice(), article.getCategory().getId(), article.getId());
        return article;
    }
}
