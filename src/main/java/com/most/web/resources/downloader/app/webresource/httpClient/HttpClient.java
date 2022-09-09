package com.most.web.resources.downloader.app.webresource.httpClient;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface HttpClient {

    DownloadResponse download(String url) throws ExecutionException, InterruptedException, IOException;
}
