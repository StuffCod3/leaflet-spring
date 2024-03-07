package ru.stuff.leafletspring.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.stuff.leafletspring.dto.Coordinate;
import ru.stuff.leafletspring.dto.PolygonDto;
import ru.stuff.leafletspring.dto.PolylineDto;

import java.util.List;

@Controller
public class MainController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/getCordPolygons")
    public ResponseEntity<?> handleCoordinates(@RequestBody PolygonDto polygon){
        System.out.println("\n"+polygon.getCoordinates()+"\n");

        for (int i = 0; i < polygon.getCoordinates().size(); i++){
            List<Coordinate> listCoordinates = polygon.getCoordinates().get(i);
            int numberPoint = 1;
            for (Coordinate listCoordinate : listCoordinates) {
                System.out.println(numberPoint + "-я точка");
                System.out.println("Широта: " + listCoordinate.getLat() +
                        "; Долгота: " + listCoordinate.getLng() + "\n");
                numberPoint++;
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/getCordPolyline")
    public ResponseEntity<?> handleCoordinatesPolyline(@RequestBody PolylineDto polyline){
        System.out.println("\n"+polyline.getCoordinates()+"\n");
        for (int i = 0; i < polyline.getCoordinates().size(); i++){
            System.out.println(i+1 + "-я точка");
            System.out.println("Широта: " + polyline.getCoordinates().get(i).getLat() +
                    "; Долгота: " + polyline.getCoordinates().get(i).getLng() + "\n");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
