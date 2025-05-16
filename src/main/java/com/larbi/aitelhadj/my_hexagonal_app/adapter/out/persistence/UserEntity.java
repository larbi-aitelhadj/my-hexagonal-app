package com.larbi.aitelhadj.my_hexagonal_app.adapter.out.persistence;

import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

/**
 * @larbi.aitelhadj
 *
 */
@Data
@Entity
@Table(name = "User", schema = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;

}
