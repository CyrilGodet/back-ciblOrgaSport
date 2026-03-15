package com.glop.cibl_orga_sport.repository;

import com.glop.cibl_orga_sport.data.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryDao extends JpaRepository<History, Long> {
}
