package ru.stuff.leafletspring.dto;

import lombok.Data;

import java.util.List;

@Data
public class PolylineDto {
    private List<Coordinate> coordinates;
}
