package com.example.assignment3.core.domain;

import java.util.List;

public interface Module {

    String getModuleName();

    String getModuleCode();

    int getLevel();

    int getCredits();

    int getYear();

    int getSemester();

    List<String> getSpecializations();

    String getProgramme();

    List<Module> getPrerequisites();

    ModuleStatus getStatus();
}