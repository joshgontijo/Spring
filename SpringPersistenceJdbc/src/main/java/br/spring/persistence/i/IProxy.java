package br.spring.persistence.i;

public interface IProxy extends IData{

	public void setPersistenceDataAccess(IData data);
}
