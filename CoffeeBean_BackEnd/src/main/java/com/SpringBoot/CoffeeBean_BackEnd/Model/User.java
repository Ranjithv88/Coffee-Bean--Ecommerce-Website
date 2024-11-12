package com.SpringBoot.CoffeeBean_BackEnd.Model;

import com.SpringBoot.CoffeeBean.Annotations.Decimal;
import com.SpringBoot.CoffeeBean_BackEnd.Model.Enum.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "user")
public class User {

    @Id
    private long id;

    @NotBlank(message = " UserName is Mandatory ......! ")
    @Size(min = 2,message = " UserName Must Be Between 2 & 30 Characters .......! ",max = 40)
    private String userName;

    @Decimal
    private int age;

    @NotBlank(message = " Email is Mandatory ......! ")
    @Email(message = " Email is Invalid .......! ")
    @Indexed(unique = true)
    private String email;

    @NotBlank(message = " Password is Mandatory ......! ")
    @Size(min = 8,message = " Too Weak .......! ")
    private String password;

    @NotNull(message = " Phone Number is Mandatory ......! ")
    @Size(min = 10,max = 10,message = " Phone Number is Invalid ......! ")
    private String phoneNumber;

    @Field("role")
    private Role role;

}

