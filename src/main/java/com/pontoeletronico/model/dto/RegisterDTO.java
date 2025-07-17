package com.pontoeletronico.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Classe para registro de novos usuários")
public class RegisterDTO {
    
    @Schema(description = "Email do usuário")
    @NotBlank(message = "O email não pode estar vazio")
    @Email(message = "Formato de email inválido")
    private String email;

    @Schema(description = "Senha do usuário")
    @NotBlank(message = "A senha não pode estar vazia")
    private String password;

    @Schema(description = "Nome do usuário")
    @NotBlank(message = "O nome não pode estar vazio")
    private String name;

}
