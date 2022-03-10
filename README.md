# allen-demo

## 1. 说明
allen-demo为创建业务工程的样例。其它工程也依照此工程创建。工程结构说明如下：
- allen-demo-client：包含对外提供的feign调用的接口。
- allen-demo-core：包含核心的业务逻辑，包括dao、sevice即定时任务处理。
- allen-demo-server：web工程，包含对外提供的接口，即controller层。

其它工程可按此工程结构创建，工程名称替换demo为实际的工程名称，pom文件、包名做相应的修改即可。

依赖的环境如下：
- 依赖顶级工程：allen-parent
- 依赖的jdk版本：1.8
- 参考启动命令：nohup java -Xms512M -Xmx512M -Xmn128M -XX:MaxMetaspaceSize=128M -XX:MetaspaceSize=128M -XX:+UseConcMarkSweepGC -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 -XX:+ExplicitGCInvokesConcurrentAndUnloadsClasses -XX:+CMSClassUnloadingEnabled -XX:+ParallelRefProcEnabled -XX:+CMSScavengeBeforeRemark -XX:ErrorFile=./logs/hs_err_pid%p.log -Xloggc:./logs/gc.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCApplicationStoppedTime -XX:HeapDumpPath=./logs -XX:+HeapDumpOnOutOfMemoryError -jar xxl-job-admin-2.3.0.jar --spring.profiles.active=prod >/dev/null 2>&1 &