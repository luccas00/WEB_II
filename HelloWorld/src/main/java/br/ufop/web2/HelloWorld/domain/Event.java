package br.ufop.web2.HelloWorld.domain;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {
    
    private Integer id;
    private UUID uuid;

    private String name;
    private String description;

    private LocalDateTime date;
    private OffsetDateTime closeDate;

    private String location;
    
    public Event(String name, LocalDateTime date, String location, String description) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.description = description;
    }
    
    public Event exemplo() {

        return Event.builder()
                    .id(1)
                    .uuid(UUID.randomUUID())
                    .name("Evento de Teste")
                    .description("Descrição do evento de teste")
                    .date(LocalDateTime.now())
                    .closeDate(OffsetDateTime.now())
                    .location("Local do evento")
                    .build();
                
    } 

}
