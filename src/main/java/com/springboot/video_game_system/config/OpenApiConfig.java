package com.springboot.video_game_system.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Video Game System Rest API",
        description = "This REST API manages video games organized into lists within a video game system.",
        version = "1.0",
        contact = @Contact(
                name = "Marcos Soto",
                url = "https://ar.linkedin.com/in/marcos-tulio-soto-de-la-vega-8a6b9668",
                email = "mtsotodelavega@gmail.com"
        ),
        license = @License(name = "GNU General Public License", url = "https://www.gnu.org/licenses/gpl-3.0.html")
))
public class OpenApiConfig {

    /*@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("Bearer Authentication", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }*/

}
