package utc.cinemas.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;
import utc.cinemas.model.entity.Movie;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class MovieDto {
    private Long id;
    private String title;
    private String genre;
    private Integer duration;
    private String description;
    private String releaseDate;
    private Integer rating;
    private Long createdUser;
    private Long modifiedUser;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private Integer status;
    private String director;
    private String image;
    private MultipartFile imageFile;

    public Movie getEntity() {
        Movie movie = new Movie();
        if (this.releaseDate != null) movie.setReleaseDate(Timestamp.valueOf(LocalDateTime.parse(releaseDate, DateTimeFormatter.ISO_DATE_TIME)));
        BeanUtils.copyProperties(this, movie);
        return movie;
    }
}
