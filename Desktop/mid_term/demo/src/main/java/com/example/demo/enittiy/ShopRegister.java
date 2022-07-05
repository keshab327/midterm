package com.example.demo.enittiy;

import java.util.Arrays;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ShopRegister")
public class ShopRegister {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "owner_name", nullable = false)
	private String owner_name;
	
	@Column(name = "email", nullable =true)
	private String email ;
	
	@Column(name = "phone_number", nullable = true)
	private Long phone_number;
	


	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Long getPhone_number() {
		return phone_number;
	}



	public void setPhone_number(Long phone_number) {
		this.phone_number = phone_number;
	}








	@Column(name = "verification", nullable = false)
	private String verification;
	
	@Column(name = "pan_number", nullable = false)
	private int pan_number;
	
	public void setPan_number(int pan_number) {
		this.pan_number = pan_number;
	}

	
	
	public String getVerification() {
		return verification;
	}


	public void setVerification(String verification) {
		this.verification = verification;
	}


	public int getPan_number() {
		return pan_number;
	}
	

	public String getOwner_name() {
		return owner_name;
	}


	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}


	


	


	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = false)
	private String description;	
	
	@Column(name = "adress",nullable = false, precision = 10, scale = 2)
    private String adress;
	
	
	
	
	


	

	@Lob
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;
	
	
	@Lob
    @Column(name = "Image_tax", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image_tax;
    
    public byte[] getImage_tax() {
		return image_tax;
	}


	public void setImage_tax(byte[] image_tax) {
		this.image_tax = image_tax;
	}


	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
    private Date createDate;

	public ShopRegister() {}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getAdress() {
		return adress;
	}



	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}







	@Override
	public String toString() {
		return "ShopRegister [id=" + id + ", owner_name=" + owner_name + ", email=" + email + ", phone_number="
				+ phone_number + ", verification=" + verification + ", pan_number=" + pan_number + ", name=" + name
				+ ", description=" + description + ", adress=" + adress + ", image=" + Arrays.toString(image)
				+ ", image_tax=" + Arrays.toString(image_tax) + ", createDate=" + createDate + "]";
	}



	public void setAdress(String adress) {
		// TODO Auto-generated method stub
		this.adress = adress;
	}


	
   
}

