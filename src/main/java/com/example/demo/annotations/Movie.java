package com.example.demo.annotations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

class Movie {
	public String name;
	public int year;
	public int rating;

	public Movie() {
	}

	public Movie(String name, int year, int rating) {
		super();
		this.name = name;
		this.year = year;
		this.rating = rating;
	}
}

@Configuration
@Import(MovieRepository.class)
class Config {
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.h2.Driver");
		ds.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
		return ds;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DriverManagerDataSource ds) {
		return new JdbcTemplate(ds);
	}
}

//	UPDATE enrollments
//	SET year = '2015'
//	WHERE id BETWEEN '20' AND '100';
