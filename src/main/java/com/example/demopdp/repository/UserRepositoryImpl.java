package com.example.demopdp.repository;

import com.example.demopdp.controller.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(User user) {
        return jdbcTemplate.update("INSERT INTO end_user (common_name, legal_name) VALUES(?,?)",
                new Object[] { user.getCommon_name(), user.getLegal_name()});
    }

    @Override
    public int update(User user) {
        return jdbcTemplate.update("UPDATE end_user SET common_name=?, legal_name=? WHERE id=?",
                new Object[] { user.getCommon_name(), user.getLegal_name(), user.getId() });
    }

    @Override
    public User findById(Long id) {
        try {
            User user = jdbcTemplate.queryForObject("SELECT * FROM end_user WHERE id=?",
                    BeanPropertyRowMapper.newInstance(User.class), id);

            return user;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("delete from end_user_organisation_association where end_user_id=?;delete from end_user_grinder_association where end_user_id=?;DELETE FROM end_user WHERE id=?;", id,id,id);
    }

    @Override
    public List<User> findAll() {
        System.out.println(jdbcTemplate.query("SELECT * from end_user", BeanPropertyRowMapper.newInstance(User.class)));
        return jdbcTemplate.query("SELECT * from end_user", BeanPropertyRowMapper.newInstance(User.class));

    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from end_user");
    }
}
