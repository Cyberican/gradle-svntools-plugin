package com.companyname.dev.plugins.gradle.pipeline.tasks

import com.companyname.dev.plugins.gradle.pipeline.api.SvnVersionData
import com.companyname.dev.plugins.gradle.pipeline.internal.SvnBaseTask
import com.companyname.dev.plugins.gradle.pipeline.internal.SvnSupport
import org.gradle.api.PathValidation
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction

/** Provides information similar to the <a href="http://svnbook.red-bean.com/en/1.7/svn.ref.svnversion.re.html">"svnversion"</a> command */
class SvnVersion extends SvnBaseTask {

  /** Local SVN working copy directory (default: {@code project.projectDir}) */
  @Internal sourcePath
  /** The name of the project extra property that will receive the resulting {@link SvnVersionData} object (default: {@code svnVersion}) */
  @Internal String targetPropertyName
  /** Continue the build if the specified path is no SVN working copy */
  @Internal boolean ignoreErrors

  @TaskAction
  def run() {
    def srcPath = sourcePath != null ? project.file(sourcePath, PathValidation.EXISTS) : project.projectDir
    def result = SvnSupport.createSvnVersionData(srcPath, getUsername(), getPassword(), proxy, ignoreErrors)
    project.ext.set(targetPropertyName ?: "svnVersion", result)
  }
}
