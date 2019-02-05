package com.companyname.dev.plugins.gradle.pipeline.tasks

import com.companyname.dev.plugins.gradle.pipeline.internal.SvnBaseTask
import org.gradle.api.InvalidUserDataException
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import org.tmatesoft.svn.core.SVNException

import static org.gradle.api.PathValidation.*

/**
 * Applies a patch file onto a local workspace directory (without committing the changes).
 */
class SvnApplyPatch extends SvnBaseTask {

  /** The name of the patch file, relative to the project directory (required) */
  @Internal patchFile
  /** The base directory to apply the patch, must be part of a local SVN workspace (default: `$project.projectDir`) */
  @Internal dir

  @TaskAction
  def run() {
    def sourceFile = project.file(patchFile, FILE)
    def targetDir = dir ? project.file(dir, DIRECTORY) : project.projectDir
    logger.debug("Applying patch {} to {}", sourceFile, targetDir.absolutePath)
    try {
      createSvnClientManager().diffClient.doPatch(sourceFile, targetDir, false, 0)
    } catch (SVNException e) {
      throw new InvalidUserDataException("svn-patch failed for $sourceFile\n" + e.message, e)
    }
  }
}
