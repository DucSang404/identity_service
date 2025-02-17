package com.devteria.identity_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jdk.jfr.Name;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.security.Permission;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    String name;
    String description;

    @ManyToMany
    Set<PermissionEntity> permissions;
}

