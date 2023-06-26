package com.example.Survey.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Survey.entity.Answer;

@Repository
public interface AnswerDao extends JpaRepository<Answer, Integer> {

}
