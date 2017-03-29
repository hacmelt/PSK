package lt.vu.usecases.mybatis.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Client {

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer cityId;

    // Rankomis prirašyti:
    private City city;
    private List<Club> clubs;
}