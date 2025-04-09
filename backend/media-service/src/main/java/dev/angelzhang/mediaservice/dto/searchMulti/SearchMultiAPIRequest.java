package dev.angelzhang.mediaservice.dto.searchMulti;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.ArrayList;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record SearchMultiAPIRequest(
        Integer page,
        ArrayList<SearchMultiAPIBody> results,
        Integer totalPages,
        Integer totalResults
) {
}