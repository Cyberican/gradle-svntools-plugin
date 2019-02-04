package com.companyname.dev.plugins.gradle.pipeline

import org.gradle.api.Action
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project

import com.companyname.dev.plugins.gradle.pipeline.tasks.*

class PipelineGradlePlugin implements Plugin<Project> {

    private PipelineGradlePluginExtension extension

    @Override
    void apply(Project project) {
        /* Create extensions and tasks */
        extension = project.extensions.create('defaultConfig', PipelineGradlePluginExtension, project)

        def dummyTask = project.tasks.create('dummyTask', DummyTask) {}
    }
}
