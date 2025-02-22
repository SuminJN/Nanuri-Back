package walab.nanuri.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import walab.nanuri.user.controller.response.MyProfileResponse;
import walab.nanuri.user.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @GetMapping("/api/nanuri/my-profile")
  public ResponseEntity<MyProfileResponse> getMyProfile(@AuthenticationPrincipal String uniqueId) {
    return ResponseEntity.ok(MyProfileResponse.from(userService.getUser(uniqueId)));
  }
}