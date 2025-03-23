package dev.angelzhang.mediaservice.dto.searchMulti;

import java.util.ArrayList;

public record SearchMultiAPIRequest(
        Integer page,
        ArrayList<SearchMultiAPIBody> results,
        Integer total_pages,
        Integer total_results
) {
}