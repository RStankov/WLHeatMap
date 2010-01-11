package wireless.adapters;

@SuppressWarnings("all")
public class NotFoundException extends Exception {
	
	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException() {
		super();
	}
}
