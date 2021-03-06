/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.workbench.common.services.backend.compiler.impl;

import java.io.Serializable;
import java.net.URI;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.drools.core.rule.KieModuleMetaInfo;
import org.kie.api.builder.KieModule;
import org.kie.workbench.common.services.backend.compiler.impl.kie.KieCompilationResponse;
import org.uberfire.java.nio.file.Path;

/***
 * Default implementation of a Kie Compilation response,
 * it contains a boolean flag as a result of the build, an optional String error message,
 * and a  List of String with the maven output
 */
public class DefaultKieCompilationResponse implements KieCompilationResponse,
                                                      Serializable {

    private KieModuleMetaInfo kieModuleMetaInfo;
    private KieModule kieModule;
    private Map<String, byte[]> projectClassLoaderStore;
    private Set<String> eventsTypeClasses;
    private DefaultCompilationResponse defaultResponse;

    public DefaultKieCompilationResponse(Boolean successful) {
        this(successful, Collections.emptyList());
    }

    public DefaultKieCompilationResponse(Boolean successful,
                                         List<String> mavenOutput) {
        this.defaultResponse = new DefaultCompilationResponse(successful,
                                                              mavenOutput,
                                                              null,
                                                              Collections.emptyList(),
                                                              Collections.emptyList());
    }

    public DefaultKieCompilationResponse(Boolean successful,
                                         List<String> mavenOutput,
                                         Path workingDir) {
        this.defaultResponse = new DefaultCompilationResponse(successful,
                                                              mavenOutput,
                                                              workingDir,
                                                              Collections.emptyList(),
                                                              Collections.emptyList());
    }

    public DefaultKieCompilationResponse(Boolean successful,
                                         List<String> mavenOutput,
                                         List<String> targetContent,
                                         List<String> projectDependencies,
                                         Path workingDir) {
        this.defaultResponse = new DefaultCompilationResponse(successful,
                                                              mavenOutput,
                                                              workingDir,
                                                              targetContent,
                                                              projectDependencies);
    }



    public DefaultKieCompilationResponse(Boolean successful,
                                         KieModuleMetaInfo kieModuleMetaInfo,
                                         KieModule kieModule,
                                         Map<String, byte[]> projectClassLoaderStore,
                                         List<String> mavenOutput,
                                         List<String> targetContent,
                                         List<String> projectDependencies,
                                         Path workingDir,
                                         Set<String> eventTypesClasses) {

        this.defaultResponse = new DefaultCompilationResponse(successful,
                                                              mavenOutput,
                                                              workingDir,
                                                              targetContent,
                                                              projectDependencies);
        this.kieModuleMetaInfo = kieModuleMetaInfo;
        this.kieModule = kieModule;
        this.projectClassLoaderStore = projectClassLoaderStore;
        this.eventsTypeClasses = eventTypesClasses;
    }

    @Override
    public Optional<KieModuleMetaInfo> getKieModuleMetaInfo() {
        return Optional.ofNullable(kieModuleMetaInfo);
    }

    @Override
    public Optional<KieModule> getKieModule() {
        return Optional.ofNullable(kieModule);
    }

    @Override
    public Boolean isSuccessful() {
        return defaultResponse.isSuccessful();
    }

    @Override
    public List<String> getMavenOutput() {
        return defaultResponse.getMavenOutput();
    }

    @Override
    public Optional<Path> getWorkingDir() {
        return defaultResponse.getWorkingDir();
    }

    @Override
    public List<String> getDependencies() {
        return defaultResponse.getDependencies();
    }

    @Override
    public List<URI> getDependenciesAsURI() {
        return defaultResponse.getDependenciesAsURI();
    }

    @Override
    public List<URL> getDependenciesAsURL() {
        return defaultResponse.getDependenciesAsURL();
    }

    @Override
    public List<String> getTargetContent() {
        return defaultResponse.getTargetContent();
    }

    @Override
    public List<URI> getTargetContentAsURI() {
        return defaultResponse.getTargetContentAsURI();
    }

    @Override
    public List<URL> getTargetContentAsURL() {
        return defaultResponse.getTargetContentAsURL();
    }

    @Override
    public Map<String, byte[]> getProjectClassLoaderStore() {
        return projectClassLoaderStore;
    }

    @Override
    public Set<String> getEventTypeClasses() {
        return eventsTypeClasses;
    }

    @Override
    public String toString() {
        return "DefaultKieCompilationResponse{" +
                "kieModuleMetaInfo=" + kieModuleMetaInfo +
                ", kieModule=" + kieModule +
                ", projectClassLoaderStore=" + projectClassLoaderStore +
                ", eventsTypeClasses=" + eventsTypeClasses +
                ", defaultResponse=" + defaultResponse +
                ", successful=" + isSuccessful() +
                ", mavenOutput=" + getMavenOutput() +
                ", workingDir=" + getWorkingDir() +
                ", dependencies=" + getDependencies() +
                ", dependenciesAsURI=" + getDependenciesAsURI() +
                ", dependenciesAsURL=" + getDependenciesAsURL() +
                ", targetContent=" + getTargetContent() +
                ", targetContentAsURI=" + getTargetContentAsURI() +
                ", targetContentAsURL=" + getTargetContentAsURL() +
                ", eventTypeClasses=" + getEventTypeClasses() +
                '}';
    }
}
