package com.iti.parking.entity.jpa;

// Generated Jun 9, 2013 9:22:49 PM by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "admins")
@NamedQueries(value = { @NamedQuery(name = "Admin.findAdminByID", query = "select a from Admin a where a.id = :id"), 
						@NamedQuery(name = "Admin.findAdminByNameAndPasswword", query = "select a from Admin a where a.name = :name and a.password = :password")
})
public class Admin implements java.io.Serializable {

	private static final long serialVersionUID = 1262610256156461462L;

	public static final String FIND_ADMIN_BY_ID = "Admin.findAdminByID";
	public static final String FIND_ADMIN_BY_MAME_AND_PASSWORD = "Admin.findAdminByNameAndPasswword";

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "password")
	private String password;

	// @Enumerated(EnumType.STRING)
	// private Role role;

	public Admin() {
	}

	public Admin( String name, String password) {
		this.name = name;
		this.password = password;
		
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// public boolean isAdmin() {
	// return Role.ADMIN.equals(role);
	// }
	//
	// public boolean isUser() {
	// return Role.USER.equals(role);
	// }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", password=" + password + "]";
	}

}
