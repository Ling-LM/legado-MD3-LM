# Legado with MD3 核心模块文档

## 1. 阅读模块（Read Module）

### 1.1 模块概述

阅读模块是应用的核心功能模块，负责书籍内容的渲染、翻页、阅读设置等功能。该模块支持多种阅读格式，包括 TXT、EPUB 等，并提供丰富的阅读设置选项。

### 1.2 主要组件

| 组件名称 | 职责 | 所在文件 |
|---------|------|----------|
| ReadBookActivity | 阅读界面的主活动 | ui/book/read/ReadBookActivity.kt |
| ReadBookViewModel | 阅读界面的 ViewModel | ui/book/read/ReadBookViewModel.kt |
| ReadMenu | 阅读菜单 | ui/book/read/ReadMenu.kt |
| BookContent | 书籍内容处理 | model/webBook/BookContent.kt |
| BaseReadBookActivity | 阅读活动的基类 | ui/book/read/BaseReadBookActivity.kt |

### 1.3 核心功能

- **页面渲染**：支持多种渲染模式，包括覆盖、仿真、滑动、滚动等
- **阅读设置**：字体、字号、行距、段距、背景等设置
- **翻页控制**：支持触摸、音量键、重力感应等翻页方式
- **内容处理**：章节跳转、内容搜索、书签等
- **阅读统计**：阅读时间、进度等统计

### 1.4 关键流程

1. 用户打开书籍 → ReadBookActivity 初始化 → 加载书籍内容
2. 用户调整阅读设置 → ReadBookViewModel 处理 → 更新界面
3. 用户翻页 → 触发翻页动画 → 加载新内容

## 2. 书架模块（Bookshelf Module）

### 2.1 模块概述

书架模块负责管理用户的书籍，包括添加、删除、分组、排序等功能。该模块提供多种书架布局选择，并支持智能分组功能。

### 2.2 主要组件

| 组件名称 | 职责 | 所在文件 |
|---------|------|----------|
| MainActivity | 主活动，包含书架界面 | ui/main/MainActivity.kt |
| BookRepository | 书籍数据仓库 | data/repository/BookRepository.kt |
| BookAdapter | 书籍适配器 | ui/book/manage/BookAdapter.kt |
| GroupViewModel | 书籍分组 ViewModel | ui/book/group/GroupViewModel.kt |

### 2.3 核心功能

- **书籍管理**：添加、删除、编辑书籍信息
- **书籍分组**：创建、管理书籍分组
- **书架布局**：支持网格、列表等多种布局
- **智能分组**：自动归类已读/未读书籍
- **书籍搜索**：在书架中搜索书籍

### 2.4 关键流程

1. 应用启动 → 加载书架数据 → 显示书籍列表
2. 用户添加书籍 → 保存到数据库 → 更新书架
3. 用户操作书籍 → 执行相应操作 → 更新界面

## 3. 网络书籍模块（WebBook Module）

### 3.1 模块概述

网络书籍模块负责处理网络书籍的搜索、章节获取和内容解析。该模块支持多种书源，并提供书源管理功能。

### 3.2 主要组件

| 组件名称 | 职责 | 所在文件 |
|---------|------|----------|
| WebBook | 网络书籍核心类 | model/webBook/WebBook.kt |
| SearchModel | 搜索模型 | model/webBook/SearchModel.kt |
| BookChapterList | 章节列表处理 | model/webBook/BookChapterList.kt |
| BookInfo | 书籍信息处理 | model/webBook/BookInfo.kt |
| BookContent | 书籍内容处理 | model/webBook/BookContent.kt |

### 3.3 核心功能

- **书源管理**：添加、编辑、删除书源
- **书籍搜索**：通过书源搜索书籍
- **章节获取**：获取书籍章节列表
- **内容解析**：解析书籍章节内容
- **书源验证**：验证书源是否可用

### 3.4 关键流程

1. 用户搜索书籍 → 遍历书源 → 解析搜索结果
2. 用户选择书籍 → 获取书籍信息 → 显示书籍详情
3. 用户打开书籍 → 获取章节列表 → 加载章节内容

## 4. 本地书籍模块（LocalBook Module）

### 4.1 模块概述

本地书籍模块负责处理本地书籍的解析和阅读。该模块支持多种本地文件格式，包括 TXT、EPUB、MOBI 等。

### 4.2 主要组件

| 组件名称 | 职责 | 所在文件 |
|---------|------|----------|
| LocalBook | 本地书籍核心类 | model/localBook/LocalBook.kt |
| TextFile | TXT 文件解析 | model/localBook/TextFile.kt |
| EpubFile | EPUB 文件解析 | model/localBook/EpubFile.kt |
| MobiFile | MOBI 文件解析 | model/localBook/MobiFile.kt |
| BaseLocalBookParse | 本地书籍解析基类 | model/localBook/BaseLocalBookParse.kt |

### 4.3 核心功能

- **文件扫描**：扫描本地文件系统中的书籍
- **格式解析**：解析不同格式的本地书籍
- **章节处理**：自动生成或解析章节
- **编码检测**：自动检测文件编码

### 4.4 关键流程

1. 用户导入本地书籍 → 扫描文件 → 解析书籍信息
2. 用户打开本地书籍 → 解析书籍内容 → 显示阅读界面

## 5. 漫画模块（Manga Module）

### 5.1 模块概述

漫画模块提供漫画阅读功能，支持图片加载、缩放、翻页等操作。该模块针对漫画阅读进行了专门优化。

### 5.2 主要组件

| 组件名称 | 职责 | 所在文件 |
|---------|------|----------|
| ReadMangaActivity | 漫画阅读活动 | ui/book/manga/ReadMangaActivity.kt |
| ReadMangaViewModel | 漫画阅读 ViewModel | ui/book/manga/ReadMangaViewModel.kt |
| ReadManga | 漫画阅读核心类 | model/ReadManga.kt |

### 5.3 核心功能

- **图片加载**：加载漫画图片
- **图片缩放**：支持图片缩放和移动
- **翻页控制**：支持多种翻页方式
- **漫画设置**：亮度、对比度等设置

### 5.4 关键流程

1. 用户打开漫画 → 加载漫画章节 → 显示漫画内容
2. 用户滑动翻页 → 加载下一张图片 → 显示新内容

## 6. 有声书模块（Audio Module）

### 6.1 模块概述

有声书模块提供有声书播放功能，支持文本转语音和音频播放。该模块集成了多种 TTS 引擎。

### 6.2 主要组件

| 组件名称 | 职责 | 所在文件 |
|---------|------|----------|
| AudioPlayActivity | 有声书播放活动 | ui/book/audio/AudioPlayActivity.kt |
| AudioPlayViewModel | 有声书播放 ViewModel | ui/book/audio/AudioPlayViewModel.kt |
| AudioPlayService | 有声书播放服务 | service/AudioPlayService.kt |
| ReadAloud | 有声书核心类 | model/ReadAloud.kt |

### 6.3 核心功能

- **文本转语音**：将书籍内容转换为语音
- **音频播放**：播放有声书内容
- **播放控制**：暂停、继续、调整速度等
- **TTS 引擎管理**：支持多种 TTS 引擎

### 6.4 关键流程

1. 用户开启有声书 → 初始化 TTS 引擎 → 开始播放
2. 用户调整播放设置 → 更新播放参数 → 应用新设置

## 7. RSS 模块

### 7.1 模块概述

RSS 模块提供 RSS 订阅和阅读功能，支持订阅 RSS 源、获取文章和阅读文章。

### 7.2 主要组件

| 组件名称 | 职责 | 所在文件 |
|---------|------|----------|
| Rss | RSS 核心类 | model/rss/Rss.kt |
| RssParserByRule | 规则解析 RSS | model/rss/RssParserByRule.kt |
| RssParserDefault | 默认 RSS 解析 | model/rss/RssParserDefault.kt |
| RssRepository | RSS 数据仓库 | data/repository/RssRepository.kt |

### 7.3 核心功能

- **RSS 源管理**：添加、编辑、删除 RSS 源
- **文章获取**：获取 RSS 源的文章
- **文章阅读**：阅读 RSS 文章
- **文章收藏**：收藏感兴趣的文章

### 7.4 关键流程

1. 用户添加 RSS 源 → 保存到数据库 → 更新 RSS 列表
2. 用户刷新 RSS → 获取最新文章 → 显示更新结果
3. 用户阅读文章 → 打开文章内容 → 显示阅读界面

## 8. 模块间协作

各核心模块之间通过清晰的接口进行协作，共同提供完整的阅读体验：

- **阅读模块** 依赖 **网络书籍模块** 或 **本地书籍模块** 获取书籍内容
- **书架模块** 依赖 **网络书籍模块** 和 **本地书籍模块** 管理书籍
- **漫画模块** 和 **有声书模块** 是 **阅读模块** 的扩展
- **RSS 模块** 独立运行，提供资讯阅读功能

## 9. 总结

Legado with MD3 的核心模块设计清晰，职责明确，通过模块化的架构实现了丰富的阅读功能。各模块之间通过合理的依赖关系协作，共同为用户提供完整的阅读体验。随着项目向 Jetpack Compose 的迁移，模块的实现方式也在不断优化，以提供更加现代化的用户界面和更好的用户体验。