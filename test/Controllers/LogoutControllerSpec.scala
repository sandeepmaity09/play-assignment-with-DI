package Controllers

import controllers.LogoutController
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play._
import play.api.mvc.Result
import play.api.test._
import play.api.test.Helpers._
import services.PersonService

import scala.concurrent.Future

class LogoutControllerSpec extends PlaySpec with OneAppPerTest with MockitoSugar {
  "Sing In Page" should {
    "should render the sign in page" in {
      val logoutcontroller = new LogoutController()
      val result:Future[Result] = logoutcontroller.logout().apply(FakeRequest())
      val bodyText:String = contentAsString(result)
      status(result) mustBe 303
//      contentType(result) mustBe Some("text/html")
    }
  }
}
