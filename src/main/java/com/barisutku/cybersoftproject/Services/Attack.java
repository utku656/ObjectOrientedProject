package com.barisutku.cybersoftproject.Services;

import com.barisutku.cybersoftproject.models.Position;
import org.springframework.stereotype.Service;

@Service
public interface Attack {
    void attack(Position position);
}
