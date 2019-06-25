package com.example.assignment3.core.repo;

import com.example.assignment3.core.domain.Module;

import java.util.List;

public interface ModulesRepository {

    List<Module> getModules();

    Module getModuleByCode(String moduleCode);
}