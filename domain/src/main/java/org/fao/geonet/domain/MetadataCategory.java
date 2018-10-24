/*
 * Copyright (C) 2001-2016 Food and Agriculture Organization of the
 * United Nations (FAO-UN), United Nations World Food Programme (WFP)
 * and United Nations Environment Programme (UNEP)
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301, USA
 *
 * Contact: Jeroen Ticheler - FAO - Viale delle Terme di Caracalla 2,
 * Rome - Italy. email: geonetwork@osgeo.org
 */

package org.fao.geonet.domain;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Cacheable;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.fao.geonet.entitylistener.MetadataCategoryEntityListenerManager;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;

/**
 * A Metadata category. This is separate from any category listed in 
 * the metadata xml itself and is geonetwork specific.
 *
 * @author Jesse
 */
@Entity
@Access(AccessType.PROPERTY)
@Cacheable
@Table(name = "Categories")
@EntityListeners(MetadataCategoryEntityListenerManager.class)
@SequenceGenerator(name = MetadataCategory.ID_SEQ_NAME, initialValue = 100, allocationSize = 1)
public class MetadataCategory extends Localized {
    @Transient
	private static final long serialVersionUID = 2336622654731250743L;

	@Transient
	private static final Set<String> EXCLUDE_FROM_XML = Sets.newHashSet("records");
	@Transient 
    protected static final String ID_SEQ_NAME = "metadata_category_id_seq";
	
	private Integer _id;
    private String _name;
    private Set<Metadata> _records = new HashSet<Metadata>();
    
    /**
     * The id of the category. This is a generated value and not controlled by the developer.
     *
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQ_NAME)
    public Integer getId() {
        return _id;
    }

    /**
     * Set the id of the category. This is typically set by the JPA entity manager and should only
     * be set by the developer when they want to merge new data with an existing entity or want to
     * perform query by example. But even then it is not generally recommended.
     *
     * @param id the id.
     */
    public void setId(Integer id) {
        this._id = id;
    }

    /**
     * The name of the category. This is a required property.
     *
     * @return the name.
     */
    @Column(nullable = false)
    public String getName() {
        return _name;
    }

    /**
     * Set the name of the category. This is a required non-null property.
     *
     * @param name the new name.
     */
    public void setName(String name) {
        this._name = name;
    }

    @Override
    @ElementCollection(fetch = FetchType.LAZY, targetClass = String.class)
    @CollectionTable(joinColumns = @JoinColumn(name = "idDes"), name = "CategoriesDes")
    @MapKeyColumn(name = "langId", length = 5)
    @Column(name = "label", nullable = false)
    public Map<String, String> getLabelTranslations() {
        return super.getLabelTranslations();
    }


    @ManyToMany(mappedBy="metadataCategories", fetch = FetchType.LAZY)
    @Nonnull
    @JsonIgnore
    public Set<Metadata> getRecords() {
        return _records;
    }

    protected void setRecords(@Nonnull Set<Metadata> records) {
        this._records = records;
    }

    @Override
    @JsonIgnore
    protected Set<String> propertiesToExcludeFromXml() {
        return EXCLUDE_FROM_XML;
    }

    @Override
    @Transient
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MetadataCategory category = (MetadataCategory) o;

        if (_id != category._id) return false;

        return true;
    }

    @Override
    @JsonIgnore
    @Transient
    public int hashCode() {
        return _id;
    }
}
