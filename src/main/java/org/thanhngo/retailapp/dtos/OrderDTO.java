package org.thanhngo.retailapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderDTO {
    @JsonProperty("user_id")
    @Min(value = 1, message = "User id cannot be less than 1")
    private Long userId;
    @JsonProperty("fullname")
    private String fullName;
    private String email;
    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number cannot be blank")
    private String phone_number;
    private String address;
    private String note;
    @JsonProperty("total_money")
    @Min(value = 0, message = "Total money cannot be less than 0")
    private Float totalMoney;
    @JsonProperty("shipping_method")
    private String shippingMethod;
    @JsonProperty("shipping_address")
    private String shippingAddress;
    @JsonProperty("payment_method")
    private String paymentMethod;
}
