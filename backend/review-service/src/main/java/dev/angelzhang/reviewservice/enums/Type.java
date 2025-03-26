package dev.angelzhang.reviewservice.enums;

import lombok.Getter;

@Getter
public enum Type {
    MOVIE("movie"),
    TV("tv");

    private final String type;

    Type(String type) {
        this.type = type;
    }
}
