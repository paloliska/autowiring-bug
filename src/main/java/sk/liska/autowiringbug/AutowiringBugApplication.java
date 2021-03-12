package sk.liska.autowiringbug;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import sk.liska.autowiringbug.data.DataChild;
import sk.liska.autowiringbug.service.DependingServiceNotWorking;
import sk.liska.autowiringbug.service.DependingServiceWorking;

@Configuration
@ComponentScan(basePackages = "sk.liska")
public class AutowiringBugApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutowiringBugApplication.class);

        DependingServiceWorking o = new DependingServiceWorking();
        applicationContext.getBeanFactory().autowireBean(o);
        o.doWork(new DataChild("Working"));

        DependingServiceNotWorking<DataChild> o2 = new DependingServiceNotWorking<>();
        applicationContext.getBeanFactory().autowireBean(o2);
        o2.doWork(new DataChild("not Working"));
    }
}