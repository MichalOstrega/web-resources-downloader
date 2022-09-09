package com.most.web.resources.downloader.app.webresource;

import com.most.web.resources.downloader.domain.webresource.WebResource;

import java.sql.SQLException;

public interface WebResourceCommandService {

    WebResource save(WebResourceParams params) throws SQLException;

}
