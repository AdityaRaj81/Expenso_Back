@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return registry -> registry.addMapping("/**")
                .allowedOrigins("https://expenso-seven.vercel.app")
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
