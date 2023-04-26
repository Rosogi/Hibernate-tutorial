package org.example;

import org.example.entity.Role;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.time.LocalDate;

public class HibernateRunner {

    public static void main(String[] args) throws SQLException {
        //Создание файла с кофнигурацией
        Configuration configuration = new Configuration();
        //Инициализация конфигурации
        configuration.configure();


        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()){
            //Начало работы с транзакцией
            session.beginTransaction();
            //Использование паттерна builder для инициализации объекта User
            User user = User.builder()
                    .username("ivan_govno@grob.ru")
                    .firstName("Ivan")
                    .lastName("Govnov")
                    .birthDate(LocalDate.of(1999, 9, 2))
                    .age(23)
                    .role(Role.ADMIN)
                    .build();
            //Использование устаревшего метода для сохранения значений в БД
            session.save(user);
            //Нахождение транзакции и отправка её в базу данных
            session.getTransaction().commit();
        }


    }
}
