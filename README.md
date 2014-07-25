单点登录（SSO）
===

本例子包含两部份  
1：sso server（改写cas）  
2：sso client (apache shiro实现)

注意
===

如果SSO验证成功返回error.jsp页面，说明是SSL认证问题，将sso server所在的tomcat开启SSL认证，并用自带JDK制作证书测试。
