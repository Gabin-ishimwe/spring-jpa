package com.gabinishimwe.springdatajpa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignRoleDto {
    private Long userId;
    private Long roleId;
}
