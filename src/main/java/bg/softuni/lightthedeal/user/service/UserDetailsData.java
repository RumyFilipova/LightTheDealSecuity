package bg.softuni.lightthedeal.user.service;
import bg.softuni.lightthedeal.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class UserDetailsData implements UserDetails {

private UUID id;
private String username;
private String password;
private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {

        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}
