import data.HSQLDBManager;

public class Main {

    public static void main(String[] args) {
	// write your code here
        HSQLDBManager.instance.startup();
        HSQLDBManager.instance.insert();
        HSQLDBManager.instance.shutdown();
    }
}
