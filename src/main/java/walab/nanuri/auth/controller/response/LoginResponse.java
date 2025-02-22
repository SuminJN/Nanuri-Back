package walab.nanuri.auth.controller.response;

import lombok.Builder;
import lombok.Getter;
import walab.nanuri.auth.dto.AuthDto;

@Getter
@Builder
public class LoginResponse {
  private String token;

  public static LoginResponse from(AuthDto authDto) {
    return LoginResponse.builder().token(authDto.getToken()).build();
  }
}