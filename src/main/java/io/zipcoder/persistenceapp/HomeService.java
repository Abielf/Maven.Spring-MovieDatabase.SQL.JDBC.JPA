package io.zipcoder.persistenceapp;

import io.zipcoder.persistenceapp.DAO.DAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public class HomeService implements DAO<Home> {
    private static final Logger log = LoggerFactory.getLogger(HomeService.class);

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Home> rowMapper= (rs, rowNum) ->{
        Home home = new Home();
        home.setId(rs.getInt("ID"));
        home.setAddress(rs.getString("ADDRESS"));
        home.setHomeNumber(rs.getString("HOMENUMBER"));
        return home;
    };

    @Autowired
    protected HomeService(JdbcTemplate j){
        this.jdbcTemplate=j;
    }

    @Override
    public List<Home> list() {
        String sql = "SELECT ID, ADDRESS, HOMENUMBER FROM HOME";
        return jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public void create(Home home) {

    }

    @Override
    public Optional<Home> get(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Home home, int id) {

    }

    @Override
    public void delete(int id) {

    }
}
