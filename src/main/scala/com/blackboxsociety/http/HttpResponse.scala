package com.blackboxsociety.http

sealed trait HttpResponse {

  val statusCode:     Int
  val body:           String
  val headers:        List[HttpHeader]
  val session:        Option[SignedSession]
  val flash:          Option[FlashSession]
  val fromController: Boolean

  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse

  def withHeader(header: HttpHeader): HttpResponse = copy(headers = headers :+ header)

  def withHeaders(newHeaders: List[HttpHeader]): HttpResponse = copy(headers = headers ++ newHeaders)

  def withSession(s: SignedSession): HttpResponse = copy(session = Some(s))

  def withFlash(f: FlashSession): HttpResponse = copy(flash = Some(f))

  def markFromController(): HttpResponse = copy(fromController = true)

}

case class Accepted(body: String,
                    headers: List[HttpHeader] = List(),
                    session: Option[SignedSession] = None,
                    flash: Option[FlashSession] = None,
                    fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 202
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    Accepted(body, headers, session, flash, fromController)
}

case class BadGateway(body: String,
                      headers: List[HttpHeader] = List(),
                      session: Option[SignedSession] = None,
                      flash: Option[FlashSession] = None,
                      fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 502
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    BadGateway(body, headers, session, flash, fromController)
}

case class Conflict(body: String,
                    headers: List[HttpHeader] = List(),
                    session: Option[SignedSession] = None,
                    flash: Option[FlashSession] = None,
                    fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 409
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    Conflict(body, headers, session, flash, fromController)
}

case class Created(body: String,
                   headers: List[HttpHeader] = List(),
                   session: Option[SignedSession] = None,
                   flash: Option[FlashSession] = None,
                   fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 201
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    Created(body, headers, session, flash, fromController)
}

case class EntityTooLarge(body: String,
                          headers: List[HttpHeader] = List(),
                          session: Option[SignedSession] = None,
                          flash: Option[FlashSession] = None,
                          fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 413
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    EntityTooLarge(body, headers, session, flash, fromController)
}

case class ExpectationFailed(body: String,
                             headers: List[HttpHeader] = List(),
                             session: Option[SignedSession] = None,
                             flash: Option[FlashSession] = None,
                             fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 417
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    ExpectationFailed(body, headers, session, flash, fromController)
}

case class FailedDependency(body: String,
                            headers: List[HttpHeader] = List(),
                            session: Option[SignedSession] = None,
                            flash: Option[FlashSession] = None,
                            fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 424
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    FailedDependency(body, headers, session, flash, fromController)
}

case class Forbidden(body: String,
                     headers: List[HttpHeader] = List(),
                     session: Option[SignedSession] = None,
                     flash: Option[FlashSession] = None,
                     fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 403
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    Forbidden(body, headers, session, flash, fromController)
}

case class Found(body: String,
                 headers: List[HttpHeader] = List(),
                 session: Option[SignedSession] = None,
                 flash: Option[FlashSession] = None,
                 fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 302
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    Found(body, headers, session, flash, fromController)
}

case class GatewayTimeout(body: String,
                          headers: List[HttpHeader] = List(),
                          session: Option[SignedSession] = None,
                          flash: Option[FlashSession] = None,
                          fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 504
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    GatewayTimeout(body, headers, session, flash, fromController)
}

case class Gone(body: String,
                headers: List[HttpHeader] = List(),
                session: Option[SignedSession] = None,
                flash: Option[FlashSession] = None,
                fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 410
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    Gone(body, headers, session, flash, fromController)
}

case class HttpVersionNotSupported(body: String,
                                   headers: List[HttpHeader] = List(),
                                   session: Option[SignedSession] = None,
                                   flash: Option[FlashSession] = None,
                                   fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 505
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    HttpVersionNotSupported(body, headers, session, flash, fromController)
}

case class InsuffecientStorage(body: String,
                               headers: List[HttpHeader] = List(),
                               session: Option[SignedSession] = None,
                               flash: Option[FlashSession] = None,
                               fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 507
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    InsuffecientStorage(body, headers, session, flash, fromController)
}

case class InternalServerError(body: String,
                               headers: List[HttpHeader] = List(),
                               session: Option[SignedSession] = None,
                               flash: Option[FlashSession] = None,
                               fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 500
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    InternalServerError(body, headers, session, flash, fromController)
}

case class Locked(body: String,
                  headers: List[HttpHeader] = List(),
                  session: Option[SignedSession] = None,
                  flash: Option[FlashSession] = None,
                  fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 423
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    Locked(body, headers, session, flash, fromController)
}

case class MethodNotAllowed(body: String,
                            headers: List[HttpHeader] = List(),
                            session: Option[SignedSession] = None,
                            flash: Option[FlashSession] = None,
                            fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 405
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    MethodNotAllowed(body, headers, session, flash, fromController)
}

case class Missing(body: String,
                   headers: List[HttpHeader] = List(),
                   session: Option[SignedSession] = None,
                   flash: Option[FlashSession] = None,
                   fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 404
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    Missing(body, headers, session, flash, fromController)
}

case class MovedPermanently(body: String,
                            headers: List[HttpHeader] = List(),
                            session: Option[SignedSession] = None,
                            flash: Option[FlashSession] = None,
                            fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 301
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    MovedPermanently(body, headers, session, flash, fromController)
}

case class MultiStatus(body: String,
                       headers: List[HttpHeader] = List(),
                       session: Option[SignedSession] = None,
                       flash: Option[FlashSession] = None,
                       fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 207
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    MultiStatus(body, headers, session, flash, fromController)
}

case class NoContent(body: String,
                     headers: List[HttpHeader] = List(),
                     session: Option[SignedSession] = None,
                     flash: Option[FlashSession] = None,
                     fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 204
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    NoContent(body, headers, session, flash, fromController)
}

case class NonAcceptable(body: String,
                         headers: List[HttpHeader] = List(),
                         session: Option[SignedSession] = None,
                         flash: Option[FlashSession] = None,
                         fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 406
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    NonAcceptable(body, headers, session, flash, fromController)
}

case class NotImplemented(body: String,
                          headers: List[HttpHeader] = List(),
                          session: Option[SignedSession] = None,
                          flash: Option[FlashSession] = None,
                          fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 501
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    NotImplemented(body, headers, session, flash, fromController)
}

case class NotModified(body: String,
                       headers: List[HttpHeader] = List(),
                       session: Option[SignedSession] = None,
                       flash: Option[FlashSession] = None,
                       fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 304
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    NotModified(body, headers, session, flash, fromController)
}

case class Ok(body: String,
              headers: List[HttpHeader] = List(),
              session: Option[SignedSession] = None,
              flash: Option[FlashSession] = None,
              fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 200
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    Ok(body, headers, session, flash, fromController)
}

case class PartialContent(body: String,
                          headers: List[HttpHeader] = List(),
                          session: Option[SignedSession] = None,
                          flash: Option[FlashSession] = None,
                          fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 206
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    PartialContent(body, headers, session, flash, fromController)
}

case class PreconditionFailed(body: String,
                              headers: List[HttpHeader] = List(),
                              session: Option[SignedSession] = None,
                              flash: Option[FlashSession] = None,
                              fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 412
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    PreconditionFailed(body, headers, session, flash, fromController)
}

case class RequestTimeout(body: String,
                          headers: List[HttpHeader] = List(),
                          session: Option[SignedSession] = None,
                          flash: Option[FlashSession] = None,
                          fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 408
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    RequestTimeout(body, headers, session, flash, fromController)
}

case class SeeOther(body: String,
                    headers: List[HttpHeader] = List(),
                    session: Option[SignedSession] = None,
                    flash: Option[FlashSession] = None,
                    fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 303
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    SeeOther(body, headers, session, flash, fromController)
}

case class ServiceUnavailable(body: String,
                              headers: List[HttpHeader] = List(),
                              session: Option[SignedSession] = None,
                              flash: Option[FlashSession] = None,
                              fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 503
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    ServiceUnavailable(body, headers, session, flash, fromController)
}

case class TemporaryRedirect(body: String,
                             headers: List[HttpHeader] = List(),
                             session: Option[SignedSession] = None,
                             flash: Option[FlashSession] = None,
                             fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 307
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    TemporaryRedirect(body, headers, session, flash, fromController)
}

case class TooManyRequests(body: String,
                           headers: List[HttpHeader] = List(),
                           session: Option[SignedSession] = None,
                           flash: Option[FlashSession] = None,
                           fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 429
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    TooManyRequests(body, headers, session, flash, fromController)
}

case class Unauthorized(body: String,
                        headers: List[HttpHeader] = List(),
                        session: Option[SignedSession] = None,
                        flash: Option[FlashSession] = None,
                        fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 401
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    Unauthorized(body, headers, session, flash, fromController)
}

case class UnsupportedMediaType(body: String,
                                headers: List[HttpHeader] = List(),
                                session: Option[SignedSession] = None,
                                flash: Option[FlashSession] = None,
                                fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 415
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    UnsupportedMediaType(body, headers, session, flash, fromController)
}

case class UriTooLong(body: String,
                      headers: List[HttpHeader] = List(),
                      session: Option[SignedSession] = None,
                      flash: Option[FlashSession] = None,
                      fromController: Boolean = false) extends HttpResponse
{
  val statusCode: Int = 414
  def copy(body: String = body,
           headers: List[HttpHeader] = headers,
           session: Option[SignedSession] = session,
           flash: Option[FlashSession] = flash,
           fromController: Boolean = fromController): HttpResponse =
    UriTooLong(body, headers, session, flash, fromController)
}
