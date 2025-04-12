package utc.cinemas.model.dto;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import utc.cinemas.model.entity.Movie;

import java.sql.Timestamp;

@Data
public class MovieDto {
    private Long id;
    private String title;
    private String genre;
    private Integer duration;
    private String description;
    private Timestamp releaseDate;
    private Integer rating;
    private Long createdUser;
    private Long modifiedUser;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private Integer status;

    public Movie getEntity() {
        Movie movie = new Movie();
        BeanUtils.copyProperties(this, movie);
        return movie;
    }
}
