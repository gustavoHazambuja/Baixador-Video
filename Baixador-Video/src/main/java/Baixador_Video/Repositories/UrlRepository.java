package Baixador_Video.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Baixador_Video.Entities.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {
    
}
