package com.timeless.shoes.common.enums;

public enum StockMovementType {
    IN,
    OUT,
    ADJUST
}
StockMovement movement = new StockMovement(variant, StockMovementType.OUT.name(), quantity, reference);
