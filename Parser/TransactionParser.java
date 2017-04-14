package Parser;

import Repository.MethodRepository;


public class TransactionParser extends Parser {
    public TransactionParser(Parser successor) {
        this.setSuccessor(successor);
    }
    public MethodRepository parse(String methodName){
        if(canHandelFile(methodName,"Transaction"))
        {
            Class c1  = softwareRepository.getClass("Transaction");
           return new MethodRepository(c1,methodName);
        }
        else
            {
          return super.parse(methodName);
        }
    }
}
