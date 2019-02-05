package com.companyname.dev.plugins.gradle.pipeline.tasks

import com.companyname.dev.plugins.gradle.pipeline.internal.SvnBaseTask
import com.companyname.dev.plugins.gradle.pipeline.internal.SvnSupport
import org.gradle.api.PathValidation
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction

/** Provides information about the current SVN workspace */
class SvnInfo extends SvnBaseTask {

  /** Source path for reading the SVN metadata (default: {@code project.projectDir}) */
  @Internal sourcePath
  /** The name of the project extra property that will receive the resulting {@link com.companyname.dev.plugins.gradle.pipeline.api.SvnData} object (default: {@code svnData}) */
  @Internal String targetPropertyName
  /** Continue the build if the specified path doesn't contain SVN data (default: {@code false}) */
  @Internal boolean ignoreErrors

  @TaskAction
  def run() {
    def srcPath = sourcePath != null ? project.file(sourcePath, PathValidation.EXISTS) : project.projectDir
    def result = SvnSupport.createSvnData(srcPath, getUsername(), getPassword(), proxy, ignoreErrors)
    project.ext.set(targetPropertyName ?: "svnData", result)
  }
}
