package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.auth.JwtTokenDTO;
import med.voll.api.domain.auth.LoginDTO;
import med.voll.api.domain.user.User;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<JwtTokenDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
        var authToken = new UsernamePasswordAuthenticationToken(loginDTO.login(), loginDTO.password());
        var user = (User) authenticationManager.authenticate(authToken).getPrincipal();
        var jwt = tokenService.generateToken(user);
        return ResponseEntity.ok(new JwtTokenDTO(jwt));
    }
}
