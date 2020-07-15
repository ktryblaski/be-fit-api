package pl.karol_trybalski.befit.dto.dto.base;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseDTO implements Serializable {

    protected Long id;

}
