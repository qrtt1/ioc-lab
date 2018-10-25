package example;

import ioc.lab.annotation.Autowired;
import ioc.lab.annotation.Component;

@Component
public class BusinessObject {

    final private IDeviceWriter writer;

    @Autowired
    public BusinessObject(IDeviceWriter writer) {
        this.writer = writer;
    }

    public String save() {
        return writer.saveToDevice();
    }
}