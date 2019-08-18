package com.sdg.security.demo.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.sdg.security.core.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@ToString
public class User extends BaseEntity {
    public interface simpleJsonView{};
    public interface detailJsonView extends simpleJsonView{};

    @JsonView(simpleJsonView.class)
    @NotBlank(message = "用户名不能为空")
    @Length(message = "用户名不在3-12位数之间",max = 12,min = 3)
    @ApiModelProperty(value = "用户名")
    private String username;

    @JsonView(detailJsonView.class)
    @NotBlank(message = "密码不能为空")
    @Length(message = "密码不在3-12位数之间",max = 12,min = 3)
    @ApiModelProperty(value = "密码")
    private String password;
}
