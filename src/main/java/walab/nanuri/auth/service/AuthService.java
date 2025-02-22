package walab.nanuri.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import walab.nanuri.auth.dto.AuthDto;
import walab.nanuri.auth.util.JwtUtil;
import walab.nanuri.base.exception.DoNotExistException;
import walab.nanuri.user.entity.User;
import walab.nanuri.user.repository.UserRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;

  @Value("${custom.jwt.secret}")
  private String SECRET_KEY;

  public User getLoginUser(String uniqueId) {
    return userRepository
        .findById(uniqueId)
        .orElseThrow(() -> new DoNotExistException("해당 유저가 없습니다."));
  }

  public AuthDto login(AuthDto dto) {
    Optional<User> user = userRepository.findById(dto.getUniqueId());

    // 최초 로그인한 경우
    if (user.isEmpty()) {
      User newUser = User.from(dto);
      userRepository.save(User.from(dto));
        return AuthDto.builder()
                .token(JwtUtil.createToken(newUser.getUniqueId(), newUser.getStatus().name(), newUser.getName() , SECRET_KEY))
                .build();
    }
    // 최초 로그인이 아닐 경우
    else {
      user.get().update(dto);
      return AuthDto.builder()
              .token(
                      JwtUtil.createToken(
                              user.get().getUniqueId(), user.get().getStatus().name(), user.get().getName() , SECRET_KEY))
              .build();
    }
  }
}