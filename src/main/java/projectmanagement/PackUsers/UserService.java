package projectmanagement.PackUsers;

import projectmanagement.Enums.Enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    // private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, TeamRepository teamRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        // this.passwordEncoder = passwordEncoder;
    }

//    /**
//     * Metoda tworząca nowego użytkownika przez Super Usera
//     */
//    @Transactional
//    public UserDTO createUser(UserCreationDTO userCreationDTO, User currentUser) {
//        // Sprawdzenie czy osoba tworząca ma uprawnienia Super Usera
//        if (!currentUser.hasRole(Role.SUPER_USER) && !currentUser.hasRole(Role.ADMIN)) {
//            throw new UnauthorizedOperationException("Tylko Super User i Admin mogą tworzyć nowych użytkowników");
//        }
//
//        // Sprawdzenie czy Super User próbuje stworzyć admina lub innego super usera
//        if (currentUser.hasRole(Role.SUPER_USER) &&
//                (userCreationDTO.getRole() == Role.ADMIN || userCreationDTO.getRole() == Role.SUPER_USER)) {
//            throw new UnauthorizedOperationException("Super User nie może tworzyć adminów ani innych Super Userów");
//        }
//
//        // Sprawdzenie czy użytkownik z podanym emailem już istnieje
//        if (userRepository.existsByEmail(userCreationDTO.getEmail())) {
//            throw new UserAlreadyExistsException("Użytkownik z podanym adresem email już istnieje");
//        }
//
//        // Utworzenie nowego użytkownika
//        User newUser = new User();
//        newUser.setEmail(userCreationDTO.getEmail());
//        newUser.setPassword(passwordEncoder.encode(userCreationDTO.getPassword())); // Hashowanie hasła
//        newUser.setName(userCreationDTO.getName());
//        newUser.setRole(userCreationDTO.getRole());
//
//        // Przypisanie do zespołu, jeśli podano
//        if (userCreationDTO.getTeamId() != null) {
//            Team team = teamRepository.findById(userCreationDTO.getTeamId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Zespół o podanym ID nie istnieje"));
//            newUser.setTeam(team);
//        }
//
//        User savedUser = userRepository.save(newUser);
//
//        // Konwersja do DTO (bez hasła)
//        return convertToDTO(savedUser);
//    }
//
//    /**
//     * Metoda służąca do bezpośredniego tworzenia Super Usera przez Admina
//     * Ta metoda byłaby wywoływana z poziomu konsoli/skryptu przez admina
//     */
//    @Transactional
//    public UserDTO createSuperUser(UserCreationDTO superUserDTO, User adminUser) {
//        // Sprawdzenie czy osoba tworząca ma uprawnienia Admina
//        if (!adminUser.hasRole(Role.ADMIN)) {
//            throw new UnauthorizedOperationException("Tylko Admin może tworzyć Super Userów");
//        }
//
//        // Sprawdzenie czy użytkownik z podanym emailem już istnieje
//        if (userRepository.existsByEmail(superUserDTO.getEmail())) {
//            throw new UserAlreadyExistsException("Użytkownik z podanym adresem email już istnieje");
//        }
//
//        // Upewnienie się, że tworzony użytkownik ma rolę Super Usera
//        superUserDTO.setRole(Role.SUPER_USER);
//
//        // Utworzenie nowego Super Usera
//        User newSuperUser = new User();
//        newSuperUser.setEmail(superUserDTO.getEmail());
//        newSuperUser.setPassword(passwordEncoder.encode(superUserDTO.getPassword())); // Hashowanie hasła
//        newSuperUser.setName(superUserDTO.getName());
//        newSuperUser.setRole(Role.SUPER_USER);
//
//        // Przypisanie do zespołu, jeśli podano
//        if (superUserDTO.getTeamId() != null) {
//            Team team = teamRepository.findById(superUserDTO.getTeamId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Zespół o podanym ID nie istnieje"));
//            newSuperUser.setTeam(team);
//        }
//
//        User savedSuperUser = userRepository.save(newSuperUser);
//
//        // Konwersja do DTO (bez hasła)
//        return convertToDTO(savedSuperUser);
//    }
//
//    /**
//     * Metoda pomocnicza do konwersji User -> projectmanagement.Enums.Enums.PackUsers.UserDTO
//     */
//    private UserDTO convertToDTO(User user) {
//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(user.getId());
//        userDTO.setEmail(user.getEmail());
//        userDTO.setName(user.getName());
//        userDTO.setRole(user.getRole());
//
//        if (user.getTeam() != null) {
//            userDTO.setTeamId(user.getTeam().getTeamID());
//        }
//
//        return userDTO;
//    }

    /**
     * Pobieranie użytkownika po ID
     */
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Użytkownik o podanym ID nie istnieje"));
    }

    /**
     * Pobieranie wszystkich użytkowników
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(userRepository.findAll());
    }
}