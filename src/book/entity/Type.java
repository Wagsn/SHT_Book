package book.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Type {
	private Integer typeId;
	private String typeName;
	/*
	 * 和user一对多
	 */
	private Set<Book> books=new HashSet<Book>();
	/*
	 * 和FatherType多对一
	 */
	private FatherType fatherType;
	
	@Id
	@GeneratedValue
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	@OneToMany(mappedBy="type",
			cascade={CascadeType.ALL},
			fetch=FetchType.LAZY)
	public Set<Book> getBooks() {
		return books;
	}
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
	
	@ManyToOne(cascade={CascadeType.ALL},
			fetch=FetchType.LAZY)
	@JoinColumn(name="fathertypeId")
	public FatherType getFatherType() {
		return fatherType;
	}
	public void setFatherType(FatherType fatherType) {
		this.fatherType = fatherType;
	}
	
	@Override
	public String toString() {
		return "type-name:"+this.getTypeName();
	}
	
	
}
