package ioc.lab;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import test.example.FooBar;
import test.example.sub.FooBarbar;

public class Lab_01_AnnotationScanner {

    AnnotationScanner scanner;

    @Before
    public void setUp() {
        scanner = new AnnotationScanner("test.example");
    }

    @Test
    public void findAnnotations() throws Exception {
        Map<String, BeanDefinition> annotatedClasses = scanner.findAnnotatedClasses();
        assertSame(annotatedClasses.get("fooBar").getBeanClass(), FooBar.class);
        assertSame(annotatedClasses.get("fooBarbar").getBeanClass(), FooBarbar.class);
        assertSame(annotatedClasses.get("message").getBeanClass(), String.class);
    }

}
