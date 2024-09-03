package com.sander.microservices.blacklist.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BlacklistCheckHistory {
    @Id
    @SequenceGenerator(
        name = "blacklist_id_sequence",
        sequenceName = "blacklist_id_sequence"
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "blacklist_id_sequence"
    )
    private Long id;
    private Long customerId;
    private Boolean isBlacklisted;
    private LocalDateTime created;
}
