package com.blackboxsociety.mvc.form

import com.blackboxsociety.util.parser.{BodyParser, QueryStringParser}
import com.blackboxsociety.http.{HttpResource, HttpRequest}
import scalaz.concurrent.Task

case class Form(fields: Seq[FormField]) extends BodyParser[Form] with QueryStringParser[Form] {

  def fromQueryString(request: HttpRequest): Form = {
    this.copy(fields = fields.map(f => f.copy(value = request.resource.getParam(f.key))))
  }

  def fromBody(request: HttpRequest): Task[Form] = {
    request.getBody().map { s =>
      val m = HttpResource.queryStringToMap(s)
      this.copy(fields = fields.map(f => f.copy(value = m.get(f.key))))
    }
  }

  def apply(key: String): Option[String] = {
    fields.find(_.key == key).flatMap(_.value)
  }

  def hasErrors(): Boolean = {
    errors.length > 0
  }

  def errors(): Seq[FormError] = {
    fields.map(f => FormError(f, f.error.getOrElse(""))).filter(_.field.hasError())
  }

  def fold[A](success: Form => A, failure: Form => A): A = {
    val check = fields.map(_.test())

    val hasErrors = check.foldLeft(false)((b, f) => b || f.hasError)
    if(!hasErrors) {
      success(Form(check))
    } else {
      failure(Form(check))
    }
  }

}

case class FormError(field: FormField, error: String)

case class FormField(key: String, value: Option[String] = None, constraint: FormConstraint, error: Option[String] = None) {

  def hasError(): Boolean = !error.isEmpty

  def test(): FormField = {
    value.map { v =>
      this.copy(error = constraint.run(v).find(_._2 != None).flatMap(_._2))
    }.getOrElse(this.copy(error = Some(s"$key undefined")))
  }

}

case class FormConstraint(constraints: List[(String => Boolean, String)] = List()) {

  def run(value: String): List[(Boolean, Option[String])] = {
    constraints.map(c => {
      val t = c._1(value)
      if(t) {
        (t, None)
      } else {
        (t, Some(c._2))
      }
    })
  }

  def restrict(test: String => Boolean, error: String): FormConstraint = {
    this.copy(constraints.::(test -> error))
  }

}

object Form {

  def form(fields: (String, FormConstraint)*): Form = {
    Form(fields.map(f => FormField(f._1, None, f._2)))
  }

}
