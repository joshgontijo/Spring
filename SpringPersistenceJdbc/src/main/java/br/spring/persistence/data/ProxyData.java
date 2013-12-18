package br.spring.persistence.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.spring.persistence.i.IData;

@Component
@Primary
public class ProxyData implements IData {

    //Implementation autowiring
    @Autowired
    HibernateData hibernateData;
    
    //Implementation autowiring
    @Autowired
    JdbcData jdbc;
    
    int x = 0;
    @Override
    public void createUser() {
        x++;
        if(x % 2 == 0){
            jdbc.createUser();
        }
        else{
            hibernateData.createUser();
        }
            
        
    }

}
