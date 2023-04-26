package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //Для обозначения POJO для Hibernate
//По умолчанию Hibernate берет в качестве названия таблицы имя класса и для преодоления конфликтов можно вручную задать имя таблицы
@Table(name = "Users", schema = "public")
public class User {

    @Id //Обязательная анотация для Hibernate
    private String username;
    private String firstName;
    private String lastName;
    //По умолчанию Hibernate берет в качестве названия артибута имя переменной и для преодоления конфликтов можно вручную задать имя атрибута в БД
    @Column(name = "birth_Date")
    private LocalDate birthDate;
    private int age;
    //Благодаря этой аннотации мы можем определить что записывать в базу данны
    //@param EnumType.ORDINAL - записывает номер значения по порядку (не рекомендуется)
    //@param EnumType.STRING - записывает конкретное значения атрибута
    @Enumerated(EnumType.STRING)
    private Role role;
}
