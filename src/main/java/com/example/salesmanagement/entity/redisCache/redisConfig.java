

// @Configuration
// public class RedisConfig {
//     @Value("${redis.host}")
//     private String redisHost;

//     @Value("${redis.port}")
//     private int redisPort;


//     @Bean
//     public LettuceConnectionFactory redisConnectionFactory() {
//         // Tạo Standalone Connection tới Redis
//         return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisHost, redisPort));
//     }

//     @Bean
//     @Primary
//     public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//         // tạo ra một RedisTemplate
//         // Với Key là Object
//         // Value là Object
//         // RedisTemplate giúp chúng ta thao tác với Redis
//         RedisTemplate<Object, Object> template = new RedisTemplate<>();
//         template.setConnectionFactory(redisConnectionFactory);
//         return template;
//     }
// }
