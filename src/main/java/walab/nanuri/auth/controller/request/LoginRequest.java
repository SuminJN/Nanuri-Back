package walab.nanuri.auth.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginRequest {
  private String hisnetToken;
}