package test.example;

import ioc.lab.annotation.Autowired;
import ioc.lab.annotation.Bean;
import ioc.lab.annotation.Component;
import test.example.sub.FooBarbar;

@Component
public class FooBar {

    private final FooBarbar fooBarbar;

    @Autowired
    public FooBar(FooBarbar fooBarbar) {
        this.fooBarbar = fooBarbar;
    }

    public String execute() {
        return fooBarbar.getMessage();
    }

    @Bean
    public String message() {
        return "富爸爸";
    }

}
