plugins {
    application
    kotlin("jvm") version "1.3.61"
}
application {
    //mainClassName = "Main"//for java
    mainClassName = "MainKt"//kotlin字节码反编译发现类会加上Kt  public final class MainKt
}
repositories {
    maven("http://maven.aliyun.com/nexus/content/repositories/releases/")
    maven("http://maven.aliyun.com/nexus/content/repositories/snapshots")
    jcenter()
    mavenCentral()
}
dependencies {
    implementation(kotlin("stdlib"))//kotlin标准库
}
//gradle构建的时候会创建project实例 build.gradle配置文件里面的代码通过task插入到project中执行
//project可以隐式调用比如name相当于project.name repositories相当于project.repositories
//name
//project.name
//project.repositories
//plugins {
//    application //这个插件包含很多常见的任务（task）比如distribution打包等
//自定义task 在tasks-other里面可查看
task("测试task"){
    println("执行了测试的task")
}
//任务依赖 把多个任务建立一些关联
task("opendoor") {
    println("opendoor")
    doFirst {
        println("doFirst opendoor")
    }
    doLast{
        println("doLast opendoor")
    }
}
task("putelephant") {
    println("putelephant")
    doLast{
        println("doLast putelephant")
    }
}.dependsOn("opendoor")
//此时执行putelephant会先执行opendoor
//但是执行opendoor也会打印putelephant因为gradle会先扫描所有任务并执行闭包的操作
//如果不想执行扫描生命周期可以使用doFirst doLast函数 此时执行opendoor不会打印doLast putelephant
//任务集 tasks下一级目录
task("task1"){
    group = "任务集"
}
task("task2"){
    group = "任务集"
}
task("project默认属性"){
    doFirst{
        project.properties.forEach {k,v ->
            println("($k,$v)")
        }
        //打印出来后可以查看并修改
        //project.properties["buildDir"] = "ad" as Nothing
    }
}
task("拷贝工作量(gradle增量更新的例子)"){
    //要想编译一次 二次编译更快需要指定输入源输出源,源没变化就不会重新编译
    inputs.dir("src")
    outputs.file("info.txt")//指定后第一次编译143ms 后面编译都只需要1ms
    doFirst{
        val dir = fileTree("src")
        var infoTxt = File("info.txt")
        infoTxt.delete()
        dir.forEach {
            if (it.isFile) infoTxt.appendText(it.name+"\n")
        }
    }
}
//常见插件
//application    支持java环境+发布程序
//java           支持java环境
//war            build任务集下会有war选项 一般用于web项目打成war包
//三方插件         到gradle的官方插件市场查找https://plugins.gradle.org
//三方插件使用有两种方式 (这里查看例子https://plugins.gradle.org/plugin/org.jetbrains.kotlin.jvm)
//第一种 方便但可能支持不是很完善
//plugins {
//    id("org.jetbrains.kotlin.jvm") version "1.3.61"
//}
//第二种 使用一般没有什么问题 Android更常用这种
//buildscript {
//    repositories {
//        maven {
//            setUrl("https://plugins.gradle.org/m2/")
//        }
//    }
//    dependencies {
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.0")
//    }
//}
//apply{
//    plugin("org.jetbrains.kotlin.jvm")
//}
//自定义maven仓库(maven私服)

//依赖坐标 组group,组织名 名name,项目名 版本号version,jar包版本
//依赖冲突 1.gradle自动支持最高板本的依赖项
//2.加上下面设置可以显示不同插件依赖相同另一个插件的版本不同的冲突,自行解决依赖
configurations.all{
    resolutionStrategy{
        failOnVersionConflict()
        //方法一.强制指定冲突的插件的版本号
        //force("依赖三元组")
    }
}
//方法二 利用exclude 排除其中一个依赖

//扩展task任务
task("扩展 拷贝文件",Copy::class){
    from("src/main/java")
    into("test-copy")
}
task("扩展 删除文件",Delete::class){
    delete("test-copy")
}

//多模块开发
//依赖其他模块
//dependencies{
//    implementation(project(":module名"))
//}

//groovy编写gradle 相差不多也支持DSL,注意groovy是单引号
//buildscript下 ext.xxx_version指定后 可以用$xxx_version引用方便统一修改


