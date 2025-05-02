<<<<<<<< HEAD:src/main/resources/archetype-resources/src/main/java/UserMapper.java
========
package PackUsers;

import org.springframework.stereotype.Component;

>>>>>>>> 1b81f41 (User Creation):src/main/java/PackUsers/UserMapper.java
@Component
public class UserMapper {
    public UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return user;
    }
}