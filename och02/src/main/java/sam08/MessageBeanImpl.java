package sam08;

public class MessageBeanImpl implements MessageBean {
	private String    name;
	private String    greet;
	private Outputter output;

	public void setName(String name) {
		this.name = name;
	}
	public void setGreet(String greet) {
		this.greet = greet;
	}
	public void setOutput(Outputter output) {
		this.output = output;
	}
	public void sayHello() {
		String msg = name+"님 !! "+greet;
		System.out.println(msg);
		if(output !=null) output.output(msg);
	}

}
