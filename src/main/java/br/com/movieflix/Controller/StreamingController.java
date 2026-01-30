package br.com.movieflix.Controller;

import br.com.movieflix.Controller.request.StreamingRequest;
import br.com.movieflix.Controller.response.StreamingResponse;
import br.com.movieflix.Entity.Streaming;
import br.com.movieflix.Service.StreamingService;
import br.com.movieflix.mapper.StreamingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService streamingService;

    @GetMapping()
    private ResponseEntity<List<StreamingResponse>> getAllStreaming() {
        List<Streaming> streamings = streamingService.findall();
        return ResponseEntity.ok(streamings.stream()
                .map(StreamingMapper::toStreamingResponse).
                toList());
    }
    @GetMapping("{id}")
    private ResponseEntity<StreamingResponse> getStreamingId(@PathVariable Long id) {
        return streamingService.findById(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping()
    private ResponseEntity<StreamingResponse> saveStreaming(@RequestBody StreamingRequest streamingRequest) {
        Streaming streaming = StreamingMapper.toStreaming(streamingRequest);
         streaming = streamingService.save(streaming);
        return ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.toStreamingResponse(streaming));
    }
    @DeleteMapping("{id}")
    private ResponseEntity<Void> deleteByStreamingId(@PathVariable Long id){
        streamingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
