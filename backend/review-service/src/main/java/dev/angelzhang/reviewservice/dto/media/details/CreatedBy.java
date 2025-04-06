package dev.angelzhang.reviewservice.dto.media.details;

public record CreatedBy(
         Integer id,
         String credit_id,
         String name,
         String original_name,
         Integer gender,
         String profile_path
) {
}
