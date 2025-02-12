package Baixador_Video.Repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import Baixador_Video.Entities.Download;
import Baixador_Video.Entities.DownloadStatus;

public interface DownloadlRepository extends JpaRepository<Download, Long> {
    
    Page<Download> findDownloadByStatus(DownloadStatus status, Pageable pageable);

    
}
