package com.example.assignment3.core.domain;

import com.example.assignment3.core.repo.ModulesRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java8.util.Lists;

import static java.lang.Integer.parseInt;
import static java8.util.Comparators.comparing;
import static java8.util.Comparators.thenComparing;
import static java8.util.stream.Collectors.toList;
import static java8.util.stream.StreamSupport.stream;

public class SampleModulesRepository implements ModulesRepository {

    private Map<String, Module> modules;

    public SampleModulesRepository(InputStream is) throws IOException {
        modules = new HashMap<>();
        readFrom(is, modules);
    }

    @Override
    public List<Module> getModules() {
        return stream(modules.values())
                .sorted(thenComparing(comparing(Module::getLevel), comparing(Module::getModuleCode)))
                .collect(toList());
    }

    @Override
    public List<Module> getProgrammeModules(String programme) {
        return stream(modules.values())
                .sorted(thenComparing(comparing(Module::getLevel), comparing(Module::getModuleCode)))
                .filter(module -> programme.equals(module.getProgramme()))
                .collect(toList());
    }

    @Override
    public List<Module> getSpecializationModules(String specialization) {
        return stream(modules.values())
                .sorted(thenComparing(comparing(Module::getLevel), comparing(Module::getModuleCode)))
                .filter(module -> module.getSpecializations().isEmpty() || module.getSpecializations().contains(specialization))
                .collect(toList());
    }

    @Override
    public List<Module> getYearModules(int year) {
        return stream(modules.values())
                .sorted(thenComparing(comparing(Module::getLevel), comparing(Module::getModuleCode)))
                .filter(module -> year == module.getYear())
                .collect(toList());
    }

    private void readFrom(InputStream source, Map<String, Module> destination) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(source))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] data = line.split(",");

                String moduleCode = data[1];
                String[] specializations = data[5].equals("None") ? new String[0] : data[5].split("/");
                String[] prerequisites = data[7].equals("None") ? new String[0] : data[7].split("/");
                List<Module> resolved = resolvePrerequisites(prerequisites);

                ModuleBuilder mb = new ModuleBuilder();
                mb.setModuleName(data[0])
                        .setModuleCode(moduleCode)
                        .setCredits(parseInt(data[2]))
                        .setYear(parseInt(data[3]))
                        .setSemester(parseInt(data[4]))
                        .setSpecializations(Lists.of(specializations))
                        .setProgramme(data[6])
                        .setPrerequisites(resolved)
                        .setLevel(parseInt(data[8]))
                        .setStatus(resolved.isEmpty() ? ModuleStatus.NONE : ModuleStatus.LOCKED);

                destination.put(moduleCode, mb.build());
            }
        }
    }

    private List<Module> resolvePrerequisites(String[] moduleCodes) {
        List<Module> resolved = new ArrayList<>();
        for(String moduleCode : moduleCodes)
            resolved.add(modules.get(moduleCode));
        return resolved;
    }
}