package org.labs.lab4back.models;

import jakarta.transaction.Transactional;
import org.labs.lab4back.contollers.ApiController;
import org.labs.lab4back.entities.Point;
import org.labs.lab4back.repositories.IPointRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import javax.naming.AuthenticationException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@SessionScope
public class PointCollection implements Serializable {
    static final Logger log =
            LoggerFactory.getLogger(ApiController.class);
    private final IPointRepository pointRepo;
    private final Auth auth;
    private final List<Point> points;

    public List<Point> getPoints() throws AuthenticationException {
        if (auth.getUser() != null) {
            return points;
        } else throw new AuthenticationException();
    }

    public PointCollection(@Autowired IPointRepository pointRepo, @Autowired Auth auth) {
        this.auth = auth;
        points = new ArrayList<>();
        if (auth.getUser() != null) {
            Set<Point> usrpoints = auth.getUser().getPoints();
            if (usrpoints != null) points.addAll(usrpoints);
        }
        this.pointRepo = pointRepo;
    }

    public void refresh() {
        points.clear();
        if (auth.getUser() != null) {
            Set<Point> usrpoints = auth.getUser().getPoints();
            if (usrpoints != null) points.addAll(usrpoints);
        }
    }
    @Transactional
    public void addPoint(double x, double y, double r) throws AuthenticationException {
        if (auth.getUser() != null) {
            Point point = new Point(x, y, r, auth.getUser());
            pointRepo.save(point);
            points.add(point);
        } else throw new AuthenticationException();
    }
    @Transactional
    public List<Point> clear() throws AuthenticationException {
        if (auth.getUser() != null) {
            pointRepo.removePointByUser(auth.getUser());
            points.clear();
        } else throw new AuthenticationException();
        return getPoints();
    }
}
