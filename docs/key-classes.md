# Legado with MD3 关键类与函数文档

## 1. 阅读模块关键类

### 1.1 ReadBookActivity

**职责**：阅读界面的主活动，负责书籍内容的显示和用户交互

**主要方法**：

| 方法名 | 参数 | 返回值 | 描述 |
|-------|------|-------|------|
| `initData` | `book: Book` | `Unit` | 初始化书籍数据 |
| `initView` | 无 | `Unit` | 初始化界面组件 |
| `loadContent` | `chapterIndex: Int, pageIndex: Int` | `Unit` | 加载指定章节和页面的内容 |
| `updatePage` | `chapterIndex: Int, pageIndex: Int` | `Unit` | 更新当前页面 |
| `showMenu` | 无 | `Unit` | 显示阅读菜单 |

**使用场景**：当用户打开书籍进行阅读时，系统会创建 ReadBookActivity 实例并显示阅读界面。

### 1.2 ReadBookViewModel

**职责**：阅读界面的 ViewModel，处理阅读相关的业务逻辑

**主要方法**：

| 方法名 | 参数 | 返回值 | 描述 |
|-------|------|-------|------|
| `loadContent` | `chapterIndex: Int` | `Flow<BookContent>` | 加载章节内容 |
| `saveReadProgress` | `chapterIndex: Int, pageIndex: Int` | `Unit` | 保存阅读进度 |
| `updateReadConfig` | `config: ReadBookConfig` | `Unit` | 更新阅读配置 |
| `getChapterList` | 无 | `List<BookChapter>` | 获取章节列表 |

**使用场景**：在阅读过程中，ViewModel 负责处理数据加载、进度保存等业务逻辑，与 UI 层分离。

### 1.3 BookContent

**职责**：处理书籍内容的加载和解析

**主要方法**：

| 方法名 | 参数 | 返回值 | 描述 |
|-------|------|-------|------|
| `loadContent` | `chapter: BookChapter` | `String` | 加载章节内容 |
| `parseContent` | `content: String` | `List<String>` | 解析内容为页面 |
| `getPageCount` | 无 | `Int` | 获取总页数 |
| `getPageContent` | `pageIndex: Int` | `String` | 获取指定页面的内容 |

**使用场景**：当需要加载和显示书籍内容时，BookContent 负责处理内容的获取和解析。

## 2. 书架模块关键类

### 2.1 BookRepository

**职责**：书籍数据仓库，处理书籍数据的存储和获取

**主要方法**：

| 方法名 | 参数 | 返回值 | 描述 |
|-------|------|-------|------|
| `getAllBooks` | 无 | `Flow<List<Book>>` | 获取所有书籍 |
| `getBooksByGroup` | `groupId: Long` | `Flow<List<Book>>` | 获取指定分组的书籍 |
| `insertBook` | `book: Book` | `Long` | 插入书籍 |
| `updateBook` | `book: Book` | `Unit` | 更新书籍信息 |
| `deleteBook` | `book: Book` | `Unit` | 删除书籍 |

**使用场景**：当需要操作书籍数据时，通过 BookRepository 进行数据库操作。

### 2.2 MainActivity

**职责**：应用的主活动，包含书架界面

**主要方法**：

| 方法名 | 参数 | 返回值 | 描述 |
|-------|------|-------|------|
| `initView` | 无 | `Unit` | 初始化界面 |
| `loadBooks` | 无 | `Unit` | 加载书籍数据 |
| `showBookshelf` | 无 | `Unit` | 显示书架 |
| `handleBookClick` | `book: Book` | `Unit` | 处理书籍点击事件 |

**使用场景**：应用启动时显示主界面，用户可以在书架上管理和阅读书籍。

## 3. 网络书籍模块关键类

### 3.1 WebBook

**职责**：网络书籍的核心类，处理网络书籍的各种操作

**主要方法**：

| 方法名 | 参数 | 返回值 | 描述 |
|-------|------|-------|------|
| `searchBook` | `keyword: String, page: Int` | `Flow<SearchBook>` | 搜索书籍 |
| `getBookInfo` | `book: Book` | `Flow<Book>` | 获取书籍信息 |
| `getChapterList` | `book: Book` | `Flow<List<BookChapter>>` | 获取章节列表 |
| `getChapterContent` | `chapter: BookChapter` | `Flow<String>` | 获取章节内容 |

**使用场景**：当需要从网络获取书籍信息、章节列表或内容时，使用 WebBook 类。

### 3.2 SearchModel

**职责**：处理书籍搜索逻辑

**主要方法**：

| 方法名 | 参数 | 返回值 | 描述 |
|-------|------|-------|------|
| `search` | `keyword: String` | `Flow<SearchBook>` | 执行搜索 |
| `searchBySource` | `keyword: String, source: BookSource` | `Flow<SearchBook>` | 通过指定书源搜索 |
| `cancelSearch` | 无 | `Unit` | 取消搜索 |

**使用场景**：当用户在搜索界面输入关键词搜索书籍时，SearchModel 负责协调搜索过程。

## 4. 本地书籍模块关键类

### 4.1 LocalBook

**职责**：本地书籍的核心类，处理本地书籍的解析和阅读

**主要方法**：

| 方法名 | 参数 | 返回值 | 描述 |
|-------|------|-------|------|
| `parseBook` | `file: File` | `Book` | 解析本地书籍文件 |
| `getChapterList` | `book: Book` | `List<BookChapter>` | 获取章节列表 |
| `getChapterContent` | `book: Book, chapter: BookChapter` | `String` | 获取章节内容 |
| `saveBook` | `book: Book` | `Long` | 保存书籍信息 |

**使用场景**：当用户导入本地书籍或打开本地书籍时，LocalBook 负责处理书籍的解析和内容获取。

### 4.2 TextFile

**职责**：处理 TXT 文件的解析

**主要方法**：

| 方法名 | 参数 | 返回值 | 描述 |
|-------|------|-------|------|
| `readText` | `file: File` | `String` | 读取文件内容 |
| `detectEncoding` | `file: File` | `String` | 检测文件编码 |
| `splitChapters` | `content: String` | `List<BookChapter>` | 分割章节 |

**使用场景**：当需要解析 TXT 格式的本地书籍时，TextFile 负责处理文件的读取和章节分割。

## 5. 漫画模块关键类

### 5.1 ReadMangaActivity

**职责**：漫画阅读活动，负责漫画内容的显示和用户交互

**主要方法**：

| 方法名 | 参数 | 返回值 | 描述 |
|-------|------|-------|------|
| `initData` | `book: Book` | `Unit` | 初始化漫画数据 |
| `loadImage` | `chapterIndex: Int, pageIndex: Int` | `Unit` | 加载漫画图片 |
| `handleScroll` | `dx: Float, dy: Float` | `Unit` | 处理滑动事件 |
| `zoomImage` | `scale: Float, focusX: Float, focusY: Float` | `Unit` | 缩放图片 |

**使用场景**：当用户打开漫画进行阅读时，ReadMangaActivity 负责显示漫画内容并处理用户交互。

### 5.2 ReadManga

**职责**：漫画阅读的核心类，处理漫画内容的加载和解析

**主要方法**：

| 方法名 | 参数 | 返回值 | 描述 |
|-------|------|-------|------|
| `getImageUrl` | `chapter: BookChapter, pageIndex: Int` | `String` | 获取图片 URL |
| `loadImage` | `url: String` | `Bitmap` | 加载图片 |
| `getPageCount` | `chapter: BookChapter` | `Int` | 获取章节页数 |

**使用场景**：当需要加载和显示漫画内容时，ReadManga 负责处理图片的获取和加载。

## 6. 有声书模块关键类

### 6.1 ReadAloud

**职责**：有声书的核心类，处理文本转语音和音频播放

**主要方法**：

| 方法名 | 参数 | 返回值 | 描述 |
|-------|------|-------|------|
| `startReadAloud` | `book: Book, chapterIndex: Int, pageIndex: Int` | `Unit` | 开始朗读 |
| `pause` | 无 | `Unit` | 暂停朗读 |
| `resume` | 无 | `Unit` | 恢复朗读 |
| `stop` | 无 | `Unit` | 停止朗读 |
| `setSpeed` | `speed: Float` | `Unit` | 设置朗读速度 |

**使用场景**：当用户开启有声书功能时，ReadAloud 负责将书籍内容转换为语音并播放。

### 6.2 AudioPlayService

**职责**：有声书播放服务，在后台处理音频播放

**主要方法**：

| 方法名 | 参数 | 返回值 | 描述 |
|-------|------|-------|------|
| `onStartCommand` | `intent: Intent, flags: Int, startId: Int` | `Int` | 服务启动时的回调 |
| `play` | 无 | `Unit` | 开始播放 |
| `pause` | 无 | `Unit` | 暂停播放 |
| `stop` | 无 | `Unit` | 停止播放 |
| `updateNotification` | `state: Int` | `Unit` | 更新通知状态 |

**使用场景**：当有声书在后台播放时，AudioPlayService 负责管理播放状态和通知。

## 7. RSS 模块关键类

### 7.1 Rss

**职责**：RSS 的核心类，处理 RSS 源的管理和文章获取

**主要方法**：

| 方法名 | 参数 | 返回值 | 描述 |
|-------|------|-------|------|
| `getArticles` | `source: RssSource` | `Flow<List<RssArticle>>` | 获取 RSS 文章 |
| `getArticleContent` | `article: RssArticle` | `Flow<String>` | 获取文章内容 |
| `subscribeSource` | `source: RssSource` | `Long` | 订阅 RSS 源 |
| `unsubscribeSource` | `source: RssSource` | `Unit` | 取消订阅 RSS 源 |

**使用场景**：当用户需要获取 RSS 文章或管理 RSS 源时，Rss 类负责处理相关操作。

### 7.2 RssParserByRule

**职责**：根据规则解析 RSS 源

**主要方法**：

| 方法名 | 参数 | 返回值 | 描述 |
|-------|------|-------|------|
| `parseArticles` | `content: String, source: RssSource` | `List<RssArticle>` | 解析文章列表 |
| `parseArticleContent` | `content: String, source: RssSource` | `String` | 解析文章内容 |

**使用场景**：当需要根据自定义规则解析 RSS 源时，RssParserByRule 负责处理解析逻辑。

## 8. 其他关键类

### 8.1 App

**职责**：应用的入口类，负责初始化应用组件

**主要方法**：

| 方法名 | 参数 | 返回值 | 描述 |
|-------|------|-------|------|
| `onCreate` | 无 | `Unit` | 应用创建时的回调 |
| `initKoin` | 无 | `Unit` | 初始化依赖注入 |
| `initDatabase` | 无 | `Unit` | 初始化数据库 |
| `initDefaultData` | 无 | `Unit` | 初始化默认数据 |

**使用场景**：应用启动时，App 类负责初始化各种组件和配置。

### 8.2 HttpHelper

**职责**：处理网络请求

**主要方法**：

| 方法名 | 参数 | 返回值 | 描述 |
|-------|------|-------|------|
| `get` | `url: String, headers: Map<String, String>?` | `StrResponse` | 发送 GET 请求 |
| `post` | `url: String, body: String, headers: Map<String, String>?` | `StrResponse` | 发送 POST 请求 |
| `download` | `url: String, file: File` | `Boolean` | 下载文件 |
| `getRedirectUrl` | `url: String` | `String` | 获取重定向 URL |

**使用场景**：当需要发送网络请求获取数据时，HttpHelper 负责处理网络通信。

### 8.3 BookCover

**职责**：处理书籍封面的加载和生成

**主要方法**：

| 方法名 | 参数 | 返回值 | 描述 |
|-------|------|-------|------|
| `loadCover` | `book: Book` | `Bitmap` | 加载书籍封面 |
| `getCoverFromUrl` | `url: String` | `Bitmap` | 从 URL 获取封面 |
| `generateCover` | `title: String, author: String` | `Bitmap` | 生成默认封面 |

**使用场景**：当需要显示书籍封面时，BookCover 负责处理封面的加载和生成。

## 9. 总结

Legado with MD3 项目中的关键类和函数设计合理，职责明确。这些类和函数构成了应用的核心功能，通过模块化的设计和清晰的接口，实现了丰富的阅读功能。了解这些关键类和函数对于理解项目的整体架构和功能实现非常重要，也为后续的开发和维护提供了参考。