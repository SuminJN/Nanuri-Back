package walab.nanuri.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import walab.nanuri.auth.controller.request.LoginRequest;
import walab.nanuri.auth.controller.response.LoginResponse;
import walab.nanuri.auth.dto.AuthDto;
import walab.nanuri.auth.service.AuthService;
import walab.nanuri.auth.service.HisnetLoginService;

@RestController
@RequestMapping("/api/nanuri/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;
  private final HisnetLoginService hisnetLoginService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> Login(@RequestBody LoginRequest request) {
    return ResponseEntity.ok(LoginResponse.from(authService.login(hisnetLoginService.callHisnetLoginApi(AuthDto.from(request)))));
  }
}