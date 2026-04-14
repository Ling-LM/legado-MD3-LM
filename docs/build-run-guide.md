# Legado with MD3 运行和构建指南

## 1. 开发环境配置

### 1.1 必要工具

- **Android Studio**：最新版本（推荐 Android Studio Hedgehog 或更高）
- **JDK**：Java 21 或更高版本
- **Git**：版本控制工具
- **Gradle**：最新版本（项目使用 Gradle Kotlin DSL）

### 1.2 环境设置

1. **安装 Android Studio**
   - 从 [Android Developer 网站](https://developer.android.com/studio) 下载并安装 Android Studio
   - 安装过程中选择包含 Android SDK 的选项

2. **配置 JDK**
   - 确保系统安装了 Java 21 或更高版本
   - 在 Android Studio 中设置 JDK 路径：`File > Project Structure > SDK Location > JDK Location`

3. **克隆项目**
   ```bash
   git clone https://github.com/HapeLee/legado-with-MD3.git
   cd legado-with-MD3
   ```

4. **同步项目**
   - 打开 Android Studio，选择 `Open an existing project`
   - 选择项目目录，等待 Gradle 同步完成
   - 同步过程中会自动下载所需依赖

## 2. 构建项目

### 2.1 构建变体

项目配置了以下构建变体：

| 变体名称 | 描述 | 构建命令 |
|---------|------|----------|
| debug | 调试版本，添加调试后缀 | `./gradlew assembleDebug` |
| release | 发布版本，开启混淆和资源压缩 | `./gradlew assembleRelease` |
| noR8 | 无 R8 混淆的发布版本 | `./gradlew assembleNoR8` |

### 2.2 构建命令

1. **构建调试版本**
   ```bash
   ./gradlew assembleDebug
   ```

2. **构建发布版本**
   ```bash
   ./gradlew assembleRelease
   ```

3. **构建所有变体**
   ```bash
   ./gradlew assemble
   ```

4. **清理构建**
   ```bash
   ./gradlew clean
   ```

5. **检查代码**
   ```bash
   ./gradlew lint
   ```

### 2.3 ABI 拆分

项目配置了 ABI 拆分，支持以下 CPU 架构：

- armeabi-v7a
- arm64-v8a
- 通用 APK

构建后，APK 文件会生成在 `app/build/outputs/apk/` 目录下，按架构和构建变体分类。

## 3. 运行项目

### 3.1 运行方式

1. **使用 Android Studio 运行**
   - 连接 Android 设备或启动模拟器
   - 点击 Android Studio 工具栏中的 "Run" 按钮
   - 选择目标设备，点击 "OK"

2. **使用命令行运行**
   ```bash
   # 安装调试版本
   ./gradlew installDebug
   
   # 安装发布版本
   ./gradlew installRelease
   ```

### 3.2 运行配置

项目的运行配置已在 Android Studio 中预设，包括：

- **App**：主应用运行配置
- **Test**：单元测试运行配置

### 3.3 调试技巧

1. **断点调试**
   - 在代码中设置断点
   - 点击 "Debug" 按钮启动调试
   - 使用调试面板查看变量和执行流程

2. **日志调试**
   - 使用 `Timber` 库输出日志
   - 在 Android Studio 的 Logcat 面板查看日志
   - 可按标签和级别过滤日志

3. **性能分析**
   - 使用 Android Studio 的 Profiler 工具
   - 监控 CPU、内存、网络和电池使用情况

## 4. 项目结构与配置

### 4.1 项目结构

```
legado-with-MD3/
├── app/                 # 主应用模块
│   ├── src/             # 源代码
│   ├── build.gradle.kts # 模块构建配置
│   └── cronetlib/       # Cronet 库文件
├── modules/             # 子模块
│   ├── book/            # 书籍相关功能
│   └── rhino/           # JavaScript 引擎
├── build.gradle.kts     # 项目构建配置
└── settings.gradle.kts  # 项目设置
```

### 4.2 配置文件

| 配置文件 | 用途 | 位置 |
|---------|------|------|
| build.gradle.kts | 主构建配置 | 项目根目录 |
| app/build.gradle.kts | 应用模块构建配置 | app/ 目录 |
| settings.gradle.kts | 项目设置 | 项目根目录 |
| version.properties | 版本配置 | app/ 目录 |
| proguard-rules.pro | 混淆规则 | app/ 目录 |
| cronet-proguard-rules.pro | Cronet 混淆规则 | app/ 目录 |

### 4.3 版本管理

项目版本通过 `app/version.properties` 文件管理：

```properties
VERSION_MAJOR=0
VERSION_MINOR=3
VERSION_PATCH=26
```

构建时，版本号会自动生成：`VERSION_MAJOR.VERSION_MINOR.VERSION_PATCH`

## 5. 常见问题与解决方案

### 5.1 构建失败

**问题**：Gradle 同步失败或构建失败

**解决方案**：
- 确保网络连接正常，Gradle 能下载依赖
- 清理项目：`./gradlew clean`
-  invalidate Android Studio 缓存：`File > Invalidate Caches / Restart`
- 检查 JDK 版本是否正确

### 5.2 依赖冲突

**问题**：依赖库版本冲突

**解决方案**：
- 查看依赖树：`./gradlew dependencies`
- 排除冲突的依赖：在 build.gradle.kts 中使用 `exclude` 语句
- 统一依赖版本：在 `libs.versions.toml` 中统一管理版本

### 5.3 运行时错误

**问题**：应用运行时崩溃或功能异常

**解决方案**：
- 查看 Logcat 中的错误信息
- 检查权限配置：确保应用有必要的权限
- 检查网络连接：确保设备可以访问网络
- 检查存储权限：确保应用可以访问存储

### 5.4 Cronet 相关问题

**问题**：Cronet 库加载失败

**解决方案**：
- 确保 `cronetlib` 目录下有正确的 JAR 文件
- 检查 `download.gradle` 脚本是否正确执行
- 清理并重新构建项目

## 6. 开发流程

### 6.1 代码风格

- 遵循 Kotlin 代码风格
- 使用 Android Studio 的代码格式化功能
- 保持代码缩进和命名规范一致

### 6.2 提交规范

- 提交信息清晰明了
- 遵循 Conventional Commits 规范
- 提交前运行代码检查：`./gradlew lint`

### 6.3 测试

- 编写单元测试和集成测试
- 运行测试：`./gradlew test`
- 确保测试通过后再提交代码

## 7. 发布流程

### 7.1 准备发布

1. **更新版本号**：修改 `app/version.properties` 文件
2. **运行测试**：确保所有测试通过
3. **运行 lint**：确保代码质量
4. **构建发布版本**：`./gradlew assembleRelease`

### 7.2 签名 APK

项目支持使用签名配置签名 APK：

1. **配置签名信息**：在 `local.properties` 文件中添加签名信息
   ```properties
   RELEASE_STORE_FILE=/path/to/keystore.jks
   RELEASE_STORE_PASSWORD=your_keystore_password
   RELEASE_KEY_ALIAS=your_key_alias
   RELEASE_KEY_PASSWORD=your_key_password
   ```

2. **构建签名 APK**：`./gradlew assembleRelease`

### 7.3 发布渠道

项目配置了应用渠道：

- **app**：主渠道版本

## 8. 总结

Legado with MD3 项目使用现代 Android 开发工具和技术栈，构建和运行流程与标准 Android 项目类似。通过本指南，开发者可以快速配置开发环境、构建项目并运行应用。

主要步骤包括：

1. 配置开发环境（Android Studio、JDK）
2. 克隆项目并同步依赖
3. 选择构建变体并执行构建命令
4. 运行应用进行测试和调试
5. 遵循开发流程和规范

通过正确的配置和操作，开发者可以高效地开发和维护 Legado with MD3 项目，为用户提供更好的阅读体验。