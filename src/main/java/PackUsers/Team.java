package PackUsers;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamID;

    @Column(nullable = false, unique = true)
    private String teamName;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private User supervisor;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<User> members;

    public void addMember(User user) {
        members.add(user);
        user.setTeam(this);
    }

    public void removeMember(User user) {
        members.remove(user);
        user.setTeam(null);
    }

    public User getSupervisor() {
        return supervisor;
    }

    public List<User> listMembers() {
        return members;
    }
}