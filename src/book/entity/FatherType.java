package book.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class FatherType {
	private Integer fathertypeId;
	private String fathertypeName;
	/*
	 * 与type是一对多的关系
	 */
	private Set<Type> types=new HashSet<Type>();
	
	@Id
	@GeneratedValue
	public Integer getFathertypeId() {
		return fathertypeId;
	}
	public void setFathertypeId(Integer fathertypeId) {
		this.fathertypeId = fathertypeId;
	}
	public String getFathertypeName() {
		return fathertypeName;
	}
	public void setFathertypeName(String fathertypeName) {
		this.fathertypeName = fathertypeName;
	}
	
	@OneToMany(mappedBy="fatherType",
			cascade={CascadeType.ALL},
			fetch=FetchType.LAZY)
	public Set<Type> getTypes() {
		return types;
	}
	public void setTypes(Set<Type> types) {
		this.types = types;
	}
	
	@Override
	public String toString() {
		return "fathertypeName名字:"+this.getFathertypeName();
	}
	
}
