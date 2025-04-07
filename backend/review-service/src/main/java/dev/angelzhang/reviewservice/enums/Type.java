package dev.angelzhang.reviewservice.enums;

import lombok.Getter;

@Getter
public enum Type {
    MOVIE("movie"),
    TV("tv");

    private final String label;

    Type(String label) {
        this.label = label;
    }

    public static Type fromString(String value) {
        for (Type type : Type.values()) {
            if (type.label.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown mediaType: " + value);
    }

    public String toString() {
        return label;
    }
}
