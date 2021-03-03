# 开启Https

# 证书设置

需要注意的是如果证书是自签名的证书，一定要设置好域名/ip列表，以免请求被拦截。

```shell
$ keytool -genkeypair -alias eureka-local-server -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore server.p12 -validity 3650
```

```shell
$ keytool -genkeypair -alias eureka-local-client -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore client.p12 -validity 3650
```

在上述sotre生成的时候，一定要注意server和client的签名信息要保持一致，同时CN对应的是DNS，如果eureka的域名是localhost，则CN需要设置为localhost。

```shell
keytool -export -alias eureka-local-server -file eureka-local-server.crt --keystore server.p12
```                           

```shell
 keytool -export -alias eureka-local-client -file eureka-local-client.crt --keystore client.p12                                
```

```shell
keytool -import -alias eureka-local-server  -file eureka-local-server.crt -keystore client.p12                                
```

```shell
keytool -import -alias eureka-local-client  -file eureka-local-client.crt -keystore server.p12                                
```

## Eureka replica 信息同步

eureka replica信息同步使用的是eureka内部的配置，和eureka client注册的配置不是同一个，所以配置了TrustAll策略之后只会对客户端注册到服务端
的时候生效，对eureka之间的同步不起作用。
eureka replica同步一定要保证证书和签名的正确和一致。