package com.most.web.resources.downloader.app.webresource.httpClient;

import io.netty.handler.codec.http.HttpHeaders;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.HttpResponseBodyPart;
import org.asynchttpclient.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
class AsyncHttpClient implements HttpClient{

    @Override
    public DownloadResponse download(String url) throws ExecutionException, InterruptedException, IOException {
        List<ByteBuffer> byteBuffers = new ArrayList<>();
        DownloadResponse response = new DownloadResponse(url);
        try (org.asynchttpclient.AsyncHttpClient client = Dsl.asyncHttpClient()) {
            response.setData(client.prepareGet(url)
                    .execute(new AsyncCompletionHandler<ByteBuffer>() {

                        @Override
                        public State onHeadersReceived(HttpHeaders headers) throws Exception {
                            response.setContentType(headers.get("Content-Type"));
                            return super.onHeadersReceived(headers);
                        }

                        @Override
                        public State onBodyPartReceived(HttpResponseBodyPart bodyPart) throws Exception {
                            byteBuffers.add(bodyPart.getBodyByteBuffer());
                            return State.CONTINUE;
                        }

                        @Override
                        public ByteBuffer onCompleted(Response response) throws Exception {
                            return merge(byteBuffers);
                        }
                    })
                    .get());
        }
        return response;
    }

    ByteBuffer merge(List<ByteBuffer> byteBuffers) {
        if (byteBuffers == null || byteBuffers.size() == 0) {
            return ByteBuffer.allocate(0);
        } else if (byteBuffers.size() == 1) {
            return byteBuffers.get(0);
        } else {
            ByteBuffer fullContent = ByteBuffer.allocate(
                    byteBuffers.stream()
                            .mapToInt(ByteBuffer::capacity)
                            .sum()
            );
            byteBuffers.forEach(fullContent::put);
            ((Buffer) fullContent).flip();
            return fullContent;
        }
    }
}
