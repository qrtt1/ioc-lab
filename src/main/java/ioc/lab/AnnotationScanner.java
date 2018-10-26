package ioc.lab;

import ioc.lab.annotation.Bean;
import ioc.lab.annotation.Component;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AnnotationScanner {

    // businessObject
    // appConfig
    Map<String, BeanDefinition> definitions = new HashMap<>();
    Reflections reflections = null;

    public AnnotationScanner(String basePackage) {
        findAnnotations(basePackage);
    }

    private void findAnnotations(String basePackage) {
        // 參考 reflections 的 library 實作
        // https://github.com/ronmamo/reflections
        // 並填至 map 內

        // 建立 reflections 加入需要用到的 scanner
        reflections = new Reflections(basePackage,
                new MethodAnnotationsScanner(),
                new TypeAnnotationsScanner(),
                new SubTypesScanner());

        for (Class<?> clazz : reflections.getTypesAnnotatedWith(Component.class)) {
            System.out.println("found: " + clazz);
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setBeanClass(clazz);
            beanDefinition.setName(toBeanName(clazz));
            definitions.put(beanDefinition.getName(), beanDefinition);
        }

        for (Method method : reflections.getMethodsAnnotatedWith(Bean.class)) {
            Class<?> clazz = method.getDeclaringClass();
            if (clazz.getAnnotation(Component.class) != null) {
                BeanDefinition beanDefinition = new BeanDefinition();
                beanDefinition.setBeanClass(method.getReturnType());
                beanDefinition.setName(method.getName());
                beanDefinition.setDependsOn(definitions.get(toBeanName(clazz)));
                definitions.put(beanDefinition.getName(), beanDefinition);
            }
        }

    }

    public String toBeanName(Class<?> clazz) {
        StringBuilder sb = new StringBuilder(clazz.getSimpleName());
        sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
        return sb.toString();
    }

    /**
     * @return 回傳一個 bean 的名稱與 bean definition 的 map
     */
    public Map<String, BeanDefinition> findAnnotatedClasses() {
        // bean 的名稱，若是 factory method 來的，那就是它的 method name
        // 若是 @Component 來的，那就是它的 class name
        return definitions;
    }

    public static void main(String[] args) {

        Reflections reflections = new Reflections("example",
                new MethodAnnotationsScanner(), new TypeAnnotationsScanner(),
                new SubTypesScanner());

        System.out.println(reflections.getTypesAnnotatedWith(Component.class));


    }
}
