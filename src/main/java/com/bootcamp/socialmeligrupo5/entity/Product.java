package com.bootcamp.socialmeligrupo5.entity;

public class Product {
		private Long id;
		private String name;
		private String brand;
		private String color;
		private String notes;
		private String type;

		public Product () {}

		public Product(String brand, String color, Long id, String name, String notes) {
				this.brand = brand;
				this.color = color;
				this.id = id;
				this.name = name;
				this.notes = notes;
		}

		public String getBrand() {
				return brand;
		}

		public void setBrand(String brand) {
				this.brand = brand;
		}

		public String getColor() {
				return color;
		}

		public void setColor(String color) {
				this.color = color;
		}

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

		public String getNotes() {
				return notes;
		}

		public void setNotes(String notes) {
				this.notes = notes;
		}

		public String getType() {
				return type;
		}

		public void setType(String type) {
				this.type = type;
		}
}
