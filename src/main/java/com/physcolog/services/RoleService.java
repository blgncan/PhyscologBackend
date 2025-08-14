package com.physcolog.services;

import com.physcolog.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
}
