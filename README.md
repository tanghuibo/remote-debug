# remote-debug

## 插件搭建步骤

从 [intellij-platform-plugin-template](https://github.com/JetBrains/intellij-platform-plugin-template) 通过 [Use this template]() 按钮 fork 代码到自己的仓库

## 核心构建配置

### gradle.properties

gradle 配置文件，可以在里面配置开发 java 插件的依赖，如

```properties
# Plugin Dependencies -> https://plugins.jetbrains.com/docs/intellij/plugin-dependencies.html
# Example: platformPlugins = com.intellij.java, com.jetbrains.php:203.4449.22
platformPlugins = java
```

### build.gradle.kts

gradle 核心构建文件，可以在里面添加 pom 依赖，如

```kotlin
dependencies {
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.13.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.0")
}
```