package e2e.pojos.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateListPayload {
    private String name;
    private String description;
    private String language;
}
