package projectmanagement.PackUsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team createTeam(Team team) {
        // If supervisor ID is provided, set the supervisor
        if (team.getSupervisor() != null && team.getSupervisor().getId() != null) {
            team.setSupervisor(userRepository.findById(team.getSupervisor().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Supervisor not found")));
        }

        return teamRepository.save(team);
    }

    public Team getTeamById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found"));
    }

    public Team updateTeam(Long id, Team teamDetails) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found"));

        team.setName(teamDetails.getName());

        // If supervisor ID is provided, set the supervisor
        if (teamDetails.getSupervisor() != null && teamDetails.getSupervisor().getId() != null) {
            team.setSupervisor(userRepository.findById(teamDetails.getSupervisor().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Supervisor not found")));
        }

        return teamRepository.save(team);
    }

    public void deleteTeam(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found"));
        teamRepository.delete(team);
    }
}