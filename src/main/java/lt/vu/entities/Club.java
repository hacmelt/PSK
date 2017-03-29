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

@Entity
@Table(name = "CLUB")
@NamedQueries({
    @NamedQuery(name = "Club.findAll", query = "SELECT c FROM Club c"),
    @NamedQuery(name = "Club.findById", query = "SELECT c FROM Club c WHERE c.id = :id"),
    @NamedQuery(name = "Club.findByName", query = "SELECT c FROM Club c WHERE c.title = :title"),
    @NamedQuery(name = "Club.findByOptLockVersion", query = "SELECT c FROM Club c WHERE c.optLockVersion = :optLockVersion")})
@Getter
@Setter
@EqualsAndHashCode(of = "title")
@ToString(of = {"id", "title"})
public class Club implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Size(min = 4, max = 50)
    @Column(name = "TITLE")
    private String title;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer optLockVersion;

    @ManyToMany(mappedBy = "clubList")
    private List<Client> clientList = new ArrayList<>();
}
