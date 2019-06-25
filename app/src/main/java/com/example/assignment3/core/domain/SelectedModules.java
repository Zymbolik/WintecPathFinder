package com.example.assignment3.core.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java8.util.stream.Collectors.toList;
import static java8.util.stream.StreamSupport.stream;

public class SelectedModules {

    private Map<String, Module> completedModules;
    private Map<String, Module> currentModules;
    private Map<String, Module> lockedModules;

    public SelectedModules() {
        completedModules = new HashMap<>();
        currentModules = new HashMap<>();
        lockedModules = new HashMap<>();

    }

    public boolean isCompleted(String moduleCode) {
        return completedModules.containsKey(moduleCode);
    }

    public boolean inProgress(String moduleCode) {
        return currentModules.containsKey(moduleCode);
    }

    public boolean isLocked(String moduleCode) {
        return lockedModules.containsKey(moduleCode);
    }

    public boolean prerequisitesSatisfied(Module module) {
        List<String> completed = stream(getCompletedModules()).map(Module::getModuleCode).collect(toList());
        List<String> prerequisites = stream(module.getPrerequisites()).map(Module::getModuleCode).collect(toList());
        return completed.containsAll(prerequisites);
    }

    public void addModule(Module module) {
        String moduleCode = module.getModuleCode();
        if(!isCompleted(moduleCode) && !inProgress(moduleCode) && !isLocked(moduleCode))
            moveModuleToCorrectGroup(module);
    }

    public void removeModule(Module module) {
        String moduleCode = module.getModuleCode();
        if(inProgress(moduleCode))
            currentModules.remove(moduleCode);
        else if(isLocked(moduleCode))
            lockedModules.remove(moduleCode);
    }

    public void completeModule(Module module) {
        String moduleCode = module.getModuleCode();

        // check whether the module was in progress before completion.
        if(inProgress(moduleCode)) {

            // move from progress to completed.
            currentModules.remove(moduleCode);
            ModuleBuilder mb = new ModuleBuilder(module).setStatus(ModuleStatus.COMPLETE);
            completedModules.put(moduleCode, mb.build());

            // try to move locked modules.
            // wrapped in ArrayList to prevent concurrent modification exception.
            stream(new ArrayList<>(lockedModules.values())).forEach(this::moveModuleToCorrectGroup);
        }
    }

    public List<Module> getLockedModules() {
        return stream(lockedModules.values()).collect(toList());
    }

    public List<Module> getCurrentModules() {
        return stream(currentModules.values()).collect(toList());
    }

    public List<Module> getCompletedModules() {
        return stream(completedModules.values()).collect(toList());
    }

    private void moveModuleToCorrectGroup(Module module) {
        // create copy of module.
        Module copy = new ModuleBuilder(module).build();

        // removing from the copy should not affect the original.
        List<Module> prerequisites = copy.getPrerequisites();
        for (String completedModuleCode : completedModules.keySet()) {

            // To remove a completed module from the prerequisites.
            Iterator<Module> iterator = prerequisites.iterator();
            while (iterator.hasNext()) {
                Module next = iterator.next();
                if (next.getModuleCode().equals(completedModuleCode))
                    iterator.remove();
            }
        }

        // try remove module from locked if it exists and add to current modules.
        if (prerequisites.isEmpty()) {
            lockedModules.remove(copy.getModuleCode());
            Module current = new ModuleBuilder(copy).setStatus(ModuleStatus.PROGRESS).build();
            currentModules.put(current.getModuleCode(), current);
        }

        // add the module to the locked modules as it has prerequisites.
        else {
            Module locked = new ModuleBuilder(copy).setStatus(ModuleStatus.LOCKED).build();
            lockedModules.put(locked.getModuleCode(), locked);
        }
    }

    public List<Module> getModules() {
        List<Module> modules = new ArrayList<>();
        modules.addAll(getCompletedModules());
        modules.addAll(getCurrentModules());
        modules.addAll(getLockedModules());
        return modules;
    }
}
