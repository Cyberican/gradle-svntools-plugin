package com.companyname.dev.plugins.gradle.pipeline.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class DummyTask extends DefaultTask {
    @TaskAction
    void dummyTask() {
        println 'I am a task'
    }
}

