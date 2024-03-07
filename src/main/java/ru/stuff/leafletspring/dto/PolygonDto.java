package ru.stuff.leafletspring.dto;

import lombok.Data;

import java.util.List;

@Data
public class PolygonDto {
    private List<List<Coordinate>> coordinates;
}
