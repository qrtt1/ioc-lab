package ioc.lab;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import test.example.FooBar;
import test.example.sub.FooBarbar;

public class BeanFactoryTest {

    @Test
    public void test() {
        BeanFactory bf = new BeanFactory("test.example");
        assertTrue(bf.getBean(FooBar.class) instanceof FooBar);
        assertTrue(bf.getBean(FooBarbar.class) instanceof FooBarbar);
        assertEquals("富爸爸", bf.getBean(String.class));
        assertEquals("富爸爸", bf.getBean("message"));
    }

}
