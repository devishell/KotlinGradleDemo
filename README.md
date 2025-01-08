# KotlinGradleDemo
use gradle by kotlin syntax.(使用<b>build.gradle.kts</b> by Kotlin DSL)

# 参考链接
[将 build 配置从 Groovy 迁移到 Kotlin](https://developer.android.google.cn/build/migrate-to-kotlin-dsl?hl=zh-cn)
Android Gradle 插件 4.0 支持在 Gradle build 配置中使用 Kotlin，用于替代 Groovy（过去在 Gradle 配置文件中使用的编程语言）。

Kotlin 比 Groovy 更适合用于编写 Gradle 脚本，因为采用 Kotlin 编写的代码可读性更高，并且 Kotlin 提供了更好的编译时检查和 IDE 支持。

虽然与 Groovy 相比，Kotlin 当前能更好地在 Android Studio 的代码编辑器中集成，但采用 Kotlin 的构建速度往往比采用 Groovy 慢，因此在决定是否迁移时应考虑构建性能。

如需查看更全面的迁移指南，请参阅 Gradle 的[官方文档](https://guides.gradle.org/migrating-build-logic-from-groovy-to-kotlin/)。
