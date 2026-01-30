package br.com.movieflix.mapper;

import br.com.movieflix.Controller.request.StreamingRequest;
import br.com.movieflix.Controller.response.StreamingResponse;
import br.com.movieflix.Entity.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public static Streaming toStreaming(StreamingRequest streamingRequest){
        return Streaming
                .builder()
                .name(streamingRequest.name())
                .build();
    }

    public static StreamingResponse toStreamingResponse(Streaming streaming) {
        return StreamingResponse.builder()
                .name(streaming.getName())
                .id(streaming.getId())
                .build();
    }
}
