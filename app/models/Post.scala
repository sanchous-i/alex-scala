package models


import anorm._
import anorm.SqlParser._

import play.api.db._
import play.api.Play.current
import java.util.Date

case class Post(id: Long, title: String, content: String, created_at: Date)

object Post {

  val post = {
    get[Long]("id") ~ get[String]("title") ~ get[String]("content") ~ get[Date]("created_at") map {
      case id~title~content~created_at => Post(id, title, content, created_at);
    }
  }

  def all() : List[Post] = DB.withConnection {implicit c =>
    SQL("select * from posts order by created_at desc").as(post *)
  }
}