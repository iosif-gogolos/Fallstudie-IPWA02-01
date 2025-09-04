package beans;

import entities.BerichtendePerson;
import entities.BergendePerson;
import entities.Person;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginController implements Serializable {

    private Long loginPersonId;        // Eingabe im Login-Form
    private Person currentUser;        // eingeloggte Person (Session)

    @Inject
    private UserService userService;

    public String login() {
        try {
            if (loginPersonId == null) {
                addMsg(FacesMessage.SEVERITY_ERROR, "Bitte Personen-ID angeben", null);
                return null;
            }
            Person p = userService.findPerson(loginPersonId);
            if (p == null) {
                addMsg(FacesMessage.SEVERITY_ERROR, "Unbekannte Personen-ID", String.valueOf(loginPersonId));
                return null;
            }
            currentUser = p;
            addMsg(FacesMessage.SEVERITY_INFO, "Erfolgreich angemeldet", getDisplayName());
            return null; // bleiben, Redirect im View via oncomplete
        } catch (Exception ex) {
            addMsg(FacesMessage.SEVERITY_ERROR, "Login fehlgeschlagen", ex.getMessage());
            return null;
        }
    }

    public String logout() {
        currentUser = null;
        loginPersonId = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return null;
    }

    /* ---- Helpers für Views ---- */

    public boolean isLoggedIn() { return currentUser != null; }
    public boolean isBerichtende() { return currentUser instanceof BerichtendePerson; }
    public boolean isBergende() { return currentUser instanceof BergendePerson; }

    public String getDisplayName() {
        if (currentUser == null) return "Gast";
        // Passe an deine Person-Felder an (Vorname/Nachname/Email)
        return currentUser.getEmail() != null ? currentUser.getEmail() : ("Person #" + currentUser.getId());
    }

    public Long getPersonId() { return currentUser != null ? currentUser.getId() : null; }

    // Wird im index.xhtml via <f:event ... listener=...> genutzt
    public void syncBerichtendeId(GeisternetzController ctrl) {
        if (isBerichtende() && ctrl.getBerichtendePersonId() == null) {
            ctrl.setBerichtendePersonId(getPersonId());
        }
    }
    public void syncBergendeId(GeisternetzController ctrl) {
        if (isBergende()) {
            ctrl.setBergendePersonId(getPersonId());
            // Für "geborgen/verschollen": wer klickt, ist identifizierbar
            if (ctrl.getBeliebigePersonId() == null) {
                ctrl.setBeliebigePersonId(getPersonId());
            }
        }
    }

    /* ---- Messaging ---- */
    private void addMsg(FacesMessage.Severity s, String sum, String det) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(s, sum, det));
    }

    /* ---- Getter/Setter ---- */
    public Long getLoginPersonId() { return loginPersonId; }
    public void setLoginPersonId(Long loginPersonId) { this.loginPersonId = loginPersonId; }
    public Person getCurrentUser() { return currentUser; }

    // EL-kompatible Kurzformen
    public boolean getLoggedIn() { return isLoggedIn(); }
    public boolean getBerichtende() { return isBerichtende(); }
    public boolean getBergende() { return isBergende(); }
    public String getDisplayNameProp() { return getDisplayName(); }
}
