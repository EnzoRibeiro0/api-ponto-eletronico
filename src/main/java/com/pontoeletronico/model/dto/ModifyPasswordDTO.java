package com.pontoeletronico.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Classe para os Usuários modificarem sua senha")
public class ModifyPasswordDTO {

    @Schema(description = "Senha antiga do usuário")
    @NotBlank(message = "A senha antiga não pode estar vazia")
    private String oldpassword;

    @Schema(description = "Nova senha do usuário")
    @NotBlank(message = "A nova senha não pode estar vazia")
    private String newpassword;

    @Schema(description = "Confirmação da nova senha do usuário")
    @NotBlank(message = "A confirmação da nova senha não pode estar vazia")
    private String confirmedPassword;

    
}
