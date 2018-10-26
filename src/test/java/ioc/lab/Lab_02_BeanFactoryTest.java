package ioc.lab;

import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import test.example.FooBar;
import test.example.sub.FooBarbar;

public class Lab_02_BeanFactoryTest {

    @Test
    public void test() {
        // 掃瞄一下 test.example 取得相關的 bean definition
        BeanFactory bf = new BeanFactory("test.example");

        // 驗證是否有抓到 bean
        assertTrue(bf.getBean(FooBar.class) instanceof FooBar);
        assertTrue(bf.getBean(FooBarbar.class) instanceof FooBarbar);

//        我們並不會直接把 factory method 的 return type 註冊為 beanName
//        而是使用它 method name
//        assertEquals("富爸爸", bf.getBean(String.class));
        assertEquals("富爸爸", bf.getBean("message"));

        // 目前的實作會在 IoC Container 內回傳一樣的 instance，也就是 spring 給 bean 的預設 scope: singleton
        // https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-scopes-singleton
        // 它這裡的 singleton 是指在 IoC Container 內確保是這唯一的一份
        // 我們傳統上的 singleton 是指在 Java ClassLoader 內確保有一份
        assertSame(bf.getBean(FooBar.class), bf.getBean("fooBar"));

        // 驗證 富爸 內是否有 富爸爸，並且是跟 managed bean 裡的 富爸爸同 1 份
        FooBar fooBar = bf.getBean(FooBar.class);
        assertSame(fooBar.getFooBarbar(), bf.getBean(FooBarbar.class));
    }

}
