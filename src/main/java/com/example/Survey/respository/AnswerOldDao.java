package com.example.Survey.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Survey.entity.AnswerOld;

@Repository
public interface AnswerOldDao extends JpaRepository<AnswerOld, Integer> {

}
