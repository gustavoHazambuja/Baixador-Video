package Baixador_Video.Entities;



import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_url")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Download {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalUrl;
    private String fileName;

    @Enumerated(EnumType.STRING)
    private DownloadStatus status;

    private LocalDateTime timesTamp;

    public Download(String url, String fileName, DownloadStatus status){
        this.originalUrl = url;
        this.fileName = fileName;
        this.timesTamp = LocalDateTime.now();
        this.status = status;
    }

}
