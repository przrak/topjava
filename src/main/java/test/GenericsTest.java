package test;

import java.util.ArrayList;
import java.util.List;

public class GenericsTest<K extends GenericsTest.A> {

    public static void main(String[] args) {
        List<A> paramA = new ArrayList<>();
        List<B> paramB = new ArrayList<>();
        List<C> paramC = new ArrayList<>();
        GenericsTest<A> gtA = new GenericsTest<A>();
        gtA.test1(paramA);
        gtA.test1(paramB);
        gtA.test1(paramB);
        gtA.test2(paramA);
//        gtA.test2(paramB); // error
//        gtA.test2(paramC); // error
        GenericsTest<B> gtB = new GenericsTest<B>();
        gtB.test1(paramA);
        gtB.test1(paramB);
        gtB.test1(paramB);
//        gtB.test2(paramA); // error
        gtB.test2(paramB);
//        gtB.test2(paramC); // error
        GenericsTest<C> gtC = new GenericsTest<C>();
        gtC.test1(paramA);
        gtC.test1(paramB);
        gtC.test1(paramB);
//        gtC.test2(paramA); // error
//        gtC.test2(paramB); // error
        gtC.test2(paramC);
    }

    public void test1(List<? extends A> param){

    }

    public void test2(List<K> param)
    {

    }

    public <K> void test3(List<K> param)
    {

    }

    static class A{}

    static class B extends A{}

    static class C extends B{}
}
