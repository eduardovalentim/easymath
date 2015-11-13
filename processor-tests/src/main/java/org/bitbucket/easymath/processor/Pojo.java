package org.bitbucket.easymath.processor;

import java.io.Serializable;
import java.util.Date;

import org.bitbucket.easymath.annotations.Function;
import org.bitbucket.easymath.annotations.Function.Type;
import org.bitbucket.easymath.annotations.Mathematical;

@Mathematical(functions = { 
		@Function(name = "juros", formula = "x + 2 * (y / 5) ^ 6", use=Type.BIGDECIMAL),
		@Function(name = "espaco", formula = "2 ^ a ^ 7 / 2 / b", use=Type.DOUBLE)})
public class Pojo implements Serializable {

	private static final long serialVersionUID = -4891051700156343092L;

	private Long id;
	private String description;
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
