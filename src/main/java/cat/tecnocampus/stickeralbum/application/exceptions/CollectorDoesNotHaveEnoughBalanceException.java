package cat.tecnocampus.stickeralbum.application.exceptions;

public class CollectorDoesNotHaveEnoughBalanceException extends RuntimeException {
    public CollectorDoesNotHaveEnoughBalanceException(Long id) {
        super("This id "+id+" not have enough balance");
    }
}
