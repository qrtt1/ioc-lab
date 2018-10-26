package ioc.lab;

public class BeanDefinition {

    private Class<?> beanClass;
    private String name;
    private BeanDefinition dependsOn;

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDependsOn(BeanDefinition dependsOn) {
        this.dependsOn = dependsOn;
    }

    public BeanDefinition getDependsOn() {
        return dependsOn;
    }
}
