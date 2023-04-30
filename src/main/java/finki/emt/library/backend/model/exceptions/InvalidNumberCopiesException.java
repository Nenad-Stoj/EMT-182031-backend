package finki.emt.library.backend.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidNumberCopiesException extends RuntimeException {
    public InvalidNumberCopiesException() {
        super("Invalid number of copies requested");
    }
}
