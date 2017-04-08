public class Main {

    public static void main(String[] args) {
	// write your code here
        //HSQLDBManager.instance.startup();
        //HSQLDBManager.instance.insert();
        //HSQLDBManager.instance.shutdown();
        Buchhandlung buchhandlung = new Buchhandlung();
        buchhandlung.show();
        System.out.println(Configuration.instance.userDirectory);
    }
}
