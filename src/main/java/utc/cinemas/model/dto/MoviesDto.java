package utc.cinemas.model.dto;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import utc.cinemas.model.entity.Movie;

import java.sql.Timestamp;

@Data
public class MoviesDto {
    private Long id;
    private String title;
    private String genre;
    private Integer duration;
    private String description;
    private Timestamp releaseDate;
    private Integer rating;
    private Integer status;

    public Movie getEntity() {
        Movie movie = new Movie();
        BeanUtils.copyProperties(this, movie);
        return movie;
    }
}
