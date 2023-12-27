package com.sharkend.jwttemplate.Entity.Token;

import com.sharkend.jwttemplate.Entity.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Token {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Integer id;
    private String token;
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;
    private boolean revoked;
    private boolean expired;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
