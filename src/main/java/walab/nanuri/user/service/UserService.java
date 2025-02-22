package walab.nanuri.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import walab.nanuri.user.dto.UserDto;
import walab.nanuri.user.repository.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public UserDto getUser(String uniqueId) {
    return UserDto.from(
        userRepository
            .findById(uniqueId)
            .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다.")));
  }
}