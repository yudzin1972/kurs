package org.example.models;


import javax.validation.constraints.*;

public class Person {
    private int id;
    @NotEmpty(message = "Имя не может быть пустым!")
    @Size(min=2, max=30, message = "Имя должно быть от 2 до 30 символов")
    private String name;
    @Min(value = 0,message = "Возраст должен быть больше 0")
    @Max(value = 140)
    private int age;
    @NotEmpty(message = "Email не может быть пустым!")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",message = "Email должен быть валидным")
    private String email;

    public Person(int id, String name, int age, String email, String context) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.context = context;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    private String context;

    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
