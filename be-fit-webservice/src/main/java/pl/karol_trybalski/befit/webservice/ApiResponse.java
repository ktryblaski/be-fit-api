package pl.karol_trybalski.befit.webservice;

import lombok.Getter;
import lombok.Setter;
import pl.karol_trybalski.befit.webservice.exception.DomainError;

@Getter
@Setter
public class ApiResponse<T> {

    private T data;
    private String error;

    public static <R> ApiResponse<R> from(DomainError error) {
        return new ApiResponse<>(null, error);
    }

    private ApiResponse(T data, DomainError error) {
        this.data = data;
        this.error = error != null ? error.code : null;
    }

}
