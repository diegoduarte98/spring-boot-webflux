package ml.diegoduarte.springbootwebflux;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
public class Playlist {

	public Playlist(String id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	@Id
	@Getter @Setter
	private String id;
	
	@Getter @Setter
	private String nome;
}
