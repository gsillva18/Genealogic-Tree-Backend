package com.example.genealogictree.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ApiModel(description = "basic response")
public class BasicResponse {

    @ApiModelProperty(value = "status", example = "CREATED", required = true)
    private HttpStatus status;
    @ApiModelProperty(value = "message", example = "successful create", required = true)
    private String message;
    @ApiModelProperty(value = "errors", example = "[]")
    private List<Object> errors;

    public BasicResponse(HttpStatus status, String message, List<FieldError> errors, String errorException){
        this.status = status;
        this.message = message;
        this.errors = new ArrayList<>();

        if (errorException != null) {
            this.errors.add(errorException);
        }

        if (errors != null){

            this.errors.addAll(errors);
        }
    }
}
