package example;

import ioc.lab.BeanFactory;
import ioc.lab.annotation.Bean;
import ioc.lab.annotation.Component;

@Component
public class AppConfig {

    @Bean
    public IDeviceWriter floopyWriter() {
        return new FloppyWriter();
    }

    @Bean
    public IDeviceWriter usbWriter() {
        return new FloppyWriter();
    }

    public static void main(String[] args) {
        BeanFactory bf = new BeanFactory("example");
        BusinessObject bo = bf.getBean(BusinessObject.class);
        System.out.println(bo.save());
    }
}
