package com.realforce1024.restspec.dto;

import com.realforce1024.restspec.common.annotation.CheckUserName;
import com.realforce1024.restspec.common.validator.A;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.random.RandomGenerator;

/**
 * @author 编程燃风 RealForce1024
 */
@Data
public class UserDTO {
    private Long id = RandomGenerator.getDefault().nextLong(1000, Long.MAX_VALUE);
    @NotBlank(message = "用户名不能为空")
    // @Pattern(regexp = "^((?!(gdx|奥利给)).)*", message = "名字内不能包含字符gdx或者奥利给")
    @CheckUserName(groups = A.class)
    private String username;
    @Min(value = 0, message = "年龄必须>=0")
    private Integer age;
    @Email(message = "请检查email格式")
    private String email;
    @Pattern(regexp = "^[a-zA-Z]$", message = "姓氏字符需是字母")
    private String lastName;
}
