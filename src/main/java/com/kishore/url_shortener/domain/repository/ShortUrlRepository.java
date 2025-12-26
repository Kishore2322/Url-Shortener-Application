package com.kishore.url_shortener.domain.repository;

import com.kishore.url_shortener.domain.entity.ShortUrl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {

//    @Query("select su from ShortUrl su where su.isPrivate = false order by su.createdAt desc")
//    @EntityGraph(attributePaths = {"createdBy"})
    @Query("select su from ShortUrl su left join fetch su.createdBy where su.isPrivate = false")
    Page<ShortUrl> findPublicShortUrls(Pageable pageable);

    boolean existsByShortKey(String shortKey);

    Optional<ShortUrl> findByShortKey(String shortKey);

    Page<ShortUrl> findByCreatedById(Long userId, Pageable pageable);
}
