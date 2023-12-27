package com.sharkend.jwttemplate.Repository;

import com.sharkend.jwttemplate.Entity.Token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    @Query("SELECT t FROM Token t INNER JOIN User u ON t.user.id = u.id" +
            " WHERE u.id = :userId AND (t.expired = false AND t.revoked = false)")
        //todo tutorial uses OR instead of AND in bracket -- create pull request
    List<Token> findAllValidTokensByUser(Integer userId);

    Optional<Token> findByToken(String token);
}
