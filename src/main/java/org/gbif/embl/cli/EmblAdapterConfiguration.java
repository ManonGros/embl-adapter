package org.gbif.embl.cli;

import com.beust.jcommander.Parameter;

import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

public class EmblAdapterConfiguration {

  @NotNull
  @Parameter(names = "--embl-ebi-api")
  public String emblEbiApi = "https://www.ebi.ac.uk/ena/portal/api/";

  @NotNull
  @Parameter(names = "--start-time")
  public String startTime;

  @NotNull
  @Parameter(names = "--frequency-in-days")
  public Integer frequencyInDays = 7;

  @NotNull
  @Parameter(names = "--working-directory")
  public String workingDirectory;

  @NotNull
  @Parameter(names = "--metadata-file")
  public String metadataFile;

  @NotNull
  @Parameter(names = "--raw-embl-data-output-file")
  public String rawEmblDataOutputFile;

  @Override
  public String toString() {
    return new StringJoiner(", ", EmblAdapterConfiguration.class.getSimpleName() + "[", "]")
        .add("emblEbiApi='" + emblEbiApi + "'")
        .add("startTime='" + startTime + "'")
        .add("frequencyInDays=" + frequencyInDays)
        .add("workingDirectory=" + workingDirectory)
        .add("metadataFile=" + metadataFile)
        .add("rawEmblDataOutputFile=" + rawEmblDataOutputFile)
        .toString();
  }
}
