package org.labs.lab4back.repositories;

import org.labs.lab4back.entities.Point;
import org.labs.lab4back.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;
import java.util.Optional;

@Repository
public interface IUserRepository extends CrudRepository<User, Integer>
{
    Optional<User> findByUsernameAndPassword(String username,String password);
    Optional<User> findByUsername(String username);
}
