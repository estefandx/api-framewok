package e2e.pojos.request.moviSearch;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieSearchResponse {
    private int page;
    private List<Movie> results;
    private int totalPages;
    private int totalResults;
}
