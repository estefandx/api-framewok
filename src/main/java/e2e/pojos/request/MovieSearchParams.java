package e2e.pojos.request;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Builder
public class MovieSearchParams {

    private String query;
    private Boolean includeAdult;
    private String language;
    private String primaryReleaseYear;
    private Integer page;
    private String region;
    private String year;

    public Map<String, String> toMap() {
        Map<String, String> queryParams = new HashMap<>();

        if (query != null) queryParams.put("query", query);
        if (includeAdult != null) queryParams.put("include_adult", includeAdult.toString());
        if (language != null) queryParams.put("language", language);
        if (primaryReleaseYear != null) queryParams.put("primary_release_year", primaryReleaseYear);
        if (page != null) queryParams.put("page", page.toString());
        if (region != null) queryParams.put("region", region);
        if (year != null) queryParams.put("year", year);

        return queryParams;
    }
}
