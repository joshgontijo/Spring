package br.josue.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/*
 * reference: http://docs.spring.io/spring/docs/3.0.0.M3/reference/html/ch04s11.html
 */

@Service//("annotationService")//Qualifier
public class AnnotationService implements IService{

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    
    public AnnotationService(){
        logger.info("ANNOTATIONSERVICE CREATED ****");
    }
    
    @Override
    public void doService() {
        logger.info("****ANNOTATIONSERVICE -  DOSERVICE****");
        
    }

}
