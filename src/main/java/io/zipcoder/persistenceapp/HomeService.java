package io.zipcoder.persistenceapp;

import io.zipcoder.persistenceapp.DAO.DAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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

    public Optional<Home> get(int id) {
        String sql="SELECT ID, ADDRESS, HOMENUMBER FROM HOME WHERE ID=?";
        Home home = null;
        try{
            home=jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
        }catch(DataAccessException e){log.info("Home not found");}
        return Optional.ofNullable(home);
    }

    public Optional<Home> getByHomeNumber(String num) {
        String sql="SELECT ID, ADDRESS, HOMENUMBER FROM HOME WHERE HOMENUMBER=?";
        Home home = null;
        try{
            home=jdbcTemplate.queryForObject(sql, new Object[]{num}, rowMapper);
        }catch(DataAccessException e){log.info("Person not found");}
        return Optional.ofNullable(home);
    }

    public Optional<Home> getByAddress(String address) {
        String sql="SELECT ID, ADDRESS, HOMENUMBER FROM HOME WHERE HOMENUMBER=?";
        Home home = null;
        try{
            home=jdbcTemplate.queryForObject(sql, new Object[]{address}, rowMapper);
        }catch(DataAccessException e){log.info("Person not found");}
        return Optional.ofNullable(home);
    }

    public Optional<Home> getByPersonId(int id) {
        String sql="SELECT HOME.ID, HOME.ADDRESS, HOME.HOMENUMBER FROM HOME INNER JOIN PERSON ON PERSON.HOME_ID=HOME.ID WHERE PERSON.ID=?";
        Home home = null;
        try{
            home=jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
        }catch(DataAccessException e){log.info("Person not found");}
        return Optional.ofNullable(home);
    }



    @Override
    public void create(Home home) {
        String sql="INSERT INTO HOME (ADDRESS,HOMENUMBER) VALUES (?,?)";
        int insert= jdbcTemplate.update(sql, home.getAddress(), home.getHomeNumber() );
                if(insert==1){log.info("Home with address:"+home.getAddress()+" Added to database.");}
    }


    @Override
    public void update(Home home, int id) {
        String sql="UPDATE HOME SET ADDRESS=?, HOMENUMBER=? WHERE ID=?";
        int update= jdbcTemplate.update(sql, home.getAddress(),home.getHomeNumber() ,id);
        if(update==1){log.info("Home Updated");}
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM HOME WHERE ID=?",id);
    }
}
/*
Add a person to a home
Remove a list of homes from the database
Find a home by address
*/