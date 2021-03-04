package hu.flowacademy.vajdasagbrand.persistence.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    ATTRACTION(1),
    GASTRONOMY(2),
    HOTEL(3),
    HOLIDAY(4),
    INFORMATION(5);

    private final int index;
}
