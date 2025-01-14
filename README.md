# Spring Boot Sample Application

[![Build Status](https://travis-ci.org/miyabayt/spring-boot-doma2-sample.svg?branch=master)](https://travis-ci.org/miyabayt/spring-boot-doma2-sample)
[![Documentation Status](https://readthedocs.org/projects/spring-boot-doma2-sample/badge/?version=latest)](http://spring-boot-doma2-sample.readthedocs.io/ja/latest/?badge=latest)

## ローカル環境

### 開発環境（IntelliJ）

#### 必要なプラグイン・設定

- Lombok pluginをインストールする。
  - Settings > Build, Excecution, Deployment > Compiler > Annotation Processor > `Enable Annotation Processing`をONにする。
- bootRunを実行している場合でもビルドされるようにする。
  - Intellij > Ctrl+Shift+A > type Registry... > `compiler.automake.allow.when.app.running`をONにする。
- Windowsの場合は、コンソール出力が文字化けするため、`C:¥Program Files¥JetBrains¥IntelliJ Idea xx.x.x¥bin`の中にある`idea64.exe.vmoptions`ファイルに`-Dfile.encoding=UTF-8`を追記する。
- ブラウザにLiveReload機能拡張をインストールする。

### Dockerの起動
MySQLなどのサーバーを立ち上げる。

```bash
$ cd /path/to/spring-boot-doma2-sample
$ ./gradlew composeUp
```

### アプリケーションの起動

#### 管理側
```bash
$ # admin application
$ cd /path/to/spring-boot-doma2-sample
$ ./gradlew :sample-web-admin:bootRun
```

#### フロント側
```bash
$ # front application
$ cd /path/to/spring-boot-doma2-sample
$ ./gradlew :sample-web-front:bootRun
```

#### バッチ
```bash
$ # 担当者情報取り込みバッチを起動する
$ cd /path/to/spring-boot-doma2-sample
$ ./gradlew :sample-batch:bootRun -Pargs="--job=importStaffJob"
```

### 接続先情報
#### テストユーザー test@sample.com / passw0rd

| 接続先| URL|
| :-----| :---------------------------------------|
| 管理側画面| http://localhost:18081/admin|
| 管理側API| http://localhost:18081/admin/api/v1/users|
| フロント側| http://localhost:18080/|

#### データベース接続先

```bash
mysql -h 127.0.0.1 -P 3306 -uroot -ppassw0rd spring-boot-doma2-sample
```

#### ブラウザを用いた自動テスト（結合テスト）
実行方法

| テスト対象| 実行方法|
| :-----| :---------------------------------------|
| 管理側画面| gradlew :it/sample-web-admin:test|
| フロント側| gradlew :it/sample-web-front:test|

※ IntelliJから起動する場合は他のテストと同様にテストクラスを選択して実行してください

CIなどで結合テストを行わない場合は以下のようにして対象から外します
```bash
$ ./gradlew clean test --info -x :it/sample-web-admin:test -x :it/sample-web-front:test
```

### コード自動生成（おまけ）
```bash
$ cd /path/to/spring-boot-doma2-sample
$ ./gradlew codegen -PsubSystem=system -Pfunc=client -PfuncStr=取引先 [-Ptarget=dao|dto|repository|service|controller|html]
```

## 参考

| プロジェクト| 概要|
| :---------------------------------------| :-------------------------------|
| [Lombok Project](https://projectlombok.org/)| 定型的なコードを書かなくてもよくする|
| [Springframework](https://projects.spring.io/spring-framework/)| Spring Framework|
| [Spring Security](https://projects.spring.io/spring-security/)| セキュリティ対策、認証・認可のフレームワーク|
| [Doma2](https://doma.readthedocs.io/ja/stable/)| O/Rマッパー|
| [spring-boot-doma2](https://github.com/domaframework/doma-spring-boot)| Doma2とSpring Bootを連携する|
| [Flyway](https://flywaydb.org/)| DBマイグレーションツール|
| [Thymeleaf](http://www.thymeleaf.org/)| テンプレートエンジン|
| [Thymeleaf Layout Dialect](https://ultraq.github.io/thymeleaf-layout-dialect/)| テンプレートをレイアウト化する|
| [WebJars](https://www.webjars.org/)| jQueryなどのクライアント側ライブラリをJARとして組み込む|
| [ModelMapper](http://modelmapper.org/)| Beanマッピングライブラリ|
| [Ehcache](http://www.ehcache.org/)| キャッシュライブラリ|
| [Spock](http://spockframework.org/)| テストフレームワーク|
| [Mockito](http://site.mockito.org/)| モッキングフレームワーク |
