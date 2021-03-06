/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.screens.library.client.screens.project;

import javax.inject.Inject;
import javax.inject.Named;

import com.google.gwt.event.dom.client.ClickEvent;
import elemental2.dom.HTMLAnchorElement;
import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLLIElement;
import org.jboss.errai.common.client.dom.elemental2.Elemental2DomUtil;
import org.jboss.errai.ui.client.local.api.elemental2.IsElement;
import org.jboss.errai.ui.client.local.spi.TranslationService;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.kie.workbench.common.screens.library.client.resources.i18n.LibraryConstants;
import org.kie.workbench.common.screens.projecteditor.client.resources.ProjectEditorResources;
import org.uberfire.ext.widgets.common.client.common.BusyPopup;
import org.uberfire.ext.widgets.common.client.common.popups.errors.ErrorPopup;

@Templated
public class ProjectView implements ProjectScreen.View,
                                    IsElement {

    public static final String ACTIVE = "active";
    private ProjectScreen presenter;

    @Inject
    private TranslationService translationService;

    @Inject
    private Elemental2DomUtil domUtil;

    @Inject
    @DataField("title")
    private HTMLDivElement title;

    @Inject
    @DataField("assets-link")
    private HTMLAnchorElement assetsTabLink;

    @Inject
    @DataField("assets-tab")
    private HTMLLIElement assetsTabItem;

    @Inject
    @DataField("contributors-link")
    private HTMLAnchorElement contributorsTabLink;

    @Inject
    @DataField("contributors-tab")
    private HTMLLIElement contributorsTabItem;

    @Inject
    @DataField("metrics-link")
    private HTMLAnchorElement metricsTabLink;

    @Inject
    @DataField("metrics-tab")
    private HTMLLIElement metricsTabItem;

    @Inject
    @DataField("settings-link")
    private HTMLAnchorElement settingsTabLink;

    @Inject
    @DataField("settings-tab")
    private HTMLLIElement settingsTabItem;

    @Inject
    @DataField("main-container")
    private HTMLDivElement mainContainer;

    @Inject
    @Named("span")
    @DataField("assets-count")
    private HTMLElement assetsCount;

    @Inject
    @Named("span")
    @DataField("actions-dropdown")
    private HTMLElement actionsDropdown;

    @Inject
    @Named("span")
    @DataField("contributors-count")
    private HTMLElement contributorsCount;

    @Inject
    @DataField("delete-project")
    private HTMLAnchorElement deleteProject;

    @Inject
    @DataField("import-asset-action")
    private HTMLAnchorElement importAsset;

    @Inject
    @DataField("add-asset-action")
    private HTMLAnchorElement addAsset;

    @Inject
    @DataField("rename")
    private HTMLAnchorElement rename;

    @Inject
    @DataField("edit-contributors")
    private HTMLAnchorElement editContributors;

    @Inject
    @DataField("duplicate")
    private HTMLAnchorElement duplicate;

    @Inject
    @DataField("reimport")
    private HTMLAnchorElement reimport;

    @Inject
    @DataField("build")
    private HTMLButtonElement build;

    @Inject
    @DataField("deploy")
    private HTMLButtonElement deploy;

    @Inject
    @DataField("main-actions")
    private HTMLDivElement mainActions;

    @Override
    public void addMainAction(final IsElement action) {
        mainActions.appendChild(action.getElement());
    }

    @Override
    public void setAssetsCount(int count) {
        assetsCount.textContent = String.valueOf(count);
    }

    @Override
    public void setContributorsCount(int count) {
        contributorsCount.textContent = String.valueOf(count);
    }

    @Override
    public void setContent(HTMLElement content) {
        this.domUtil.removeAllElementChildren(this.mainContainer);
        this.mainContainer.appendChild(content);
    }

    @Override
    public void setTitle(String projectName) {
        this.title.textContent = projectName;
    }

    @Override
    public void setAddAssetVisible(boolean visible) {
        this.addAsset.hidden = !visible;
    }

    @Override
    public void setImportAssetVisible(boolean visible) {
        this.importAsset.hidden = !visible;
    }

    @Override
    public void setEditContributorsVisible(boolean visible) {
        this.editContributors.hidden = !visible;
    }

    @Override
    public void setDuplicateVisible(boolean visible) {
        this.duplicate.hidden = !visible;
    }

    @Override
    public void setReimportVisible(boolean visible) {
        this.reimport.hidden = !visible;
    }

    @Override
    public void setBuildEnabled(boolean enabled) {
        this.build.disabled = !enabled;
    }

    @Override
    public void setDeployEnabled(boolean enabled) {
        this.deploy.disabled = !enabled;
    }

    @Override
    public void setDeleteProjectVisible(boolean visible) {
        this.deleteProject.hidden = !visible;
    }

    @Override
    public void setActionsVisible(boolean visible) {
        this.actionsDropdown.hidden = !visible;
    }

    @Override
    public String getLoadingMessage() {
        return translationService.getTranslation(LibraryConstants.Loading);
    }

    @Override
    public String getItemSuccessfullyDuplicatedMessage() {
        return translationService.getTranslation(LibraryConstants.ItemSuccessfullyDuplicated);
    }

    @Override
    public String getReimportSuccessfulMessage() {
        return translationService.getTranslation(LibraryConstants.ReimportSuccessful);
    }

    @Override
    public void init(ProjectScreen presenter) {
        this.presenter = presenter;
    }

    @EventHandler("assets-link")
    public void clickAssetsTab(final ClickEvent clickEvent) {
        this.deactivateAllTabs();
        this.activate(this.assetsTabItem);
        this.presenter.showAssets();
    }

    @EventHandler("contributors-link")
    public void clickContributorsTab(final ClickEvent clickEvent) {
        this.deactivateAllTabs();
        this.activate(this.contributorsTabItem);
        this.presenter.showContributors();
    }

    @EventHandler("metrics-link")
    public void clickMetricsTab(final ClickEvent clickEvent) {
        this.deactivateAllTabs();
        this.activate(this.metricsTabItem);
        this.presenter.showMetrics();
    }

    @EventHandler("settings-link")
    public void clickSettingsTab(final ClickEvent clickEvent) {
        this.deactivateAllTabs();
        this.activate(this.settingsTabItem);
        this.presenter.showSettings();
    }

    @EventHandler("delete-project")
    public void delete(final ClickEvent event) {
        presenter.delete();
    }

    @EventHandler("import-asset-action")
    public void importAsset(final ClickEvent event) {
        presenter.importAsset();
    }

    @EventHandler("rename")
    public void rename(final ClickEvent event) {
        presenter.rename();
    }

    @EventHandler("edit-contributors")
    public void editContributors(final ClickEvent event) {
        presenter.editContributors();
    }

    @EventHandler("build")
    public void build(final ClickEvent event) {
        presenter.build();
    }

    @EventHandler("deploy")
    public void deploy(final ClickEvent event) {
        presenter.deploy();
    }

    @EventHandler("add-asset-action")
    public void addAsset(final ClickEvent event) {
        presenter.addAsset();
    }

    @EventHandler("duplicate")
    public void duplicate(final ClickEvent event) {
        presenter.duplicate();
    }

    @EventHandler("reimport")
    public void reimport(final ClickEvent event) {
        presenter.reimport();
    }

    private void activate(HTMLLIElement element) {
        element.classList.add(ACTIVE);
    }

    private void deactivate(HTMLLIElement element) {
        element.classList.remove(ACTIVE);
    }

    private void deactivateAllTabs() {
        this.deactivate(this.assetsTabItem);
        this.deactivate(this.contributorsTabItem);
        this.deactivate(this.metricsTabItem);
        this.deactivate(this.settingsTabItem);
    }

    @Override
    public void showABuildIsAlreadyRunning() {
        ErrorPopup.showMessage(ProjectEditorResources.CONSTANTS.ABuildIsAlreadyRunning());
    }

    @Override
    public void showBusyIndicator(String message) {
        BusyPopup.showMessage(message);
    }

    @Override
    public void hideBusyIndicator() {
        BusyPopup.close();
    }
}
