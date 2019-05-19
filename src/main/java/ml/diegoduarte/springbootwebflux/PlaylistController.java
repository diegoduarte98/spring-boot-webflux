package ml.diegoduarte.springbootwebflux;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
public class PlaylistController {

	@Autowired
	private PlaylistService playlistService;

	@GetMapping("/playlist")
	public Flux<Playlist> getPlaylist() {
		return playlistService.findAll();
	}

	@GetMapping("/playlist/{id}")
	public Mono<Playlist> getPlaylist(@PathVariable String id) {
		return playlistService.findById(id);
	}

	@PostMapping("/playlist")
	public Mono<Playlist> save(@RequestBody Playlist playlist) {
		return playlistService.save(playlist);
	}

	@GetMapping(value = "/playlist/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Tuple2<Long, Playlist>> getPlaylistByEvents() {

		Flux<Long> interval = Flux.interval(Duration.ofSeconds(5));
		Flux<Playlist> events = playlistService.findAll();
		System.out.print("Passou aqui events");
		return Flux.zip(interval, events);

	}
}
