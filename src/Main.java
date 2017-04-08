package src;

import View.Buchhandlung;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //src.HSQLDBManager.instance.startup();
        //src.HSQLDBManager.instance.insert();
        //src.HSQLDBManager.instance.shutdown();
        Buchhandlung buchhandlung = new Buchhandlung();

        System.out.println(Configuration.instance.userDirectory);
    }
}
