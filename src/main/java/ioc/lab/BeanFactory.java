package ioc.lab;

import java.util.HashMap;
import java.util.Map;

/**
 * 簡易 IoC Container 實作
 * 
 * <li>僅實作 constructor injection
 * <li>支援 @Component 宣告一個類別是 managed bean
 * <li>支援 @Bean 宣告一個方法是 factory method 可以用來產生 managed bean
 * <li>使用 @AutoWired 宣告建立構必需被 injection
 */
public class BeanFactory {

    // container 內放置 managed beans (components)
    final Map<String, Object> container = new HashMap<>();

    final private AnnotationScanner annotationScanner;

    public BeanFactory(String basePackage) {
        this.annotationScanner = new AnnotationScanner(basePackage);
        createdManagedComponents();
        createManagedBeans();
    }

    private void createdManagedComponents() {
        // 實作掃瞄 @Component 的類別，並建立相關物件
    }

    private void createManagedBeans() {
        // 實作掃瞄 @Bean 的方法，並呼叫它建立相關物件
    }

    /**
     * @param name
     *            實作以 bean 的名稱取得 managed bean
     * @return
     */
    public <T> T getBean(String name) {
        return null;
    }

    /**
     * @param clazz
     *            實作以 bean 的類別取得 managed bean
     * @return
     */
    public <T> T getBean(Class<T> clazz) {
        return null;
    }

}
