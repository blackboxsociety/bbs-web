package com.blackboxsociety.http

abstract class HttpResponse {

  val statusCode: Int
  val body:       String
  val headers:    List[HttpHeader]
  val session:    Option[SignedSession]
  val flash:      Option[FlashSession]

  def make(c: String, l: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]): HttpResponse

  def withHeader(header: HttpHeader): HttpResponse = make(body, headers :+ header, session, flash)

  def withHeaders(newHeaders: List[HttpHeader]): HttpResponse = make(body, headers ++ newHeaders, session, flash)

  def withSession(s: SignedSession): HttpResponse = make(body, headers, Some(s), flash)

  def withFlash(f: FlashSession): HttpResponse = make(body, headers, session, Some(f))

}

case class Accepted(body: String,
                    headers: List[HttpHeader] = List(),
                    session: Option[SignedSession] = None,
                    flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 202
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]): HttpResponse = {
    Accepted(b, h, s, f)
  }
}

case class BadGateway(body: String,
                      headers: List[HttpHeader] = List(),
                      session: Option[SignedSession] = None,
                      flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 502
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    BadGateway(b, h, s, f)
}

case class Conflict(body: String,
                    headers: List[HttpHeader] = List(),
                    session: Option[SignedSession] = None,
                    flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 409
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    Conflict(b, h, s, f)
}

case class Created(body: String,
                   headers: List[HttpHeader] = List(),
                   session: Option[SignedSession] = None,
                   flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 201
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    Created(b, h, s, f)
}

case class EntityTooLarge(body: String,
                          headers: List[HttpHeader] = List(),
                          session: Option[SignedSession] = None,
                          flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 413
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    EntityTooLarge(b, h, s, f)
}

case class ExpectationFailed(body: String,
                             headers: List[HttpHeader] = List(),
                             session: Option[SignedSession] = None,
                             flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 417
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    ExpectationFailed(b, h, s, f)
}

case class FailedDependency(body: String,
                            headers: List[HttpHeader] = List(),
                            session: Option[SignedSession] = None,
                            flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 424
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    FailedDependency(b, h, s, f)
}

case class Forbidden(body: String,
                     headers: List[HttpHeader] = List(),
                     session: Option[SignedSession] = None,
                     flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 403
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    Forbidden(b, h, s, f)
}

case class Found(body: String,
                 headers: List[HttpHeader] = List(),
                 session: Option[SignedSession] = None,
                 flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 302
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    Found(b, h, s, f)
}

case class GatewayTimeout(body: String,
                          headers: List[HttpHeader] = List(),
                          session: Option[SignedSession] = None,
                          flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 504
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    GatewayTimeout(b, h, s, f)
}

case class Gone(body: String,
                headers: List[HttpHeader] = List(),
                session: Option[SignedSession] = None,
                flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 410
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    Gone(b, h, s, f)
}

case class HttpVersionNotSupported(body: String,
                                   headers: List[HttpHeader] = List(),
                                   session: Option[SignedSession] = None,
                                   flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 505
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    HttpVersionNotSupported(b, h, s, f)
}

case class InsuffecientStorage(body: String,
                               headers: List[HttpHeader] = List(),
                               session: Option[SignedSession] = None,
                               flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 507
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    InsuffecientStorage(b, h, s, f)
}

case class InternalServerError(body: String,
                               headers: List[HttpHeader] = List(),
                               session: Option[SignedSession] = None,
                               flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 500
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    InternalServerError(b, h, s, f)
}

case class Locked(body: String,
                  headers: List[HttpHeader] = List(),
                  session: Option[SignedSession] = None,
                  flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 423
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    Locked(b, h, s, f)
}

case class MethodNotAllowed(body: String,
                            headers: List[HttpHeader] = List(),
                            session: Option[SignedSession] = None,
                            flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 405
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    MethodNotAllowed(b, h, s, f)
}

case class Missing(body: String,
                   headers: List[HttpHeader] = List(),
                   session: Option[SignedSession] = None,
                   flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 404
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    Missing(b, h, s, f)
}

case class MovedPermanently(body: String,
                            headers: List[HttpHeader] = List(),
                            session: Option[SignedSession] = None,
                            flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 301
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    MovedPermanently(b, h, s, f)
}

case class MultiStatus(body: String,
                       headers: List[HttpHeader] = List(),
                       session: Option[SignedSession] = None,
                       flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 207
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    MultiStatus(b, h, s, f)
}

case class NoContent(body: String,
                     headers: List[HttpHeader] = List(),
                     session: Option[SignedSession] = None,
                     flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 204
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    NoContent(b, h, s, f)
}

case class NonAcceptable(body: String,
                         headers: List[HttpHeader] = List(),
                         session: Option[SignedSession] = None,
                         flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 406
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    NonAcceptable(b, h, s, f)
}

case class NotImplemented(body: String,
                          headers: List[HttpHeader] = List(),
                          session: Option[SignedSession] = None,
                          flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 501
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    NotImplemented(b, h, s, f)
}

case class NotModified(body: String,
                       headers: List[HttpHeader] = List(),
                       session: Option[SignedSession] = None,
                       flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 304
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    NotModified(b, h, s, f)
}

case class Ok(body: String,
              headers: List[HttpHeader] = List(),
              session: Option[SignedSession] = None,
              flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 200
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    Ok(b, h, s, f)
}

case class PartialContent(body: String,
                          headers: List[HttpHeader] = List(),
                          session: Option[SignedSession] = None,
                          flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 206
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    PartialContent(b, h, s, f)
}

case class PreconditionFailed(body: String,
                              headers: List[HttpHeader] = List(),
                              session: Option[SignedSession] = None,
                              flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 412
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    PreconditionFailed(b, h, s, f)
}

case class RequestTimeout(body: String,
                          headers: List[HttpHeader] = List(),
                          session: Option[SignedSession] = None,
                          flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 408
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    RequestTimeout(b, h, s, f)
}

case class SeeOther(body: String,
                    headers: List[HttpHeader] = List(),
                    session: Option[SignedSession] = None,
                    flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 303
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    SeeOther(b, h, s, f)
}

case class ServiceUnavailable(body: String,
                              headers: List[HttpHeader] = List(),
                              session: Option[SignedSession] = None,
                              flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 503
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    ServiceUnavailable(b, h, s, f)
}

case class TemporaryRedirect(body: String,
                             headers: List[HttpHeader] = List(),
                             session: Option[SignedSession] = None,
                             flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 307
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    TemporaryRedirect(b, h, s, f)
}

case class TooManyRequests(body: String,
                           headers: List[HttpHeader] = List(),
                           session: Option[SignedSession] = None,
                           flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 429
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    TooManyRequests(b, h, s, f)
}

case class Unauthorized(body: String,
                        headers: List[HttpHeader] = List(),
                        session: Option[SignedSession] = None,
                        flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 401
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    Unauthorized(b, h, s, f)
}

case class UnsupportedMediaType(body: String,
                                headers: List[HttpHeader] = List(),
                                session: Option[SignedSession] = None,
                                flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 415
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    UnsupportedMediaType(b, h, s, f)
}

case class UriTooLong(body: String,
                      headers: List[HttpHeader] = List(),
                      session: Option[SignedSession] = None,
                      flash: Option[FlashSession] = None) extends HttpResponse
{
  val statusCode: Int = 414
  def make(b: String, h: List[HttpHeader], s: Option[SignedSession], f: Option[FlashSession]) =
    UriTooLong(b, h, s, f)
}
