package Baixador_Video.Services.Downloads;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Baixador_Video.Exceptions.DownloadException;
import Baixador_Video.Exceptions.UrlException;
import Baixador_Video.Repositories.UrlRepository;

@Service
public class DownloadService {
    
    @Autowired
    UrlRepository urlRepository;

    private String domains[] = {"youtube.com", "youtu.be", "vimeo.com"};

    @Transactional
    public void downloadVideo(String originalUrl){
        validadeUrl(originalUrl);

        if(originalUrl.isEmpty()){
            throw new UrlException("URL vazia.");
        }

        boolean isValid = Arrays.stream(domains).anyMatch(originalUrl::contains); // Percorrendo o array e verifica se algum domínio está na URL

        if(!isValid){
            throw new UrlException("URL não pertence a um site suportado.");
        }

        prepareDownloadFolder();
        executeDownloadComman(originalUrl);
    }

    private void prepareDownloadFolder(){

        File downloadFolder = new File("downloads");

        if(!downloadFolder.exists()){
            boolean created = downloadFolder.mkdirs(); // Cria a pasta "downloads" se não existir

            if(!created){
                throw new DownloadException("Falha ao criar a pasta de downloads.");
            }
        }
    }

        /*
         mkdir() Cria apenas um diretório, falha se os diretórios pais não existirem.

         mkdirs() Cria toda a estrutura de diretórios necessária.
         */

       

    private void executeDownloadComman(String originalUrl){

        String command = "yt-dlp -o \"downloads/%(title)s.%(ext)s\" " + originalUrl; // Comando para baixar o vídeo

        try{
            ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", command); // Chamo o comando no terminal
            processBuilder.inheritIO(); // Permite visualizar a saída do comando no console
            Process process = processBuilder.start(); // Executa o processo
            int exitCode = process.waitFor(); // Aguarda a conclusão

            if(exitCode != 0){
                throw new DownloadException("Erro ao baixar o vídeo. Código de saída: " + exitCode);
            }
        }catch(IOException | InterruptedException e){
            throw new DownloadException("Falha ao executar o comando de download. "+  e);
        }
    }















    private void validadeUrl(String originalUrl){

         try{
            URI uri = new URI(originalUrl); // Cria um objeto URI a partir da URL
            String host = uri.getHost(); // Extrai o host (domínio)

            if (host == null || Arrays.stream(domains).noneMatch(host::contains)) {
                throw new UrlException("URL não pertence a um site suportado.");
            }

        }catch (URISyntaxException e) {
            throw new UrlException("URL inválida.");
        }
    }
}


