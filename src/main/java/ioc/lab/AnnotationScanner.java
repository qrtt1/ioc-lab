package ioc.lab;

import java.util.HashMap;
import java.util.Map;

public class AnnotationScanner {

    Map<String, BeanDefinition> definitions = new HashMap<>();

    public AnnotationScanner(String basePackage) {
        findAnnotations(basePackage);
    }

    private void findAnnotations(String basePackage) {
        // 參考 reflections 的 library 實作
        // https://github.com/ronmamo/reflections
        // 並填至 map 內
    }

    /**
     * @return 回傳一個 bean 的名稱與 bean definition 的 map
     */
    public Map<String, BeanDefinition> findAnnotatedClasses() {
        // bean 的名稱，若是 factory method 來的，那就是它的 method name
        // 若是 @Component 來的，那就是它的 class name
        return null;
    }
}
