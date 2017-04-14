package Repository;


import Model.Book;

public class MethodRepository {
    private Class cl;
    private String methodName;

    public MethodRepository(Class cl, String methodName){
        this.cl = cl;
        this.methodName = methodName;
    }

    public void execute(Book book) {
    }
}
