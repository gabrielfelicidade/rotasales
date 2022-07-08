package br.com.rotaractsorocabaleste.rotasales.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@With
public class ChangePasswordDTO implements Serializable {

    private static final long serialVersionUID = 1312285169570532933L;

    private String password;

}
