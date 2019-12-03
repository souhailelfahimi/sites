package com.example.demo.module;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Site implements Serializable{
	@Id
	@GeneratedValue
	private Long id;
	private String nom;
	private String description;
	private double x;
	private double y;
	@OneToMany(mappedBy = "site")
	private List<Photo> photos;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public List<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	public Site( String nom, String description, double x, double y) {
		super();
		this.nom = nom;
		this.description = description;
		this.x = x;
		this.y = y;
		
	}
	public Site() {
		super();
	}
	

}
