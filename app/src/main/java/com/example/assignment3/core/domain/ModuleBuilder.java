package com.example.assignment3.core.domain;

import java.util.List;

public class ModuleBuilder {

    private String moduleName;
    private String moduleCode;
    private int credits;
    private int year;
    private int semester;
    private List<String> specializations;
    private String programme;
    private List<Module> prerequisites;

    public ModuleBuilder() {}

    public ModuleBuilder setModuleName(String moduleName) {
        this.moduleName = moduleName;
        return this;
    }

    public ModuleBuilder setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
        return this;
    }

    public ModuleBuilder setCredits(int credits) {
        this.credits = credits;
        return this;
    }

    public ModuleBuilder setYear(int year) {
        this.year = year;
        return this;
    }

    public ModuleBuilder setSemester(int semester) {
        this.semester = semester;
        return this;
    }

    public ModuleBuilder setSpecializations(List<String> specializations) {
        this.specializations = specializations;
        return this;
    }

    public ModuleBuilder setProgramme(String programme) {
        this.programme = programme;
        return this;
    }

    public ModuleBuilder setPrerequisites(List<Module> prerequisites) {
        this.prerequisites = prerequisites;
        return this;
    }

    public Module build() {
        BuiltModule module = new BuiltModule();
        module.moduleName = moduleName;
        module.moduleCode = moduleCode;
        module.credits = credits;
        module.year = year;
        module.semester = semester;
        module.specializations = specializations;
        module.programme = programme;
        module.prerequisites = prerequisites;
        return module;
    }

    private static class BuiltModule implements Module {

        private String moduleName;
        private String moduleCode;
        private int credits;
        private int year;
        private int semester;
        private List<String> specializations;
        private String programme;
        private List<Module> prerequisites;

        @Override
        public String getModuleName() {
            return moduleName;
        }

        @Override
        public String getModuleCode() {
            return moduleCode;
        }

        @Override
        public int getCredits() {
            return credits;
        }

        @Override
        public int getYear() {
            return year;
        }

        @Override
        public int getSemester() {
            return semester;
        }

        @Override
        public List<String> getSpecializations() {
            return specializations;
        }

        @Override
        public String getProgramme() {
            return programme;
        }

        @Override
        public List<Module> getPrerequisites() {
            return prerequisites;
        }
    }
}