package Baixador_Video.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Baixador_Video.Entities.Download;
import Baixador_Video.Entities.DownloadStatus;
import Baixador_Video.Services.Downloads.DownloadService;

@RestController
@RequestMapping(value = "/downloads")
public class DownloadController {
    
    @Autowired
    DownloadService downloadService;

    @PostMapping
    public void downloadVideo(@RequestParam(defaultValue = "") String originalUrl){

        downloadService.downloadVideo(originalUrl);
    }

    @GetMapping
    public ResponseEntity<Page<Download>> getAllDownloads(Pageable pageable){
        Page<Download> result = downloadService.getAllDownloads(pageable);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/status/{status}")
    public ResponseEntity<Page<Download>> findDOwnloadByStatus(@PathVariable DownloadStatus status, Pageable pageable){
        Page<Download> result = downloadService.findDownloadByStatus(status, pageable);

        return new ResponseEntity<>(result, HttpStatus.FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteDownloadById(@PathVariable Long id){

        downloadService.deleteById(id);
    }
}
