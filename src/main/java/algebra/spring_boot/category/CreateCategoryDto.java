package algebra.spring_boot.category;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateCategoryDto {

    @NotBlank
    private String name;


    private String description;
}
