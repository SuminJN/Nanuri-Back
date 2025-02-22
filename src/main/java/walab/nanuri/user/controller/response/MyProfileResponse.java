package walab.nanuri.user.controller.response;

import lombok.Builder;
import lombok.Getter;
import walab.nanuri.user.dto.UserDto;

@Builder
@Getter
public class MyProfileResponse {
    private String uniqueId;
    private String name;
    private String email;
    private Integer grade;
    private Integer semester;
    private String department;
    private String major1;
    private String major2;

    public static MyProfileResponse from(UserDto dto) {
        return MyProfileResponse.builder()
            .uniqueId(dto.getUniqueId())
            .name(dto.getName())
            .email(dto.getEmail())
            .grade(dto.getGrade())
            .semester(dto.getSemester())
            .department(dto.getDepartment())
            .major1(dto.getMajor1())
            .major2(dto.getMajor2())
            .build();
    }
}