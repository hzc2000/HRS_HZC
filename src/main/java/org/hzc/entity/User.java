package org.hzc.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Validated
@Entity
@Table(name = "t_user_hzc")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true,
        exclude = {"pwdHash","password"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "{user.name.blank}")
    private String name;
    @Pattern(regexp = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$", message = "{user.phone.invalid}")
    @NotBlank(message = "{user.phone.blank}")
    private String phone;
    @Transient
    @NotBlank(message = "{user.password.blank}")
    private String password;

    private String pwdHash;
    @Enumerated(EnumType.STRING)
    private Role role;

}
