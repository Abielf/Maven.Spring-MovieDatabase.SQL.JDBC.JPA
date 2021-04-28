package io.zipcoder.persistenceapp;

import io.zipcoder.persistenceapp.DAO.DAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PersonService implements DAO<Person> {



    private static final Logger log = LoggerFactory.getLogger(PersonService.class);

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Person> rowMapper= (rs, rowNum) ->{
        Person person = new Person();
        person.setId(rs.getInt("ID"));
        person.setFirstName(rs.getString("FIRST_NAME"));
        person.setLastName(rs.getString("LAST_NAME"));
        person.setMobile(rs.getString("MOBILE"));
        person.setBirthday(rs.getDate("BIRTHDAY"));
        person.setHomeId(rs.getInt("HOME_ID"));
        return person;
    };

    @Autowired
    protected PersonService(JdbcTemplate j){
        this.jdbcTemplate=j;
    }


    public List<Person> list() {
        String sql = "SELECT ID, FIRST_NAME, LAST_NAME, MOBILE, BIRTHDAY, HOME_ID FROM PERSON";
        return jdbcTemplate.query(sql,rowMapper);
    }

    public List<Person> listByHome(int homeID) {
        String sql = "SELECT ID, FIRST_NAME, LAST_NAME, MOBILE, BIRTHDAY, HOME_ID FROM PERSON WHERE HOME_ID=?";
        return jdbcTemplate.query(sql,new Object[]{homeID},rowMapper);
    }


    public void create(Person person) {
        String sql="INSERT INTO PERSON(FIRST_NAME, LAST_NAME, MOBILE, BIRTHDAY, HOME_ID) VALUES(?,?,?,?,?)";
        int insert= jdbcTemplate.update(sql,person.getFirstName(), person.getLastName(), person.getMobile(), person.getBirthday(), person.getHomeId());
        if(insert==1){log.info("New person added to DB: "+person.getFirstName()+" "+person.getLastName());}
    }


    public Optional<Person> get(int id) {
        String sql="SELECT ID, FIRST_NAME, LAST_NAME, MOBILE, BIRTHDAY, HOME_ID FROM PERSON WHERE ID=?";
        Person person = null;
        try{
            person=jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
        }catch(DataAccessException e){log.info("Person not found");}
        return Optional.ofNullable(person);
    }

    public List<Person> phoneLookup(String mobile){
        String sql = "SELECT ID, FIRST_NAME, LAST_NAME, MOBILE, BIRTHDAY, HOME_ID FROM PERSON WHERE MOBILE=?";
        return jdbcTemplate.query(sql, new Object[] {mobile}, rowMapper);
    }

    public List<Person> lastLookup(String last){
        String sql = "SELECT ID, FIRST_NAME, LAST_NAME, MOBILE, BIRTHDAY, HOME_ID FROM PERSON WHERE LAST_NAME=?";
        return jdbcTemplate.query(sql, new Object[] {last}, rowMapper);
    }



    public void update(Person person, int id) {
        String sql="UPDATE PERSON SET FIRST_NAME=?, LAST_NAME=?, MOBILE=?, BIRTHDAY=?, HOME_ID=? WHERE ID=?";
        int update= jdbcTemplate.update(sql, person.getFirstName(),person.getLastName(), person.getMobile(), person.getBirthday(),person.getHomeId(),id);
        if(update==1){log.info("Person Updated");}
    }

    public Map<String, Long> firstNameFrequencies(){
        String sql="SELECT FIRST_NAME, COUNT(FIRST_NAME) AS FREQUENCY FROM PERSON GROUP BY FIRST_NAME";
        List<Map<String,Object>> counts=jdbcTemplate.queryForList(sql);
        Map<String, Long> returnThis= new HashMap<>();
        counts.forEach( rowMap ->{
            String first=(String) rowMap.get("FIRST_NAME");
            Long c=(Long) rowMap.get("FREQUENCY");
            returnThis.put(first,c);
        });
        return returnThis;
    }



    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM PERSON WHERE ID=?",id);
    }
}