package org.labs.lab4back.contollers;

import jakarta.servlet.http.HttpServletResponse;
import org.labs.lab4back.entities.Point;
import org.labs.lab4back.models.PointCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;

@Controller
public class ApiController {
    @Autowired
    private PointCollection collection;
    @PostMapping("/areaCheck")
    @ResponseBody
    public List<Point> areaCheckPOST(@RequestParam double x, @RequestParam double y,@RequestParam double r, HttpServletResponse response) {
        try {
            collection.addPoint(x, y, r);
            return collection.getPoints();
        }
        catch (AuthenticationException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
    }
    @GetMapping("/areaCheck")
    @ResponseBody
    public List<Point> areaCheckGET(HttpServletResponse response) {
        try {
            return collection.getPoints();
        }
        catch (AuthenticationException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
    }
    @DeleteMapping("/areaCheck")
    @ResponseBody
    public List<Point> areaCheckDELETE(HttpServletResponse response) {
        try {
            return collection.clear();
        }
        catch (AuthenticationException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
    }

}
