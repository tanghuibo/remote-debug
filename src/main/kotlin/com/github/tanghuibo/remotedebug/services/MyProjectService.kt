package com.github.tanghuibo.remotedebug.services

import com.intellij.openapi.project.Project
import com.github.tanghuibo.remotedebug.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
