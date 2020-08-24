package com.jp.eslocapi.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.jp.eslocapi.Configuration;
import com.jp.eslocapi.api.entities.ArquivosEnviados;
import com.jp.eslocapi.api.entities.Atendimento;
import com.jp.eslocapi.api.services.ArquivoEnviadoService;
import com.jp.eslocapi.util.exceptions.DoNotCreateFolder;
import com.jp.eslocapi.util.exceptions.FolderNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileUtil {

	@Value("${servicos.atendimentos.raiz}")
	private String raiz;
	
	@Autowired
	private Configuration folderDate;
	
	public void salvar(MultipartFile arquivo, String diretorio, String nome) {
		String extensaoOriginal = arquivo.getOriginalFilename().split("[.]")[1];

		Path diretorioPath = Paths.get(this.raiz, diretorio);

		Path arquivoPath = diretorioPath.resolve(nome + "." + extensaoOriginal);

		log.info("Arquivo enviado: {}", arquivoPath.getFileName());
		log.info("Diretório informado: {}", diretorioPath);

		try {
			Files.createDirectories(diretorioPath);
			arquivo.transferTo(arquivoPath.toFile());
			
		} catch (IOException e) {
			throw new RuntimeException("Problemas na tentativa de enviar arquivo.", e);
		}
	}

	// Cria uma pasta com o nome informado. A pasta é criada no diretorio raiz do
	// sistema
	public void createFolder(String folderName) throws DoNotCreateFolder {
		Path diretorioPath = Paths.get(this.raiz, folderName);
		try {
			log.info("Criando a pasta para o atendimentos {}", diretorioPath.getParent());
			Files.createDirectories(diretorioPath);

		} catch (IOException e) {

			throw new DoNotCreateFolder();
		}
	}

	public String findFolder(Atendimento atd) {
		
		//obtem a hora atual
		Integer hora = atd.getDataCadastro().getHour();
		//obtem os minutos
		Integer minuto = atd.getDataCadastro().getMinute();
		
		LocalDateTime dataDoAtendimentoTime = atd.getDataAtendimento().atTime(hora, minuto); 
		
		StringBuilder folder = new StringBuilder();
		folder.append(dataDoAtendimentoTime.format(folderDate.keyDateTimeFormater()));
		folder.append(atd.getProdutor().getCpf());
		
		String idFolder = folder.toString();

		File f = new File(this.raiz);

		File[] arquivos = f.listFiles();
		
		for (File arq : arquivos) {
			if (arq.getName().startsWith(idFolder)) {

				log.info("Pasta encontrada: {}", arq.getAbsolutePath());

				return arq.getName();
			}
		}
		throw new FolderNotFoundException();

	}


}
