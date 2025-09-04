package entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "person_typ", discriminatorType = DiscriminatorType.STRING)
@Table(name = "person")
public abstract class Person implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) private String vorname;
    @Column(nullable = false) private String nachname;

    @Column(unique = true, nullable = false)
    private String email;

    private String telefon;

    // --- Getter/Setter ---
    public Long getId() { return id; }
    public String getVorname() { return vorname; }
    public void setVorname(String vorname) { this.vorname = vorname; }
    public String getNachname() { return nachname; }
    public void setNachname(String nachname) { this.nachname = nachname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }
}
