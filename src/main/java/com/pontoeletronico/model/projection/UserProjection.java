package com.pontoeletronico.model.projection;

import com.pontoeletronico.model.enums.UserRole;

public interface UserProjection {
    
    String getId();
    String getNome();
    String getEmail();
    UserRole getRole();
    String getStatus();
} 
