package br.spring.persistence;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:database-mysql-bean.xml", "classpath:database-config.xml"})
public class MysqlPersistenceTest extends PersistenceTestBase{

}
