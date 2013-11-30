package br.spring.persistence.springutils;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

import br.spring.persistence.data.JdbcData;
import br.spring.persistence.i.IData;

/*
 * This class allows to change the implementations at runtime
 * like Abstract Factory Pattern
 */

//should be autowired on duplicated interface usages classes
@Component
// Or can just implement BeanFactoryPostProcessor
public class MyBeanResolver implements BeanDefinitionRegistryPostProcessor {

	private Class<?> targetBeanClass = null;

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

		// this method can be used to set a primary bean, although
		// beans defined in a @Configuration class will not be avalable here.

	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

		// here, all beans are available including those defined by
		// @configuration, @component, xml, etc.

		// do some magic to somehow find which is the preferred bean name for
		// each interface
		// you have access to all bean-definition names with:
		// beanFactory.getBeanDefinitionNames()

		// String beanName = "jdbcData"; // let's say is this one
		// get the definition for that bean and set it as primary
		// beanFactory.getBeanDefinition(beanName).setPrimary(true);

		// TODO check beans by instance of
		// Map<String, Class<f>> beans =
		// beanFactory.getBeansOfType(IData.class);

		// this works only for bean loading
		// for (String beanName : beanFactory.getBeanDefinitionNames()) {
		// Object beanInstance = beanFactory.getSingleton(beanName); // do not
		// use getBean
		// if (targetBeanClass != null) {
		// if (targetBeanClass.isInstance(beanInstance)) {
		// beanFactory.getBeanDefinition(beanName).setPrimary(true);
		// return;
		// }
		// }
		// }

	}

}
