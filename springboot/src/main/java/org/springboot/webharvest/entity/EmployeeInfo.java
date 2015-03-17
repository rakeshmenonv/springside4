package org.springboot.webharvest.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;


import org.springboot.common.IdEntity;
@Entity
@Cacheable(value = true)
/*@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)*/
@Table(name = "userdetails")
public class EmployeeInfo extends IdEntity{
	
	private String name;
	private String age;
	private String address;
	private String email;
	private String phoneNumber;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	

}
