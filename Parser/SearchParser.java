package Parser;

import Repository.MethodRepository;

/**
 * Created by wn00084650 on 14.04.2017.
 */
public class SearchParser extends Parser {
    public SearchParser(Parser successor) {
        this.setSuccessor(successor);
    }
    public MethodRepository parse(String methodName){
        if(canHandelFile(methodName,"Search")){
            Class c1  = softwareRepository.getClass("Search");
           return new MethodRepository(c1,methodName);
        }
        else
        {
            return super.parse(methodName);
        }
    }
}
