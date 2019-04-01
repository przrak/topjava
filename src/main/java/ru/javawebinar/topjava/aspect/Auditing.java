package ru.javawebinar.topjava.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
public class Auditing
{
    /*
    Произвольный класс или пакет:
    y execution(void transfer*(String)) — произвольный метод, начинающийся
    с transfer, с одним параметром типа String и возвращаемым типом void;
    y execution(* transfer(*)) — произвольный метод с названием transfer, принимающий на входе один параметр;
    y execution(* transfer(int, ..)) — произвольный метод с названием transfer,
    первый параметр которого имеет тип int (здесь .. означает, что за ним может следовать несколько других параметров или ни одного).
     Ограничение по классу: execution(void com.packt.patterninspring.chapter6.bankapp.service.TransferServiceImpl.*(..)) — произвольный метод из
    класса TransferServiceImpl, возвращающий void, включая любой подкласс, но
    не в случае использования другой реализации.
     Ограничение по интерфейсу: execution(void com.packt.patterninspring.chapter6.bankapp.service.TransferService.transfer(*)) — произвольный метод
    с названием transfer(), принимающий один аргумент и возвращающий void из
    любого реализующего интерфейс TransferService объекта. Это вариант, обеспечивающий большую гибкость — он работает даже при изменении реализации.
     Использование аннотаций: execution(@javax.annotation.security.RolesAllowedvoid transfer*(..)) — произвольный возвращающий void метод, название
    которого начинается с transfer. Аннотирован @RolesAllowed.
     Работа с пакетами:
    y execution(* com..bankapp.*.*(..)): com и bankapp может отделять один
    каталог;
    y execution(* com.*.bankapp.*.*(..)): com и bankapp может разделять несколько каталогов;
    y execution(* *..bankapp.*.*(..)): произвольный подпакет с названием bankapp.
     */
//    @Before("execution(* ru.javawebinar.topjava.service.UserServiceImpl.getAll())")
    @Before("execution(* ru.javawebinar.topjava.service.UserService.*(..))")
    public void validate()
    {
        System.out.println("Тестирование перед выполнением запроса");
    }
}
