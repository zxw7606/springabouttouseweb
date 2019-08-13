package com.annotation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vo.User;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * @description:
 * @create: 2019-08-12 16:43
 * @author: zxw
 **/
public class UserAnnotationFormatterFactory implements AnnotationFormatterFactory<UserFormat> {


    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    public Set<Class<?>> getFieldTypes() {
        HashSet<Class<?>> set = new HashSet<>();
        set.add(User.class);
        return set;
    }

    @Override
    public Printer<?> getPrinter(UserFormat annotation, Class<?> fieldType) {
        return new UserFormatter(annotation.role());
    }

    @Override
    public Parser<?> getParser(UserFormat annotation, Class<?> fieldType) {
        return new UserFormatter(annotation.role());
    }

    public static class UserFormatter implements Formatter<User> {

        public UserFormatter(UserFormat.ROLE role) {
            if (role == UserFormat.ROLE.STUDENT) {
                System.out.println("用户是学生");
            } else if (role == UserFormat.ROLE.TEACHER) {
                System.out.println("用户是老师");
            } else {
                System.out.println("用户身份未知");
            }
        }

        @Override
        public User parse(String text, Locale locale) throws ParseException {
            try {

                User user = mapper.readValue(text, User.class);
                user.setName(user.getName()+"parse by userFormatter.");
                return user;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public String print(User object, Locale locale) {
            try {
                    object.setName(object.getName() + "\n print by userFormatter.");
                return mapper.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
