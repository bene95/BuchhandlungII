package Parser;

import Repository.MethodRepository;

/**
 * Created by wn00084650 on 14.04.2017.
 */
public class PersistenceParser extends Parser {

    public MethodRepository parse(String methodName){
        if(canHandelFile(methodName,"Persistence")){
            Class c1  = softwareRepository.getClass("Persistence");
           return  new MethodRepository(c1,methodName);
        }
        else
        {
          return   super.parse(methodName);
        }
    }
}
