package org.thanhngo.retailapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
@Data//created toString to show object
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageDTO {
    @Min(value = 1, message = "Product id must be greater than 0")
    @JsonProperty("product_id")
    private Long productId;
    @Size(min= 5, max = 200, message = "Image url must be between 5 and 200")
    @JsonProperty("image_url")
    private  String imageUrl;


}

