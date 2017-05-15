/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.vu.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.johnzon.mapper.JohnzonIgnore;

@Entity
@Table(name = "CLIENT")
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT s FROM Client s"),
    @NamedQuery(name = "Client.findById", query = "SELECT s FROM Client s WHERE s.id = :id"),
    @NamedQuery(name = "Client.findByFirstName", query = "SELECT s FROM Client s WHERE s.firstName LIKE :firstName"),
    @NamedQuery(name = "Client.findByLastName", query = "SELECT s FROM Client s WHERE s.lastName LIKE :lastName"),
})
@Getter
@Setter
@EqualsAndHashCode(of = "registrationNo")
@ToString(of = {"id", "firstName", "lastName"})
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Size(min = 3, max = 20)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Size(min = 3, max = 20)
    @Column(name = "LAST_NAME")
    private String lastName;

    @Size(min = 3, max = 20)
    @Column(name = "REGISTRATION_NO")
    private String registrationNo;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer optLockVersion;

    @JoinTable(name = "CLIENT_CLUB", joinColumns = {
        @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "CLUB_ID", referencedColumnName = "ID")})
    @ManyToMany
    @JohnzonIgnore
    private List<Club> clubList = new ArrayList<>();

    @JoinColumn(name = "CITY_ID", referencedColumnName = "ID")
    @ManyToOne
    @JohnzonIgnore
    private City city;

}
