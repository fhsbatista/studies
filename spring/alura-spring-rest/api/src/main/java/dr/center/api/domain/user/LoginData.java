package dr.center.api.domain.user;

import jakarta.validation.constraints.NotBlank;

public record LoginData(
        @NotBlank
        String username,

        @NotBlank
        String password) {
}
