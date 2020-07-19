package com.example.demo.annotations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

	@Autowired
	private JdbcTemplate template;

	@PostConstruct
	public void createTable() {
		template.execute(
				"CREATE TABLE movies (id bigint auto_increment primary key, name VARCHAR(50), year int, rating int)");
	}

	public void createMovie(String name, int year, int rating) {
		String query = "INSERT INTO movies (name, year, rating) VALUES (?,?,?)";
		template.update(query, "Some movie", 1974, 3);
		template.update(query, "Some other movie", 1993, 2);
	}

	public List<Movie> findMoviesByName(String likeName) {
		String query = "SELECT * FROM movies WHERE name LIKE '" + likeName + "'";
		return template.query(query, new RowMapper<Movie>() {
			@Override
			public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
				Movie movie = new Movie();
				movie.name = rs.getString("name");
				movie.year = rs.getInt("year");
				movie.rating = rs.getInt("rating");
				return movie;
			}
		});
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext();
		config.register(Config.class);
		config.refresh();
		MovieRepository repository = config.getBean(MovieRepository.class);

		repository.createMovie("Some movie", 1974, 3);
		repository.createMovie("Some other movie", 1993, 2);

		List<Movie> movies = repository.findMoviesByName("Some%");
		for (Movie movie : movies) {
			System.out.println(movie.name + " - " + movie.year + " - " + movie.rating);
		}
	}
}