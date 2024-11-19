package org.labs.lab4back.repositories;

import org.labs.lab4back.entities.Point;
import org.labs.lab4back.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPointRepository extends CrudRepository<Point, Integer> {
    void removePointByUser(User user);
}
