package Baixador_Video.Exceptions;

public class DownloadException extends RuntimeException {
    
    public DownloadException(String messege){
        super(messege);
    }
}
