package br.spring.persistence.i;

import br.spring.persistence.controller.ex.MyCustomException;
import br.spring.persistence.model.User;

public interface IService {

	public void createUser(User user) throws MyCustomException;
}
