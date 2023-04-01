package com.campera.app3idadefacil.repository;

import com.campera.app3idadefacil.model.WeeklyPosology;
import com.campera.app3idadefacil.model.WeeklyPosologyDateTime;
import com.campera.app3idadefacil.model.WeeklyPosologyDateTimeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeeklyPosologyDateTimeRepository
        extends JpaRepository<WeeklyPosologyDateTime, WeeklyPosologyDateTimeId> {
}
