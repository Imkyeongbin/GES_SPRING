package sam07;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ex07 {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/bean07.xml");
		vehicle vh = (vehicle) ac.getBean("vh7");
		vh.ride();
	}

}
