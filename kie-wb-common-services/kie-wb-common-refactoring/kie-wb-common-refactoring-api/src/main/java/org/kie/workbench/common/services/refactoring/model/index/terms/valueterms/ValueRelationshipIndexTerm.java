/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
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
package org.kie.workbench.common.services.refactoring.model.index.terms.valueterms;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.kie.workbench.common.services.refactoring.model.index.terms.RelationshipIndexTerm;
import org.kie.workbench.common.services.refactoring.service.RelationshipType;
import org.uberfire.commons.validation.PortablePreconditions;

@Portable
public class ValueRelationshipIndexTerm extends RelationshipIndexTerm implements ValueIndexTerm {

    private String relatedResOrPartName;
    private TermSearchType searchType;

    public ValueRelationshipIndexTerm() {
        //Errai marshalling
    }

    public ValueRelationshipIndexTerm(final String relatedResOrPartName, final RelationshipType type) {
        this(relatedResOrPartName, type, TermSearchType.NORMAL);
    }

    public ValueRelationshipIndexTerm(final String relatedResOrPartName, final RelationshipType type, final TermSearchType searchType) {
        super(type);
        this.relatedResOrPartName = PortablePreconditions.checkNotNull( "relatedResOrPartName", relatedResOrPartName );
        this.searchType = PortablePreconditions.checkNotNull( "searchType", searchType );
    }

    @Override
    public String getValue() {
        return relatedResOrPartName;
    }

    /* (non-Javadoc)
     * @see org.kie.workbench.common.services.refactoring.model.index.terms.valueterms.ExtendedValueIndexTerm#getTermType()
     */
    @Override
    public TermSearchType getSearchType() {
        return this.searchType;
    }

}