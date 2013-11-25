package br.josue.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//@Service annotation is not needed, because we already defined as a bean on application-context.xml
public class XMLService implements IService {

 private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    
    public XMLService(){
        logger.info("XMLSERVICE CREATED ****");
    }
    
    @Override
    public void doService() {
        logger.info("****XMLERVICE -  DOSERVICE****");
        
    }
    
}
