package ioc.lab;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 簡易 IoC Container 實作
 * <p>
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


    List<BeanDefinition> unresolved = new ArrayList<>();

    private void createdManagedComponents() {
        // 實作掃瞄 @Component 的類別，並建立相關物件
        // reflection api
        // class constructor, parameter,
        annotationScanner.definitions.values().stream().forEach(beanDefinition -> {
            if (beanDefinition.getDependsOn() == null) {
                Constructor[] constructors = beanDefinition.getBeanClass().getConstructors();
                if (constructors[0].getParameterCount() == 0) {
                    try {
                        container.put(beanDefinition.getName(), constructors[0].newInstance());
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } else {
                    unresolved.add(beanDefinition);
                }
            }
        });

        resolve();

    }

    private void resolve() {
        List<BeanDefinition> stillUnresolved = new ArrayList<>();
        unresolved.stream().forEach(beanDefinition -> {
            Constructor[] constructors = beanDefinition.getBeanClass().getConstructors();
            Class[] parameterTypes = constructors[0].getParameterTypes();
            List<Object> parameters = Arrays.stream(parameterTypes).map(
                    c -> {
                        String name = annotationScanner.toBeanName(c);
                        Object o = container.get(name);
                        if (o == null) {
                            System.out.println("Cannot resolve bean by name: " + name);
                            stillUnresolved.add(beanDefinition);
                        }
                        return o;
                    }
            ).collect(Collectors.toList());

            try {
                if (!stillUnresolved.contains(beanDefinition)) {
                    container.put(beanDefinition.getName(), constructors[0].newInstance(parameters.toArray()));
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });

        unresolved = unresolved.stream().filter(b -> stillUnresolved.contains(b)).collect(Collectors.toList());
    }

    private void createManagedBeans() {
        // 實作掃瞄 @Bean 的方法，並呼叫它建立相關物件
        annotationScanner.definitions.values().stream().forEach(beanDefinition -> {
            if (beanDefinition.getDependsOn() != null) {
                // 有 dependsOn 的是 factory method
                Object o = container.get(beanDefinition.getDependsOn().getName());
                try {
                    Method method = o.getClass().getMethod(beanDefinition.getName());
                    Object bean = method.invoke(o);
                    container.put(beanDefinition.getName(), bean);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });
        resolve();
    }

    /**
     * @param name 實作以 bean 的名稱取得 managed bean
     * @return
     */
    public <T> T getBean(String name) {
        return (T) container.get(name);
    }

    /**
     * @param clazz 實作以 bean 的類別取得 managed bean
     * @return
     */
    public <T> T getBean(Class<T> clazz) {
        return (T) container.get(annotationScanner.toBeanName(clazz));
    }

}
