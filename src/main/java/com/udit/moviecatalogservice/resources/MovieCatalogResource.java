package com.udit.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.udit.moviecatalogservice.models.Movie;
import com.udit.moviecatalogservice.models.MovieCatalog;
import com.udit.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate template;

	@GetMapping("/{userId}")
	public List<MovieCatalog> getCatalogs(@PathParam("userId") String userId) {

		UserRating userRating = template.getForObject("http://ratings-data-service/userratings/" + userId,
				UserRating.class);
		
		return userRating.getRatings().stream().map(rating -> {
			Movie movie = template.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
			return new MovieCatalog(Integer.valueOf(movie.getMovieId()), movie.getName(), rating.getRating());
		}).collect(Collectors.toList());

	}
}
