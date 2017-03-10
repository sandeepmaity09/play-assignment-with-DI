package Controllers

import controllers.SignUpController
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play._
import play.api.Configuration
import play.api.mvc.Result
import play.api.test._
import play.api.test.Helpers._
import services.PersonService

import scala.concurrent.Future

class SignUpControllerSpec extends PlaySpec with OneAppPerTest with MockitoSugar {
  "Sing In Page" should {
    "should render the sign in page" in {
      val person = mock[PersonService]
      val config = mock[Configuration]
      val singupcontroller = new SignUpController(person,config)
      val result:Future[Result] = singupcontroller.signUp().apply(FakeRequest())
      val bodyText:String = contentAsString(result)
      status(result) mustBe OK
      contentType(result) mustBe Some("text/html")
    }
  }
}
