package com.bootcamp.socialmeligrupo5.entity;

import java.time.LocalDate;

public class Post {
		private Long id;
		private LocalDate date;
		private Integer category;
		private Long sellerId;
		private Product product;

		public Post(Integer category, LocalDate date, Long id, Product product, Long sellerId) {
				this.category = category;
				this.date = date;
				this.id = id;
				this.product = product;
				this.sellerId = sellerId;
		}

		public Integer getCategory() {
				return category;
		}

		public void setCategory(Integer category) {
				this.category = category;
		}

		public LocalDate getDate() {
				return date;
		}

		public void setDate(LocalDate date) {
				this.date = date;
		}

		public Long getId() {
				return id;
		}

		public void setId(Long id) {
				this.id = id;
		}

		public Product getProduct() {
				return product;
		}

		public void setProduct(Product product) {
				this.product = product;
		}

		public Long getSellerId() {
				return sellerId;
		}

		public void setSellerId(Long sellerId) {
				this.sellerId = sellerId;
		}
}
