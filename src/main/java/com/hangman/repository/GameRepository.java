package com.hangman.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hangman.model.Game;

public interface GameRepository extends JpaRepository<Game, String> {

}
