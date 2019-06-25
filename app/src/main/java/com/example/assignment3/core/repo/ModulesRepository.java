package com.example.assignment3.core.repo;

import com.example.assignment3.core.domain.Module;

import java.util.List;

public interface ModulesRepository {

    List<Module> getModules();

    List<Module> getProgrammeModules(String programme);

    List<Module> getSpecializationModules(String specialization);

    List<Module> getYearModules(int year);

    List<Module> getModules(int year, String programme, String specialization);
}