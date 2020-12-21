package org.gbif.embl.util;

import org.gbif.dwc.terms.DcTerm;
import org.gbif.dwc.terms.DwcTerm;
import org.gbif.dwc.terms.Term;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public final class EmblAdapterConstants {

  public static final String METADATA_FILENAME = "metadata.xml";
  public static final String CORE_FILENAME = "occurrence.txt";
  public static final String DESCRIPTOR_FILENAME = "meta.xml";

  public static final Pattern LOCATION_PATTERN = Pattern.compile("([0-9.]+\\s+\\w)\\s+([0-9.]+\\s+\\w)");

  public static final String DEFAULT_DELIMITER = "\t";
  public static final String COUNTRY_DELIMITER = ":";
  public static final String TAXON_ID_PREFIX = "ASV:";
  public static final String ASSOCIATED_SEQUENCES_URL = "https://www.ebi.ac.uk/ena/browser/api/embl/";
  public static final String REFERENCES_URL = "https://www.ebi.ac.uk/ena/browser/view/";
  public static final String TAXON_CONCEPT_ID_URL = "https://www.ebi.ac.uk/ena/browser/view/Taxon:";

  public static final String PRESERVED_SPECIMEN = "PreservedSpecimen";
  public static final String MATERIAL_SAMPLE = "MaterialSample";

  public static final List<Term> TERMS = Arrays.asList(DwcTerm.occurrenceID, DwcTerm.associatedSequences,
      DcTerm.references, DwcTerm.decimalLatitude, DwcTerm.decimalLongitude, DwcTerm.country, DwcTerm.locality,
      DwcTerm.identifiedBy, DwcTerm.recordedBy, DwcTerm.eventDate, DwcTerm.catalogNumber, DwcTerm.basisOfRecord,
      DwcTerm.taxonID, DwcTerm.scientificName, DwcTerm.taxonConceptID, DwcTerm.minimumElevationInMeters,
      DwcTerm.maximumElevationInMeters, DwcTerm.sex);

  public static final String DATA_URL = "https://www.ebi.ac.uk/ena/portal/api/search?dataPortal=ena&fields=accession,location,country,identified_by,collected_by,collection_date,specimen_voucher,sequence_md5,scientific_name,tax_id,altitude,sex&offset=0&limit=0&&query=geo_box1(-90,-180,90,180)%20OR%20specimen_voucher=%22*%22%20OR%20identified_by=%22*%22%20OR%20country=%22*%22&result=sequence";

  private EmblAdapterConstants() {
  }
}
