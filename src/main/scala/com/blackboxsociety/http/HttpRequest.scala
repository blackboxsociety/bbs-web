package com.blackboxsociety.http

case class HttpRequest(method:   HttpMethod,
                       resource: HttpResource,
                       version:  HttpVersion,
                       headers:  List[HttpHeader])