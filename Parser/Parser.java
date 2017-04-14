package Parser;

import Model.Book;
import Repository.MethodRepository;
import Repository.SoftwareRepository;


public class Parser {
    protected SoftwareRepository softwareRepository = new SoftwareRepository(new Book());
    protected MethodRepository methodRepository;
    private Parser successor;

    public Parser getSuccessor() {
        return successor;
    }

    public MethodRepository parse(String methodName){
        if(getSuccessor() != null){
           return getSuccessor().parse(methodName);
        }
        else
        {
            System.out.println("unable to find the correct parser for the file : " + methodName);
            return null;
        }
    }
    protected boolean canHandelFile(String methodeName,String className){
        return (methodeName == null) || (softwareRepository.getMethod(methodeName,className));
    }

    public void setSuccessor(Parser successor) {
        this.successor = successor;
    }
}
