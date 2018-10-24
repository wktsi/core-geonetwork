package org.fao.geonet.repository;

import org.fao.geonet.domain.TextFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Data Access object for the {@link TextFile} entities.
 */
public interface TextFileRepository extends GeonetRepository<TextFile, Integer>, CrudRepository<TextFile, Integer>,
		JpaRepository<TextFile, Integer>, JpaSpecificationExecutor<TextFile> {
}
