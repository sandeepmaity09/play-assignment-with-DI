package Controllers

import controllers.SignInController
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play._
import play.api.mvc.Result
import play.api.test._
import play.api.test.Helpers._
import services.PersonService

import scala.concurrent.Future

class SignInControllerSpec extends PlaySpec with OneAppPerTest with MockitoSugar {
  "Sing In Page" should {
    "should render the sign in page" in {
      val person = mock[PersonService]
      val singincontroller = new SignInController(person)
      val result:Future[Result] = singincontroller.signIn().apply(FakeRequest())
      val bodyText:String = contentAsString(result)
      bodyText canEqual  "button"
    }
  }
}
