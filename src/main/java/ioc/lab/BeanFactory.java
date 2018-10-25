package ioc.lab;

public class BeanFactory {

    public BeanFactory(String basePackage) {
        scanComponents();
        invokeFactoryMethods();
    }

    public void scanComponents() {

    }

    public void invokeFactoryMethods() {

    }

    public <T> T getBean(String name) {
        return null;
    }

    public <T> T getBean(Class<T> clazz) {
        return null;
    }

}
