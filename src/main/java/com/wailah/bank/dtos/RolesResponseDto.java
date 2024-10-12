package com.wailah.bank.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class RolesResponseDto {

    private Integer id;
    private String roleName;

}
