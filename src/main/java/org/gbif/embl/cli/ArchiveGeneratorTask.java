package org.gbif.embl.cli;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.gbif.embl.util.DwcArchiveBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.gbif.embl.util.EmblAdapterConstants.DATE_NO_SEPARATORS_FORMAT;

public class ArchiveGeneratorTask implements Runnable {

  private static final Logger LOG = LoggerFactory.getLogger(ArchiveGeneratorTask.class);

  private final String taskName;
  private final String requestUrl;
  private final String archiveNameTemplate;
  private final String rawDataFile;
  private final String workingDirectory;
  private final String metadataFilePath;

  public ArchiveGeneratorTask(
      String taskName,
      String requestUrl,
      String archiveNameTemplate,
      String rawDataFile,
      String workingDirectory,
      String metadataFilePath) {
    this.taskName = taskName;
    this.requestUrl = requestUrl;
    this.archiveNameTemplate = archiveNameTemplate;
    this.rawDataFile = rawDataFile;
    this.workingDirectory = workingDirectory;
    this.metadataFilePath = metadataFilePath;
  }

  @Override
  public void run() {
    LOG.info("[{}] Start downloading data", taskName);
    CommandLine cmd = new CommandLine("curl");
    cmd.addArgument("-X");
    cmd.addArgument("GET");
    cmd.addArgument(requestUrl);
    cmd.addArgument("-o");
    cmd.addArgument(rawDataFile);

    DefaultExecutor executor = new DefaultExecutor();
    executor.setExitValue(0);

    try {
      // download data
      executor.execute(cmd);

      // create archive
      LOG.info("[{}] Start creating archive", taskName);
      DwcArchiveBuilder dwcArchiveBuilder = new DwcArchiveBuilder(workingDirectory, metadataFilePath);
      String archiveName = String.format(archiveNameTemplate, LocalDate.now().format(DATE_NO_SEPARATORS_FORMAT));
      dwcArchiveBuilder.buildArchive(
          new File(workingDirectory + "/output", archiveName), rawDataFile);
      LOG.info("[{}] Archive {} was created", taskName, archiveName);

      // delete temp files
      Files.deleteIfExists(Paths.get(rawDataFile));
      LOG.info("[{}] Raw data file {} deleted", taskName, rawDataFile);
    } catch (IOException e) {
      LOG.error("[{}] IOException while producing archive", taskName, e);
    }
  }
}
