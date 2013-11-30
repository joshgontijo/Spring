package br.spring.persistence.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
//ref: http://viralpatel.net/blogs/hibernate-inheritance-table-per-concrete-class-annotation-xml-mapping/
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Resource {

	@Id
	private String uuid;
	private Date date_created;
	private Date date_updated;
	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Date getDate_created() {
		return date_created;
	}
	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}
	public Date getDate_updated() {
		return date_updated;
	}
	public void setDate_updated(Date date_updated) {
		this.date_updated = date_updated;
	}
	
	
	
	
}
