package finki.emt.library.backend.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NotAvailableCopiesException extends RuntimeException {
    public NotAvailableCopiesException(Integer requestedCopies) {
        super(String.format("The requested number of copies %d is not available",
                requestedCopies));
    }
}
