package beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named("loginController")
@RequestScoped
public class LoginController {
    private String username;
    private String password;

    public String login() {
        // Default Login Nutzer (in Produktion durch .env ersetzen um sensible Daten zu schützen)
        if ("iosif.gogolos@iu-study.org".equals(username) && "ipwa02-01".equals(password)) {
            return "home.xhtml?faces-redirect=true";
        } else {
            return "login.xhtml?faces-redirect=true"; // oder Fehler anzeigen
        }
    }

    // Getter & Setter Methoden
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
