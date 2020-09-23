package com.schoolerp.indexpage;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CRUD.ClassesRepository;
import com.CRUD.dynamocrud.DynamicKeyCrud;
import com.config.BeanConfig;
import com.model.Classes;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";

    private final AtomicLong counter = new AtomicLong();
    
    ApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfig.class);
    
//    @Autowired
    private ClassesRepository classesRepository = ctx.getBean(ClassesRepository.class);
    private DynamicKeyCrud dkrep = ctx.getBean(DynamicKeyCrud.class);

    @RequestMapping("/update")
    public void dbInsertCheck() {
    	classesRepository.setSectionForClass("LKG", "HI");
    }
    
    @RequestMapping("/generateotp")
    public void generateOtp() {
    	dkrep.generateDpin();
    }
    
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
//    	throw new RuntimeException("Some Exception");
//    	Optional<Classes> dbRecord = classRepo.findById("LKG");
    	Classes rec = classesRepository.getSectionsForClass("LKG");
    	classesRepository.setSectionForClass("LKG", "HI");
        return new Greeting(counter.incrementAndGet(),
            String.format(
//            		template
            		rec.getSection().toString()
            		, name));
    }
    
    @GetMapping(path="/greeting/path/{param}")
    public Greeting greetingPathParam(@PathVariable String param) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, param));
    }
    
    @RequestMapping("/angCal")
    public String angCall() {
    	System.out.println("<<<<<<             JAVA              >>>>>>>>>");
    	return String.format(template, "JAVA Return");
    }
    
}