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

    // 由於目前的實作，建構子要抓到的 bean name 為 iDeviceWriter，我們修了 method 名稱
    @Bean
    public IDeviceWriter iDeviceWriter() {
        return new UsbDiskWriter();
    }

    public static void main(String[] args) {
        BeanFactory bf = new BeanFactory("example");
        BusinessObject bo = bf.getBean(BusinessObject.class);
        System.out.println(bo.save());
    }
}
