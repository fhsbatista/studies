package dr.center.api.controller;

import dr.center.api.domain.user.LoginData;
import dr.center.api.domain.user.User;
import dr.center.api.infra.security.TokenJWT;
import dr.center.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid LoginData data) {
        var loginToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = authManager.authenticate(loginToken);
        var jwt = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new TokenJWT(jwt));
    }
}
