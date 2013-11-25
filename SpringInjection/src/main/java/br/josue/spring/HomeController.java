package br.josue.spring;

import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/*
 *Annotation | Meaning                                            |
+------------+-----------------------------------------------------+
| @Component | generic stereotype for any Spring-managed component |
| @Repository| stereotype for persistence layer                    |
| @Service   | stereotype for service layer                        |
| @Controller| stereotype for presentation layer (spring-mvc)      |
 *
 */





@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	@Qualifier("annotationService")//to avoid doubled objects
	private IService annotationService;
	
	@Autowired
	@Qualifier("xmlService") //to avoid doubled objects
    private IService xmlService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		
		annotationService.doService();
		xmlService.doService();
		
		return "home";
	}
	
}
