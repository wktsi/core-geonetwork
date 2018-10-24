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

import javax.annotation.Nullable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

/**
 * The Class CssStyleSettingsModel.
 */
@Entity(name = "Settings_CssStyle")
@Table(name = "Settings_CssStyle")
@Cacheable(value=false)
@Access(AccessType.PROPERTY)
public class CssStyleSetting extends GeonetEntity {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6799485605075101098L;

    /** The backgroud color. */
    private String variable;

    /** The font color. */
    private String value;

    /**
     * Instantiates a new css style settings model.
     */
    public CssStyleSetting() {
    }

    public CssStyleSetting(String variable, String value) {
        this.variable = variable;
        this.value = value;
    }

    @Id
    @Column(nullable = false)
    @NonNull
    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    @Column
    @Nullable
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
