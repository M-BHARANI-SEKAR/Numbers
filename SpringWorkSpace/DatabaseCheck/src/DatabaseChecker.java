import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.config.BeanConfig;
import com.schoolerp.indexpage.GreetingController;

public class DatabaseChecker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GreetingController gc = new GreetingController();
		gc.dbInsertCheck();
				//new AnnotationConfigApplicationContext(BeanConfig.class);

	}

}
