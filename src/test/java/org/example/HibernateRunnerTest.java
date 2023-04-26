package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import junit.framework.TestCase;
import org.example.entity.User;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class HibernateRunnerTest extends TestCase {
    /***
     * Данный класс показывает что происходит "под капотом" у Hibernate
     * и как происходит мапинг полей entity класса и sql запроса
     * при помощи Reflection API
     */
    @Test
    void checkReflectionApi(){
        User user = User.builder()
                .username("ivan_govno@grob.ru")
                .firstName("Ivan")
                .lastName("Govnov")
                .birthDate(LocalDate.of(1999, 9, 2))
                .age(23)
                .build();

        String sql = """
                insert
                into
                %s
                (%s)
                values
                (%s)
                """;

        String tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
                .map(tableAnnotation -> tableAnnotation.schema() + "." + tableAnnotation.name())
                .orElse(user.getClass().getName());

        Field[] declaredFields = user.getClass().getDeclaredFields();

        String columnNames = Arrays.stream(declaredFields)
                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
                        .map(Column::name)
                        .orElse(field.getName()))
                .collect(Collectors.joining(", "));

        String columnValues = Arrays.stream(declaredFields)
                .map(field -> "?")
                .collect(Collectors.joining(", "));

        System.out.println(sql.formatted(tableName, columnNames, columnValues));

    }

}