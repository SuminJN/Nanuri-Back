package walab.nanuri.auth.dto;

import lombok.*;
import walab.nanuri.auth.controller.request.LoginRequest;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AuthDto {
  private String uniqueId;
  private String hisnetToken;
  private String token; //jwt 토큰
  private String name;
  private String email;
  private String department;
  private String major1;
  private String major2;
  private Integer grade;
  private Integer semester;

  public static AuthDto from(LoginRequest request) {
    return AuthDto.builder().hisnetToken(request.getHisnetToken()).build();
  }
}
