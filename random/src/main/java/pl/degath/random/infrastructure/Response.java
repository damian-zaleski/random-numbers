package pl.degath.random.infrastructure;

public class Response {

    private final String body;

    public Response(String body) {
        this.body = Validator.notBlank(body, "Invalid response body (Cannot be null or blank).");
    }

    public String getBody() {
        return body;
    }
}
