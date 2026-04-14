# Legado with MD3 Code Wiki

## 项目概述

Legado with MD3 是基于开源项目阅读 (Legado) 开发的 Material Design 3 风格重构版本。本项目在对 UI 进行重绘的基础上，加入了多项分支独有功能，并正在逐步从传统 View 迁移至 Jetpack Compose 框架，目标是提供更加现代、流畅且一致的阅读体验。

## 文档目录

### 1. 架构与设计

- [项目架构](architecture.md) - 项目整体架构、模块划分和职责说明
- [核心模块](core-modules.md) - 主要功能模块的详细说明
- [关键类与函数](key-classes.md) - 核心类和函数的详细文档
- [依赖关系](dependencies.md) - 技术栈和依赖库分析

### 2. 开发与构建

- [运行和构建指南](build-run-guide.md) - 开发环境配置、构建命令和运行方法

## 项目特点

- **全新主题**：全新 Material Design 3 设计界面，支持预测性返回手势与共享元素动画
- **阅读界面**：更加个性化的阅读界面与菜单配置
- **阅读记录**：提供详尽的阅读记录，支持时间轴与章节维度统计
- **体验增强**：更健全的漫画阅读、有声书与发现等界面体验
- **书架布局**：更多的书架布局选择，针对平板端进行了专门的界面优化
- **实用功能**：新增书籍备注、智能伴生分组（自动归类已读/未读），支持手柄上下翻页

## 核心功能

1. **多格式支持**：支持本地 TXT、EPUB 阅读，智能扫描本地文件
2. **高度自定义**：切换字体、背景、行距、段距、加粗、简繁转换等
3. **净化替换**：强力去除广告，替换正文内容
4. **翻页模式**：覆盖、仿真、滑动、滚动等多种模式随心切换
5. **完全开源**：无广告，持续迭代优化

## 技术栈

- **开发语言**：Kotlin
- **UI 框架**：传统 View + Jetpack Compose
- **架构模式**：MVVM
- **数据库**：Room
- **网络请求**：OkHttp + Cronet
- **依赖注入**：Koin
- **图片加载**：Glide
- **音视频播放**：ExoPlayer

## 开发流程

1. **环境配置**：安装 Android Studio 和 JDK 21+
2. **项目克隆**：`git clone https://github.com/HapeLee/legado-with-MD3.git`
3. **依赖同步**：在 Android Studio 中打开项目并同步依赖
4. **构建项目**：`./gradlew assembleDebug`
5. **运行应用**：在模拟器或设备上运行应用
6. **测试与调试**：使用 Android Studio 的调试工具进行测试

## 贡献指南

1. **代码风格**：遵循 Kotlin 代码风格
2. **提交规范**：遵循 Conventional Commits 规范
3. **测试**：确保所有测试通过
4. **代码检查**：提交前运行 `./gradlew lint`

## 常见问题

- **构建失败**：检查网络连接、清理项目、invalidate 缓存
- **依赖冲突**：查看依赖树、排除冲突依赖
- **运行时错误**：查看 Logcat 错误信息、检查权限配置
- **Cronet 问题**：确保 cronetlib 目录有正确的 JAR 文件

## 相关资源

- [官方仓库](https://github.com/HapeLee/legado-with-MD3)
- [原始项目](https://github.com/gedoor/legado)
- [Material Design 3 文档](https://m3.material.io/)
- [Jetpack Compose 文档](https://developer.android.com/jetpack/compose)