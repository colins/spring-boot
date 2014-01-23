/**
 * Created by pivotal on 1/22/14.
 */
package com.springapp.mvc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}