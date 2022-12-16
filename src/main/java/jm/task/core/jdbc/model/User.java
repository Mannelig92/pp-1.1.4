package jm.task.core.jdbc.model;

import javax.persistence.*;

@Entity
@Table(name = "newUsers") //Можно не писать в скобках если название таблицы совпадает с классом, но лучше писать
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    //Column указывает на то к какому именно столбцу мы привязываем это поле
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "age")
    private Byte age;

    public User() { //Обязатален конструктор без аргументов для Hibernate

    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Пользователь{" +
                "Имя='" + name + '\'' +
                ", Фамилия='" + lastName + '\'' +
                ", Возраст=" + age +
                '}';
    }
}
