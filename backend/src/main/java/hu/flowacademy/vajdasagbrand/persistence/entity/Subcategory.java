package hu.flowacademy.vajdasagbrand.persistence.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Subcategory {
    HONOURABLES(1),
    FAMOUS_BUILDINGS(2),
    MUSEUMS(3);

    private final int index;
}
