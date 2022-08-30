package com.daoren.auth.config;

/**
 * extends ResourceServerConfigurerAdapter
 * 资源服务器配置
 *
 * @author peng_da
 * @date 2022/8/25 13:11
 */
//@Order
//@EnableResourceServer
public class ResoucesServerConfig {
//    private ResourceServerProperties resourceServerProperties;
//    private TokenStore tokenStore;
//    @Autowired
//    public void setResourceServerProperties(ResourceServerProperties resourceServerProperties) {
//        this.resourceServerProperties = resourceServerProperties;
//    }
//
//    @Autowired
//    public void setTokenStore(TokenStore tokenStore) {
//        this.tokenStore = tokenStore;
//    }
//
//    @Bean
//    public ResourceServerTokenServices resourceServerTokenServices(){
//        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
//        remoteTokenServices.setCheckTokenEndpointUrl(resourceServerProperties.getTokenInfoUri());
//        remoteTokenServices.setClientId(resourceServerProperties.getClientId());
//        remoteTokenServices.setClientSecret(resourceServerProperties.getClientSecret());
//        return remoteTokenServices;
//    }
//
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources
//                .resourceId(resourceServerProperties.getResourceId())
////                .tokenServices(resourceServerTokenServices())
//                .tokenStore(tokenStore)
//                .stateless(true);
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/**").access("#oauth2.hasScope('all')")
//                .and()
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
//    }
}
