package com.example.assignment3.core.domain;

import java.util.List;

class ModuleBuilder {

    private String moduleName;
    private String moduleCode;
    private int level;
    private int credits;
    private int year;
    private int semester;
    private List<String> specializations;
    private String programme;
    private List<Module> prerequisites;
    private ModuleStatus status;

    ModuleBuilder() {}

    ModuleBuilder setModuleName(String moduleName) {
        this.moduleName = moduleName;
        return this;
    }

    ModuleBuilder setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
        return this;
    }

    ModuleBuilder setLevel(int level) {
        this.level = level;
        return this;
    }

    ModuleBuilder setCredits(int credits) {
        this.credits = credits;
        return this;
    }

    ModuleBuilder setYear(int year) {
        this.year = year;
        return this;
    }

    ModuleBuilder setSemester(int semester) {
        this.semester = semester;
        return this;
    }

    ModuleBuilder setSpecializations(List<String> specializations) {
        this.specializations = specializations;
        return this;
    }

    ModuleBuilder setProgramme(String programme) {
        this.programme = programme;
        return this;
    }

    ModuleBuilder setPrerequisites(List<Module> prerequisites) {
        this.prerequisites = prerequisites;
        return this;
    }

    ModuleBuilder setStatus(ModuleStatus status) {
        this.status = status;
        return this;
    }

    Module build() {
        BuiltModule module = new BuiltModule();
        module.moduleName = moduleName;
        module.moduleCode = moduleCode;
        module.level = level;
        module.credits = credits;
        module.year = year;
        module.semester = semester;
        module.specializations = specializations;
        module.programme = programme;
        module.prerequisites = prerequisites;
        module.status = status;
        return module;
    }

    private static class BuiltModule implements Module {

        private String moduleName;
        private String moduleCode;
        private int level;
        private int credits;
        private int year;
        private int semester;
        private List<String> specializations;
        private String programme;
        private List<Module> prerequisites;
        private ModuleStatus status;

        @Override
        public String getModuleName() {
            return moduleName;
        }

        @Override
        public String getModuleCode() {
            return moduleCode;
        }

        @Override
        public int getLevel() {
            return level;
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

        @Override
        public ModuleStatus getStatus() {
            return status;
        }
    }
}