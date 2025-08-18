package tracker.trackerback.model;

import lombok.Getter;

@Getter
public final class AuthResponse {
    private final String token;

    public AuthResponse(String token) {
        this.token = token;
    }
}
