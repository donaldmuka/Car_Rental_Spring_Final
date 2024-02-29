package com.carrental.carrentalapp.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BRANCHES")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private Long id;
    @Column(name = "rental_name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "rental_branch")
    private Rental rental;
    @OneToMany(mappedBy = "branch")
    private Set<Employee> employees;
    @OneToMany(mappedBy = "branch",fetch = FetchType.EAGER)
    private Set<Car>cars;
    @Column(name = "branch_address")
    private String address;
    @OneToMany(mappedBy = "id")
    private Set<Department> department;

}


