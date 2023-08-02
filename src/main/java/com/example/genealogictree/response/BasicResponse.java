package com.example.genealogictree.response;

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
public class BasicResponse {

    private HttpStatus status;
    private String message;
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
