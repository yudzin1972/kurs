package org.example.DAO;

import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //private static int id;
    // private List<Person> people;
 /*   private static final String URL="jdbc:mysql://localhost:3306/kurs";
    private static final String USERNAME="root";
    private static final String PASSWORD="root";
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    {
//        people = new ArrayList<>();
//        people.add(new Person(++id, "Иван",20,"y11@tut.by"));
//        people.add(new Person(++id, "Сидор", 40,"fgh@rambler.ru"));
//        people.add(new Person(++id, "Петр",26,"fgvc@mail.ru"));
//        people.add(new Person(++id, "Петя",33,"qazxs@gmail.ru"));
//        people.add(new Person(++id, "Саша",46,"gbvcfde@yandex.com"));
    }
*/
    public List<Person> index() {
 /*       List<Person> people=new ArrayList<>();
        try {
            Statement statement=connection.createStatement();
            String sql="SELECT * FROM person";
            ResultSet resultSet=statement.executeQuery(sql);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int colCount = rsmd.getColumnCount();
            while(resultSet.next()){
                Person person=new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));
                people.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;*/
        return jdbcTemplate.query("SELECT * From person", new PersonMapper());
    }

    public Person show(int id) {
   /*     Person person=null;
      //  return people.stream().filter(person -> person.getId()==id).findAny().orElse(null);
        try {
            PreparedStatement preparedStatement=
                    connection.prepareStatement("SELECT * FROM person WHERE id=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            resultSet.next();
            person=new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;*/
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
/*//        person.setId(++id);
//        people.add(person);
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO person (name,age,email) VALUES(?,?,?)");
//            Statement statement = connection.createStatement();
//            String SQL = "INSERT INTO Person (name,age,email)"+ "VALUES('" + person.getName() +
//                    "'," + person.getAge() + ",'" + person.getEmail() + "')";
//
//            statement.executeUpdate(SQL);
            preparedStatement.setString(1,person.getName());
            preparedStatement.setInt(2,person.getAge());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
        jdbcTemplate.update("INSERT INTO Person (name,age,email,context) VALUES(?,?,?,?)",
                person.getName(),
                person.getAge(),
                person.getEmail(),
                person.getContext());
    }

    public void update(int id, Person updatePerson) {
  /*      try {
            PreparedStatement preparedStatement=
                    connection.prepareStatement("UPDATE person SET name=?, age=?, email=? WHERE id=?");
            preparedStatement.setString(1,updatePerson.getName());
            preparedStatement.setInt(2,updatePerson.getAge());
            preparedStatement.setString(3,updatePerson.getEmail());
            preparedStatement.setInt(4,updatePerson.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        Person personForUpdate=show(id);
//        personForUpdate.setName(updatePerson.getName());
//        personForUpdate.setAge(updatePerson.getAge());
//        personForUpdate.setEmail(updatePerson.getEmail());*/
        jdbcTemplate.update("UPDATE person SET name=?, age=?, email=?, context=? WHERE id=?",
                updatePerson.getName(),
                updatePerson.getAge(),
                updatePerson.getEmail(),
                updatePerson.getContext(),
                id);
    }

    public void delete(int id) {
   /*     try {
            PreparedStatement preparedStatement=
                    connection.prepareStatement("DELETE FROM person WHERE id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //   people.removeIf(p -> p.getId() == id);*/
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }
}
