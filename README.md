# 五等分の花嫁ったーくん
## 使い方
1. secret.gradleを記述
    1. /secret.gradle を作成する
    2. 下記 `secret.gradle 記載例` として自身のTwitterのBearer token 取得用の値を記述する
2. 取得開始するTweet IDを記述
    1. 下記 `Tweet IDの記述先` の最初のTweet読み出し処理時にTweet IDを記述する
3. 実行する

### secret.gradle 記載例
```
ext {
    secret = [
            twitter: 'Bearer token 取得用の値を記述'
    ]
}
```

### Tweet IDの記述先
https://github.com/urotarou/QuintessentialQuintuplets_snapshot/blob/master/app/src/main/kotlin/lab/uro/kitori/quintessentialquintuplets/presentation/datasource/LoadHeroineTweetDataSource.kt#L28

上記の第2引数に `1231476167992991744` を指定
