package org.thanhngo.retailapp.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data//created toString to show object
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    @NotEmpty(message = "Category's name cannot be empty!")
    private String name;
}
